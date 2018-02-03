package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.Robot;

public class DriveDistance extends Command {
	private double distance = 0;

	public DriveDistance(double distance) {
		requires(Robot.motorSubsystem);
		
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		Robot.motorSubsystem.setDriveDistance(distance);
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
		return Robot.motorSubsystem.isFinished();
	}

}