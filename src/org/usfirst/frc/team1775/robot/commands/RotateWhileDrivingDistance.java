package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateWhileDrivingDistance extends Command {
	
	double wait;
	double angle;
	double initDistance;
	boolean reduceSpeed;
	
	public RotateWhileDrivingDistance(double wait, double angle, boolean reduceSpeed) {
		this.angle = angle;
		this.wait = wait;
		this.reduceSpeed = reduceSpeed;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		initDistance = Robot.motorSubsystem.getDistance();
		Robot.motorSubsystem.reduceMotorSpeed(reduceSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		return wait <= Robot.motorSubsystem.getDistance() - initDistance;
	}
	
	public void end() {
		Robot.motorSubsystem.setRotateAngleForAuto(angle);
	}
}
