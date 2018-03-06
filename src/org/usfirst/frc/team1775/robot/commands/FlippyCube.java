package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;

public class FlippyCube extends Command {
	private double speed;
	
	public FlippyCube(double speed) {
		requires(Robot.liftSubsystem);
		this.speed = speed;
	}

	public void execute() {
		Robot.liftSubsystem.flipCube(speed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}