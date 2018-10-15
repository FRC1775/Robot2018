package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnInCircles extends Command {
		
	@Override
	protected void execute() {
		while(OI.turnButton.get()) {
				
			double x=Robot.motorSubsystem.getRotateAngle();
			Robot.motorSubsystem.setRotateAngle(x+5);
			Robot.motorSubsystem.rotateAngle();

		}
	}

	@Override
	protected boolean isFinished() {
		return !OI.turnButton.get();
	}
	
}