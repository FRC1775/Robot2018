package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
    public Drive(){
        requires(Robot.motorSubsystem);
    }
 
    @Override
    protected void execute() {
        double yVal = OI.myJoystick.getRawAxis(1);
        double xVal = OI.myJoystick.getRawAxis(4);
        Robot.motorSubsystem.drive(yVal, xVal);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}