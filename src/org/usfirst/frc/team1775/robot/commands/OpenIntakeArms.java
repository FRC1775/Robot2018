package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class OpenIntakeArms extends Command {
	
	public OpenIntakeArms() {
		requires(Robot.intakeOpenSubsystem);
	}
    protected void execute() {
    	if(OI.getPOVDirection() == -1) {
        	RobotMap.intakeClose.set(true);
        	RobotMap.intakeOpen.set(false);
    	}else {
    		RobotMap.intakeOpen.set(true);
    		RobotMap.intakeClose.set(false);
    	}
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}