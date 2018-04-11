package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RotateWhileDriving extends InstantCommand {
	
	double angle;
	
	public RotateWhileDriving(double angle) {
		requires(Robot.motorSubsystem);
		this.angle = angle;
	}
	
	public void execute() {
		Robot.motorSubsystem.setRotateAngleForAuto(angle);
	}
}

//8.8 degrees after 10 inches 