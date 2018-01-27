package org.usfirst.frc.team1775.robot.commands;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.command.Command;

public class SetServo extends Command {
    private double angle; 
    private boolean is Finished;
    
    @Override
	protected void execute() {
	    RobotMap.servo.set(angle);
	    angle += 0.01;
	    if (angle <= 0.5) {
	    } 
}

@Override
	protected boolean isFinished() {
	return isFinished; 
	}
}

=======
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SetServo extends Command {
    private double angle = 0;
    private boolean isFinished;
    
    @Override
    protected void initialize() {
        super.initialize();
        
        isFinished = false;
    }
    
    @Override
    protected void execute() {
        RobotMap.servo.set(angle);
        if(angle <= 0.5) {
            angle += 0.01;
        }
    }
    
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
>>>>>>> master
