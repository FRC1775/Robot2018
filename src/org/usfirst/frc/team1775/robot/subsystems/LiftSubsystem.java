package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Lift;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem {
//	practice bot values
//	public static final double UP_MIN_SPEED = 0.6;
//	public static final double DOWN_MIN_SPEED = 0.15;
//	public static final double DOWN_MAX_SPEED = 0.5;
//	public static final double UP_MAX_SPEED = 0.8;
	
//	competition bot values
	public static final double UP_MIN_SPEED = 0.55;
	public static final double DOWN_MIN_SPEED = 0.25;
	//public static final double DOWN_MAX_SPEED = 0.4;
	//public static final double UP_MAX_SPEED = 0.75;
	public static final double DOWN_MAX_SPEED = 0.5;
	public static final double UP_MAX_SPEED = 0.8;
	
	private static final double MIN_HEIGHT_START_RAMP = 30.0;
	private static final double MAX_HEIGHT_START_RAMP = 70.0;
	private static final double MAX_HEIGHT = 84.0;
	
	private static final double TARGET_TOLERANCE = 2;
	private static final double ON_TARGET_MIN_TIME = 500;
	
	private static final double START_RAMP_TIME_MS = 500.0;
	
	private static final double MIN_HEIGHT = 1;
	private static final int MIN_HEIGHT_FOR_CUBE_FLIP = 16;

	private double startTime = System.currentTimeMillis();
	private double firstTimeWithinTarget = -1;
	
	private boolean hasSeenBottomLimitSwitch;
	
	private PIDController liftToHeightPidController;
	
	public LiftSubsystem() {
		liftToHeightPidController = new PIDController(0.2, 0, 0, RobotMap.liftEncoder,
				(value) -> { 
					if(liftToHeightPidController.isEnabled()) {
						setSpeedForPid(value);
					}
				}, 0.02);
		// This line might not be the output range we want
		liftToHeightPidController.setOutputRange(-DOWN_MAX_SPEED, UP_MAX_SPEED);
		liftToHeightPidController.setInputRange(0, MAX_HEIGHT);
		liftToHeightPidController.setAbsoluteTolerance(TARGET_TOLERANCE);
		addChild(liftToHeightPidController);
	}
	
	public void setLiftHeight(double height) {
		liftToHeightPidController.setSetpoint(height);
		liftToHeightPidController.enable();
	}
	
	public void liftSlow() {
		liftToHeightPidController.setOutputRange(-DOWN_MIN_SPEED, UP_MAX_SPEED);
	}
	
	public void liftHeight() {
		// Nothing to do here at the moment
	}
	
	private boolean resetLift;
	private long resetStartTime;
	
	public void initSendable(SendableBuilder builder) {
		builder.addDoubleProperty("lift height", () -> { return RobotMap.liftEncoder.getDistance(); }, null);
		builder.addBooleanProperty("resetGyro", 
			() -> {
				if (resetLift)
				{
					setSpeed(-DOWN_MIN_SPEED);
					if (!RobotMap.liftBottomLimitSwitch.get()) {
						resetLift = false;
						resetStartTime = System.currentTimeMillis();
					}
				}
				if (!RobotMap.liftBottomLimitSwitch.get() && System.currentTimeMillis() - resetStartTime < 500) {
					RobotMap.liftEncoder.reset();
				}
				return resetLift;
			}, 
			(value) -> {
				if (value) {
					resetLift = true;
				}
			});
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Lift());
	}
	
	public void stop() {
		// this might disable the motor controller for too long because it's for
		// safety purposes, so it might need to be changed
		liftToHeightPidController.disable();
		RobotMap.liftMotorController1.stopMotor();
		brake();
	}
	
	public void flipCube(double speed) {
		if (RobotMap.liftEncoder.getDistance() >= MIN_HEIGHT_FOR_CUBE_FLIP) {
			RobotMap.cubeFlip.set(speed);
		}
	}

	public void setSpeed(double speed) {
		liftToHeightPidController.disable();
		double outputSpeed = 0;

		if (isAllowedToGoUp(speed) || isAllowedToGoDown(speed)) {
			unbrake();
			outputSpeed = getAdjustedSpeed(speed);
		} else {
			brake();
			outputSpeed = 0;
			startTime = System.currentTimeMillis();
		}
		
		if (Robot.liftSubsystem.checkBottomLimitSwitch()) {
			RobotMap.liftEncoder.reset();
		}
		
		SmartDashboard.putNumber("lift encoder", RobotMap.liftEncoder.getDistance());
		RobotMap.liftMotorController1.set(outputSpeed); // Must call set, not setSpeed, to take into account the setInverted on the controller
	}
	
	public boolean isLiftHeightOnTarget()
	{
		if (liftToHeightPidController.onTarget()) {
			if (firstTimeWithinTarget == -1) {
				firstTimeWithinTarget = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() - firstTimeWithinTarget > ON_TARGET_MIN_TIME) {
				return true;
			}
		} else {
			firstTimeWithinTarget = -1;
		}
		return false;
	}
	
	private static void setSpeedForPid(double speed) {
		double outputSpeed = 0;
		
		if (speed > 0 && !Robot.liftSubsystem.checkTopLimitSwitch() || speed < 0 && !Robot.liftSubsystem.checkBottomLimitSwitch()) {
			unbrake();
			outputSpeed = speed;
		} else {
			outputSpeed = 0;
		}
		
		RobotMap.liftMotorController1.set(outputSpeed);
	}
	
	private boolean isAllowedToGoUp(double inputLiftSpeed) {
		return inputLiftSpeed >= UP_MIN_SPEED && !Robot.liftSubsystem.checkTopLimitSwitch();
	}
	
	private boolean isAllowedToGoDown(double inputLiftSpeed) {
		if(hasSeenBottomLimitSwitch) {
			return inputLiftSpeed <= -DOWN_MIN_SPEED && !Robot.liftSubsystem.checkBottomLimitSwitch() && 
					RobotMap.liftEncoder.getDistance() > MIN_HEIGHT;
		}
		return inputLiftSpeed <= -DOWN_MIN_SPEED && !Robot.liftSubsystem.checkBottomLimitSwitch();
	}
	
	private double getAdjustedSpeed(double inputLiftSpeed) {
		double ramp = Math.min((System.currentTimeMillis() - startTime) / START_RAMP_TIME_MS, 1);
		
		if (inputLiftSpeed <= -DOWN_MIN_SPEED) { // Going down
			if (RobotMap.liftEncoder.getDistance() < MIN_HEIGHT_START_RAMP) {
				ramp = Math.max(RobotMap.liftEncoder.getDistance() / MIN_HEIGHT_START_RAMP, 0);
			}
			return -DOWN_MIN_SPEED + ramp * (Math.max(inputLiftSpeed, -DOWN_MAX_SPEED) + DOWN_MIN_SPEED);
		} else if (inputLiftSpeed >= UP_MIN_SPEED) {
			if (RobotMap.liftEncoder.getDistance() > MAX_HEIGHT_START_RAMP) {
				ramp = Math.max(1 - (RobotMap.liftEncoder.getDistance() - MAX_HEIGHT_START_RAMP) / (MAX_HEIGHT - MAX_HEIGHT_START_RAMP), 0);
			}
			return UP_MIN_SPEED + ramp * (Math.min(inputLiftSpeed, UP_MAX_SPEED) - UP_MIN_SPEED);
		}
		return 0;
	}
	
	private static void unbrake() {
		RobotMap.liftBrake.set(false);
		RobotMap.liftUnbrake.set(true);
	}
	
	private static void brake() {
		RobotMap.liftBrake.set(true);
		RobotMap.liftUnbrake.set(false);
	}
	
	public boolean checkBottomLimitSwitch() {
		boolean bottomLimitSwitchHit = !RobotMap.liftBottomLimitSwitch.get();
		
		if(bottomLimitSwitchHit) {
			hasSeenBottomLimitSwitch = true;
		}
		
		return bottomLimitSwitchHit;
	}
	
	public boolean checkTopLimitSwitch() {
		return !RobotMap.liftTopLimitSwitch.get();
	}
}
