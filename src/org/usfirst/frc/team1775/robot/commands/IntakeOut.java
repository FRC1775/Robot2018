package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;

public class IntakeOut extends FallbackCommand {
	public IntakeOut() {
		requires(Robot.intakeSubsystem);
	}

	public void execute() {
		Robot.intakeSubsystem.runOut();
	}

	@Override
	protected void interrupted() {
		super.interrupted();
		Robot.intakeSubsystem.stop();
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.intakeSubsystem.stop();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}