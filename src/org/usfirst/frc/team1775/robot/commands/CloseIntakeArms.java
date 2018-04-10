package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class CloseIntakeArms extends InstantCommand {
	
    protected void execute() {
    	RobotMap.intakeClose.set(true);
    }
}