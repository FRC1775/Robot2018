package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class SetServo extends Command {
    private double angle; 
    private boolean isFinished;
    
    @Override
	protected void execute() {
	    //RobotMap.servo.set(angle);
	    angle += 0.01;
	    if (angle <= 0.5) {
	    } 
}

@Override
	protected boolean isFinished() {
	return isFinished; 
	}
}
