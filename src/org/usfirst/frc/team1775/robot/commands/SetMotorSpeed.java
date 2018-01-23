package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.PWM;

public class SetMotorSpeed extends Command {
    
    public SetMotorSpeed() {
        requires(Robot.motorSubsystem);
    }
    
    @Override
    protected void execute() {
        double yVal = OI.myJoystick.getY();
        Robot.motorSubsystem.setSpeed(yVal);
    }
    
    public void setSpeed(double speed) {
        RobotMap.motorController.set(speed);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}