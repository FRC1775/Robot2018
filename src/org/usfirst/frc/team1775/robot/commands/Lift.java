package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;

public class Lift extends Command {
	public Lift() {
		requires(Robot.liftSubsystem);
	}

	public void execute() {
		double yVal2 = OI.mechanismJoystick.getRawAxis(3);
		Robot.liftSubsystem.setSpeed(yVal2);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}