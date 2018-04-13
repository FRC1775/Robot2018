package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RotateWhileDrivingTimed extends WaitCommand {
	
	double angle;
	
	public RotateWhileDrivingTimed(double wait, double angle) {
//		super(0);
		super(wait);
		
		this.angle = angle;
	}
	
	public void end() {
		Robot.motorSubsystem.setRotateAngleForAuto(angle);
	}
}

// 8.8 degrees after 10 inches