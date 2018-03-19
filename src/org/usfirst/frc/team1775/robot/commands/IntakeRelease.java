package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class IntakeRelease extends TimedCommand {

    public IntakeRelease() {
    	super(1);
    }

    protected void execute() {
    	if (RobotMap.intakeLiftUp.get()) {
	    	RobotMap.leftIntakeRelease.setAngle(0);
	    	RobotMap.rightIntakeRelease.setAngle(0);
    	}
    }

    protected void end() {
    	RobotMap.leftIntakeRelease.setAngle(180);
    	RobotMap.rightIntakeRelease.setAngle(180);
    }
}
