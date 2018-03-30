package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

public class LiftUsingButton extends Command {
	private double height = 0;

	public LiftUsingButton(double height) {
		requires(Robot.liftSubsystem);

		this.height = height;
	}

	@Override
	protected void execute() {
		Robot.liftSubsystem.setSpeed(0.5);
	}

	@Override
	protected void interrupted() {
		Robot.liftSubsystem.stop();
	}

	@Override
	protected boolean isFinished() {
		return RobotMap.liftEncoder.get() - height >= -1 && RobotMap.liftEncoder.get() - height <= 1;
	}
}
