package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem {
	private enum DriveMode {
		Regular, RotateToAngle, DriveToDistance
	}

	private static DriveMode driveMode = DriveMode.Regular;

	double driveToDistancePidResult = 0;
	double rotateToAnglePidResult = 0;

	private PIDController driveToDistancePidController = new PIDController(0, 0, 0, (PIDSource) RobotMap.driveEncoder,
			(value) -> {
				driveToDistancePidResult = value;
			}, 0.02);
	
	private PIDController rotateToAnglePidController = new PIDController(0 ,0 ,0 ,(PIDSource) RobotMap.gyro,
			(value) ->  {
				rotateToAnglePidResult = value;
			}, 0.02);

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
		driveToDistancePidController.setOutputRange(-0.9, 0.9);
	}

	public void drive(double moveValue, double rotateValue) {
		RobotMap.drive.arcadeDrive(moveValue, rotateValue * -1);
	}

	public void driveDistance() {
		RobotMap.drive.arcadeDrive(driveToDistancePidResult, 0, false);
	}
	
	public void rotateAngle() {
		RobotMap.drive.arcadeDrive(0, rotateToAnglePidResult);
	}

	public boolean isFinished() {
		if (driveMode == DriveMode.DriveToDistance) {
			return driveToDistancePidController.onTarget();
		} else if (driveMode == DriveMode.RotateToAngle) {
			return rotateToAnglePidController.onTarget();
		}

		return false;
	}

	public void setDriveDistance(double distance) {
		setDriveMode(DriveMode.DriveToDistance);

		RobotMap.driveEncoder.reset();

		driveToDistancePidController.setPID(0.35, 0, 2.3);
		driveToDistancePidController.setInputRange(-100, 100);
		driveToDistancePidController.setContinuous(true);
		driveToDistancePidController.setOutputRange(-0.5, 0.5);
		driveToDistancePidController.setAbsoluteTolerance(0.5);
		driveToDistancePidController.setToleranceBuffer(20);

		driveToDistancePidController.setSetpoint(distance);
		driveToDistancePidController.enable();
	}
	
	
	public void setRotateAngle(double angle) {
		setDriveMode(DriveMode.RotateToAngle);

		RobotMap.driveEncoder.reset();

		driveToDistancePidController.setPID(0.35, 0, 2.3);
		driveToDistancePidController.setInputRange(-100, 100);
		driveToDistancePidController.setContinuous(true);
		driveToDistancePidController.setOutputRange(-0.8, 0.8);
		driveToDistancePidController.setAbsoluteTolerance(1);
		driveToDistancePidController.setToleranceBuffer(40);

		driveToDistancePidController.setSetpoint(angle);
		driveToDistancePidController.enable();
	}

	public void stop() {
		RobotMap.drive.stopMotor();
	}

	private void setDriveMode(DriveMode mode) {
		if (driveMode == mode)
			return;

		driveMode = mode;
	}
}