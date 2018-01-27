package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickServo extends Command {

public double inputJoystick;
public double atAngle;    

protected void execute(){
inputJoystick = driver.getX();
atAngle = 90(inputJoystick)+90;

RobotMap.servo.setAngle(atAngle);
}   

protected boolean isFinished(){
return false;
}
}