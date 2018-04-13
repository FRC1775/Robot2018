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
		if(reduceSpeed) {
			Robot.motorSubsystem.setMaxSpeed();
		}
		initDistance = Robot.motorSubsystem.getDistance();
	}
	
	@Override
	protected boolean isFinished() {
		return wait <= Robot.motorSubsystem.getDistance() - initDistance;
	}
	
	public void end() {
		Robot.motorSubsystem.setRotateAngleForAuto(angle);
	}
}

// 8.8 degrees after 10 inches