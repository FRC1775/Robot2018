package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickServo extends Command {
	public JoystickServo() {
		requires(Robot.exampleSubsystem);

	}

	@Override
	protected void execute() {
		double xVal = OI.myJoystick.getX();
		double yVal = OI.myJoystick.getY();

		double xServo = xVal * 90 + 90;
		double yServo = xVal * 90 + 90;
	}

	@Override
	protected boolean isFinished() {
		return false;

	}
}