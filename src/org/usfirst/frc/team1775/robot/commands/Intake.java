package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

public class Intake extends Command{
    public Intake(){
        requires(Robot.IntakeSubsystem);
    }
 
    @Override
    protected void execute() {
        double xVal = OI.myJoystick.getRawAxis(4);
        Robot.IntakeSubsystem.setSpeed(xVal);
    }
    
    protected boolean isFinished() {
        return false;
    }