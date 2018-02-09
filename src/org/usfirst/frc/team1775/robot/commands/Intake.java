package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;


public class Intake extends Command{
	
    public Intake () {
    	requires(Robot.intakeSubsystem);
    }
    public void execute() {
    double yVal2 = OI.myJoystick2.getRawAxis(1);
    Robot.intakeSubsystem.setSpeed(yVal2);
}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}