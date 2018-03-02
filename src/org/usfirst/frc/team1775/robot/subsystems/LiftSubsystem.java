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
	private static final double START_RAMP_TIME_MS = 500.0;
	private static final double MAX_SPEED = 0.6;
	private static final double LIFT_SPEED_DEADBAND = 0.1;

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
	}

	public void setSpeed(double speed) {
		double outputSpeed = 0;

		if (isAllowedToGoUp(speed)) {
			outputSpeed = speed * getRampAndMaxSpeedMultiplier();
		} else if (isAllowedToGoDown(speed)) {
			outputSpeed = speed * getRampAndMaxSpeedMultiplier();
		} else {
			outputSpeed = 0;
			startTime = System.currentTimeMillis();
		}
		SmartDashboard.putNumber("lift encoder", RobotMap.liftEncoder.getDistance());
		RobotMap.liftMotorController1.set(outputSpeed); // Must call set, not setSpeed, to take into account the setInverted on the controller
	}
	
	private boolean isAllowedToGoUp(double inputLiftSpeed) {
		return inputLiftSpeed > LIFT_SPEED_DEADBAND && RobotMap.liftTopLimitSwitch.get();
	}
	
	private boolean isAllowedToGoDown(double inputLiftSpeed) {
		return inputLiftSpeed < -LIFT_SPEED_DEADBAND && RobotMap.liftBottomLimitSwitch.get();
	}
	
	private double getRampAndMaxSpeedMultiplier() {
		return Math.min((System.currentTimeMillis() - startTime) / START_RAMP_TIME_MS, 1) * MAX_SPEED;
	}
}