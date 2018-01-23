package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Servo;

public class JoystickServo extends Command {
    
    @Override
    protected void execute() {
        double xVal = myJoystick.getX();
        double yVal = myJoystick.getY();
        
        double xServo = xVal * 90 + 90;
        double yServo = yVal * 90 + 90;
        
        RobotMap.servo.setAngle(xServo);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}