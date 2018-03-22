package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;

public class IntakeIn extends Command {
	private double speed;
	public IntakeIn(double speed) {
		requires(Robot.intakeSubsystem);
		this.speed = speed;
	}

	public void execute() {
		Robot.intakeSubsystem.runIn(speed);
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
