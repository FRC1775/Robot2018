package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.Robot;

public class LiftToHerdHeight extends Command {
	private double height = 0;

	public LiftToHerdHeight(double height) {
		requires(Robot.liftSubsystem);

		this.height = height;
	}

	@Override
	protected void execute() {
		Robot.liftSubsystem.liftSlow();
		Robot.liftSubsystem.setLiftHeight(height);
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
