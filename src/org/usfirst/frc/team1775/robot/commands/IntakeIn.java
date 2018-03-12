package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;

public class IntakeIn extends Command {
	public IntakeIn() {
		requires(Robot.intakeSubsystem);
	}

	public void execute() {
		Robot.intakeSubsystem.runIn();
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