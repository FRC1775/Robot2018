/*package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class IntakeLift extends InstantCommand {
	private boolean goingUp;

    public IntakeLift(boolean goingUp) {
    	this.goingUp = goingUp;
    }

    protected void execute() {
    	if (goingUp) {
    		RobotMap.intakeLiftDown.set(false);
    		RobotMap.intakeLiftUp.set(true);
    	} else {
    		RobotMap.intakeLiftUp.set(false);
    		RobotMap.intakeLiftDown.set(true);
    	}
    }
}
*/