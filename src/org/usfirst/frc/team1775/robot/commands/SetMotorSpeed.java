package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.PWM;

public class SetMotorSpeed extends Command {

	public SetMotorSpeed() {
		requires(Robot.motorSubsystem);
	}

	@Override
	protected void execute() {
		double yVal = OI.myJoystick.getY();
		this.setSpeed(yVal);
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