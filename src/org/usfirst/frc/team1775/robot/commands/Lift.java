package org.usfirst.frc.team1775.robot.commands;
import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Lift extends Command {
    public Lift(){
        requires(Robot.liftSubsystem);
    }
 
    @Override
    protected void execute() {
        double yVal = OI.subsystemsJoystick.getRawAxis(4);
        Robot.liftSubsystem.setSpeed(yVal);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}