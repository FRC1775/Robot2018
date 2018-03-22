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
	private static final double DEFAULT_ROTATE_RAMP_TIME = 400;
	private static final double ON_TARGET_MIN_TIME = 500;
	
	private enum DriveMode {
		Regular, RotateToAngle, DriveToDistance
	}

	private static DriveMode driveMode = DriveMode.Regular;

	double driveToDistancePidResult = 0;
	double rotateToAnglePidResult = 0;

	private PIDController driveToDistancePidController;
	
	private PIDController rotateToAnglePidController;
	
	private PIDController straightDrivePidController;
	
	boolean shouldSetPoint = true;
	
	private double firstTimeWithinTarget = -1;
	
	double rotateInPlaceCurrentRampFactor = 0;
	double rotateInPlaceStartTime = 0;
	double straightDriveRotateCompensationValue = 0;
	
	public MotorSubsystem() {
		driveToDistancePidController = new PIDController(1, 0, 5, this,
				(value) -> {
					if (driveToDistancePidController.isEnabled()) {
						RobotMap.drive.arcadeDrive(value, -straightDriveRotateCompensationValue);
					}
				}, 0.02);
		
		driveToDistancePidController.setInputRange(-300, 300);
		driveToDistancePidController.setContinuous(true);
		driveToDistancePidController.setOutputRange(-0.6, 0.6);
		driveToDistancePidController.setAbsoluteTolerance(2);
		//addChild(driveToDistancePidController);
		
		rotateToAnglePidController = new PIDController(0.15, 0, 0.45,(PIDSource) RobotMap.gyro,
				(value) ->  {
					if (rotateToAnglePidController.isEnabled()) {
						RobotMap.drive.arcadeDrive(0, value);
					}
				}, 0.02);
		rotateToAnglePidController.setInputRange(-180, 180);
		rotateToAnglePidController.setOutputRange(-0.75, 0.75);
		rotateToAnglePidController.setAbsoluteTolerance(2);
		rotateToAnglePidController.setContinuous();
		addChild(rotateToAnglePidController);
		
		// Ivan suggests putting a P that's about 1/4 of what the rotateToAnglePidController is
		straightDrivePidController = new PIDController(-0.2, 0.0, 0.0, (PIDSource) RobotMap.gyro, (value) -> {
			straightDriveRotateCompensationValue = value;
		}, 0.01);
		straightDrivePidController.setOutputRange(-0.4, 0.4);
		straightDrivePidController.setSetpoint(0);
		straightDrivePidController.enable();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void drive(double moveValue, double rotateValue) {
		rotateToAnglePidController.disable();
		driveToDistancePidController.disable();
		double realRotateValue = rotateValue;
		double realMoveValue = -moveValue;
		if(moveValue > 0.15 || moveValue < -0.15) {
			// changed from -moveValue to realMoveValue
			realRotateValue = realMoveValue * rotateValue;
		} else {
			if (rotateValue < 0.15 && rotateValue > -0.15) {

				rotateInPlaceCurrentRampFactor = 0;
				rotateInPlaceStartTime = System.currentTimeMillis();
			}
			realMoveValue = 0;
						
			//SmartDashboard.putNumber("DriveTrain.rotateValue", rotateValue);
			//SmartDashboard.putBoolean("DriveTrain.squaredInputs", squaredInputs);

			// TODO handle ramp of rotate
			rotateInPlaceCurrentRampFactor = Math.min(1, (System.currentTimeMillis() - rotateInPlaceStartTime) / (double) DEFAULT_ROTATE_RAMP_TIME);
			realRotateValue = rotateValue * rotateInPlaceCurrentRampFactor;
		}
		SmartDashboard.putNumber("Distance", getDistance());
		SmartDashboard.putNumber("Angle", RobotMap.gyro.getAngle());
		
		realRotateValue = driveStraightCorrection(moveValue, realRotateValue);
		RobotMap.drive.arcadeDrive(realMoveValue, realRotateValue, true);
	}
	
	private double driveStraightCorrection(double moveValue, double rotateValue) {
		if (rotateValue < 0.2 && rotateValue > -0.2) {
			if (shouldSetPoint || (moveValue < 0.1 && moveValue > -0.1)) {
				RobotMap.gyro.reset();
				shouldSetPoint = false;
			}
			return -straightDriveRotateCompensationValue;
		} else {
			shouldSetPoint = true;
			return rotateValue;
		}
	}

	public void driveDistance() {
		rotateToAnglePidController.disable();
		SmartDashboard.putNumber("Distance", getDistance());
	}
	
	public void rotateAngle() {
		driveToDistancePidController.disable();
		SmartDashboard.putNumber("Angle", RobotMap.gyro.getAngle());
	}

	public void setDriveDistance(double distance) {
		setDriveMode(DriveMode.DriveToDistance);

		RobotMap.driveEncoderLeft.reset();
		RobotMap.driveEncoderRight.reset();
		
		RobotMap.gyro.reset();
		RobotMap.gyro.zeroYaw();

		driveToDistancePidController.setSetpoint(distance);
		driveToDistancePidController.enable();
	}
	
	
	public void setRotateAngle(double angle) {
		setDriveMode(DriveMode.RotateToAngle);

		RobotMap.gyro.reset();
		RobotMap.gyro.zeroYaw();
		
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
		builder.addDoubleProperty("distance", () -> { return getDistance(); }, null);
		builder.addBooleanProperty("reset drive encoder", () -> { return false; }, (value) -> {
			if (value) {
				driveToDistancePidController.reset();
				RobotMap.driveEncoderLeft.reset();
				RobotMap.driveEncoderRight.reset();
				RobotMap.gyro.reset();
				RobotMap.gyro.zeroYaw();
			}
		});
	}
	
	public boolean isDriveDistanceOnTarget() {
		if (driveToDistancePidController.onTarget()) {
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
		return ((RobotMap.driveEncoderLeft.getDistance() - RobotMap.driveEncoderRight.getDistance()) / 2.0);
	}
}
