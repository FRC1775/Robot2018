package org.usfirst.frc.team1775.robot.commands;
import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command {
    public Intake(){
        requires(Robot.intakeSubsystem);
    }
 
    @Override
    protected void execute() {
        double yVal = OI.subsystemsJoystick.getRawAxis(1);
        Robot.intakeSubsystem.setSpeed(yVal);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}