package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakeLift extends InstantCommand {
	private boolean goingUp;

    public IntakeLift(boolean goingUp) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.goingUp = goingUp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (goingUp) {
    		RobotMap.intakeLiftDown.set(false);
    		RobotMap.intakeLiftUp.set(true);
    	} else {
    		RobotMap.intakeLiftUp.set(false);
    		RobotMap.intakeLiftDown.set(true);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
