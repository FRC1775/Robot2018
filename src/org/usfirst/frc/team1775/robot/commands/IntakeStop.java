package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team1775.robot.Robot;

public class IntakeStop extends InstantCommand {
	public IntakeStop() {
		requires(Robot.intakeSubsystem);
	}

	public void execute() {
		Robot.intakeSubsystem.stop();
	}
	
	@Override
	protected void interrupted() {
		Robot.intakeSubsystem.stop();
	}
	
	@Override
	protected void end() {
		Robot.intakeSubsystem.stop();
	}
}