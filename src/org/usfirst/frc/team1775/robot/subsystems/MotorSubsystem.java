package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorSubsystem extends Subsystem implements PIDSource {
	private enum DriveMode {
		Regular, RotateToAngle, DriveToDistance
	}

	private static DriveMode driveMode = DriveMode.Regular;
	
	double driveToDistancePidResult = 0;
	double rotateToAnglePidResult = 0;

	private PIDController driveToDistancePidController = new PIDController(0, 0, 0, this,
			(value) -> {
				driveToDistancePidResult = value;
			}, 0.02);
	
	private PIDController rotateToAnglePidController;
	
	public MotorSubsystem() {
		rotateToAnglePidController = new PIDController(0 ,0 ,0 ,RobotMap.gyro,
				(value) ->  {
					RobotMap.drive.arcadeDrive(0, value);
//					System.out.println(value);
				}, 0.02);
		rotateToAnglePidController.setInputRange(-180, 180);
		rotateToAnglePidController.setOutputRange(-0.75, 0.75);
		rotateToAnglePidController.setAbsoluteTolerance(2);
		rotateToAnglePidController.setContinuous();
		addChild(rotateToAnglePidController);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
		driveToDistancePidController.setOutputRange(-0.9, 0.9);
	}

	public void drive(double moveValue, double rotateValue) {
		rotateToAnglePidController.disable();
		SmartDashboard.putNumber("Distance", getDistance());
		SmartDashboard.putNumber("Angle", RobotMap.gyro.getAngle());
		RobotMap.drive.arcadeDrive(-moveValue, rotateValue, true);
	}

	public void driveDistance() {
		rotateToAnglePidController.disable();
		RobotMap.drive.arcadeDrive(driveToDistancePidResult, 0, false);
	}
	
	public void rotateAngle() {
		rotateToAnglePidController.enable();
		SmartDashboard.putNumber("Angle", RobotMap.gyro.getAngle());
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

		RobotMap.driveEncoderLeft.reset();
		RobotMap.driveEncoderRight.reset();

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

		RobotMap.gyro.reset();
		RobotMap.gyro.zeroYaw();

		rotateToAnglePidController.setPID(0.02, 0, 0);

		rotateToAnglePidController.setSetpoint(angle);
		rotateToAnglePidController.enable();
	}

	public void stop() {
		RobotMap.drive.stopMotor();
	}

	private void setDriveMode(DriveMode mode) {
		if (driveMode == mode)
			return;

		driveMode = mode;
	}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.addDoubleProperty("navx/angle", () -> { return RobotMap.gyro.getAngle(); }, null);
		builder.addBooleanProperty("resetGyro", () -> { return false; }, (value) -> {
			if (value) {
				rotateToAnglePidController.reset();
				RobotMap.gyro.reset();
				RobotMap.gyro.zeroYaw();
			}
		});
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// This is for the drive train encoders
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// This is for the drive train encoders
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// This is for the drive train encoders
		return getDistance();
	}
	
	private double getDistance() {
		SmartDashboard.putNumber("LeftEncoder", RobotMap.driveEncoderLeft.getDistance());
		SmartDashboard.putNumber("RightEncoder", -RobotMap.driveEncoderRight.getDistance());
		return (RobotMap.driveEncoderLeft.getDistance() + -RobotMap.driveEncoderRight.getDistance()) / 2.0;
	}
}