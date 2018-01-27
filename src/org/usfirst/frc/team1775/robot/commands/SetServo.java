package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.RobotMap;

public class SetServo extends Command{
    private double angle;
    private boolean isFinished;

    @Override
	protected void execute() {
	  RobotMap.servo.set(angle);
	  if (angle <= 0.25) {
        angle += 0.01;
	  }
	  
    }

     
    @Override
    protected boolean isFinished(){
        return isFinished;
    }
}