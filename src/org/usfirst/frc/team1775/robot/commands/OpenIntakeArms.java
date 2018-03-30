package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class OpenIntakeArms extends InstantCommand {
	private boolean open;
	
	public OpenIntakeArms(boolean open) {
		this.open = open;
	}
	
    protected void execute() {
    	RobotMap.intakeOpen.set(open);
    }
}