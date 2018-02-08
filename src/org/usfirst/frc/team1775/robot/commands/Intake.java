package org.usfirst.frc.team1775.robot.commands;

public class Intake extends Command{
    public Intake(){
        requires(Robot.IntakeSubsystem);
    }
 
    @Override
    protected void execute() {
        double xVal = OI.myJoystick.getRawAxis(4);
        Robot.IntakeSubsystem.setSpeed(xVal);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }