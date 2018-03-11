package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Lift;
//import org.usfirst.frc.team1775.robot.subsystems.MotorSubsystem.DriveMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem {
	public static final double UP_MIN_SPEED = 0.6;
	public static final double DOWN_MIN_SPEED = 0.25;
	public static final double DOWN_MAX_SPEED = 0.5;
	public static final double UP_MAX_SPEED = 0.8;
	
	private static final double MIN_HEIGHT_START_RAMP = 20.0;
	private static final double MAX_HEIGHT_START_RAMP = 70.0;
	private static final double MAX_HEIGHT = 84.0;
	
	private static final double START_RAMP_TIME_MS = 500.0;

	private double startTime = System.currentTimeMillis();
	
	double liftToHeightPidResult = 0;
	
	private PIDController liftToHeightPidController;
	
	public LiftSubsystem() {
		liftToHeightPidController = new PIDController(0, 0, 0, RobotMap.liftEncoder,
				(value) -> {
					liftToHeightPidResult = value;
				}, 0.02);
		// This line might not be the output range we want
		liftToHeightPidController.setOutputRange(-.8, .8);
		addChild(liftToHeightPidController);
	}
	
	public void setLiftHeight(double height) {
		liftToHeightPidController.setPID(0, 0, 0);
		liftToHeightPidController.setSetpoint(height);
		liftToHeightPidController.enable();
	}
	
	public void liftHeight() {
		setSpeed(liftToHeightPidResult);
	}
	
	public void initSendable(SendableBuilder builder) {
		builder.addDoubleProperty("lift height", () -> { return RobotMap.liftEncoder.getDistance(); }, null);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Lift());
	}
	
	public void stop() {
		// this might disable the motor controller for too long because it's for
		// safety purposes, so it might need to be changed
		RobotMap.liftMotorController1.stopMotor();
		brake();
	}
	
	public void flipCube(double speed) {
		//if (RobotMap.liftEncoder.getDistance() > 20) {
			RobotMap.cubeFlip.set(speed);
		//}
	}

	public void setSpeed(double speed) {
		double outputSpeed = 0;
		
		System.out.println(RobotMap.liftEncoder.getDistance());

		if (isAllowedToGoUp(speed) || isAllowedToGoDown(speed)) {
			unbrake();
			outputSpeed = getAdjustedSpeed(speed);
		} else {
			brake();
			outputSpeed = 0;
			startTime = System.currentTimeMillis();
		}
		
		if (!RobotMap.liftBottomLimitSwitch.get()) {
			RobotMap.liftEncoder.reset();
		}
		
		SmartDashboard.putNumber("lift encoder", RobotMap.liftEncoder.getDistance());
		RobotMap.liftMotorController1.set(outputSpeed); // Must call set, not setSpeed, to take into account the setInverted on the controller
	}
	
	private boolean isAllowedToGoUp(double inputLiftSpeed) {
		return inputLiftSpeed >= UP_MIN_SPEED && RobotMap.liftTopLimitSwitch.get();
	}
	
	private boolean isAllowedToGoDown(double inputLiftSpeed) {
		return inputLiftSpeed <= -DOWN_MIN_SPEED && RobotMap.liftBottomLimitSwitch.get();
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
			System.out.println(UP_MIN_SPEED + ramp * (Math.min(inputLiftSpeed, UP_MAX_SPEED) - UP_MIN_SPEED));
			return UP_MIN_SPEED + ramp * (Math.min(inputLiftSpeed, UP_MAX_SPEED) - UP_MIN_SPEED);
		}
		return 0;
	}
	
	private void unbrake() {
		RobotMap.liftBrake.set(false);
		RobotMap.liftUnbrake.set(true);
	}
	
	private void brake() {
		RobotMap.liftBrake.set(true);
		RobotMap.liftUnbrake.set(false);
	}
}