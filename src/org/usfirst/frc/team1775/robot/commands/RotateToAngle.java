package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateToAngle extends Command {
	private double angle = 0;
	
	public RotateToAngle(double angle) {
		requires(Robot.motorSubsystem);
		
		this.angle = angle;
	}
	
	@Override
	protected void initialize() {
		Robot.motorSubsystem.setRotateAngle(this.angle);
	}
	
	@Override
	protected void execute() {
		Robot.motorSubsystem.rotateAngle();
	}

	@Override
	protected boolean isFinished() {
		return this.isFinished();
	}
}
