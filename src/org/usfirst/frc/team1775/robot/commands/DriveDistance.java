package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

public class DriveDistance extends Command {
	private double distance = 0;
	private long startTime = 0;

	public DriveDistance(double distance) {
		requires(Robot.motorSubsystem);

		this.distance = distance;
	}

	@Override
	protected void initialize() {
		Robot.motorSubsystem.setDriveDistance(distance);
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		Robot.motorSubsystem.driveDistance();
	}

	@Override
	protected void interrupted() {
		Robot.motorSubsystem.stop();
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - startTime > 500 && !RobotMap.gyro.isMoving();
	}

}