package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team1775.robot.Robot;

public class FlippyCube extends InstantCommand {
	private double speed;
	
	public FlippyCube(double speed) {
		requires(Robot.liftSubsystem);
		this.speed = speed;
	}

	public void execute() {
		Robot.liftSubsystem.flipCube(speed);
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.liftSubsystem.flipCube(0);
	}
	
	@Override
	protected void interrupted() {
		super.interrupted();
		Robot.liftSubsystem.flipCube(0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
