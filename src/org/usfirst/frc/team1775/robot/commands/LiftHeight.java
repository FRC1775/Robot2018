package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.Robot;

public class LiftHeight extends Command {
	private double height = 0;

	public LiftHeight(double height) {
		requires(Robot.liftSubsystem);

		this.height = height;
	}

	@Override
	protected void initialize() {
		Robot.liftSubsystem.setLiftHeight(height);
	}

	@Override
	protected void execute() {
		Robot.liftSubsystem.liftHeight();
	}

	@Override
	protected void interrupted() {
		Robot.liftSubsystem.stop();
	}

	@Override
	protected boolean isFinished() {
		return Robot.liftSubsystem.isLiftHeightOnTarget();
	}
}
