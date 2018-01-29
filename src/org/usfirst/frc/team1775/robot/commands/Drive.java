package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.PWM;

public class Drive extends Command {

	public Drive() {
		requires(Robot.motorSubsystem);
	}

	@Override
	protected void execute() {
		double yVal = OI.leftJoystick.getY();
		double xVal = OI.rightJoystick.getX();
		this.drive(yVal, xVal);
	}

	public void setSpeed(double speed) {
		System.out.println(speed);
		RobotMap.motorController.set(speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}