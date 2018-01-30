package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;

public class SetMotorSpeed extends Command{
    requires(Robot.MotorSubsystem);
    publi void execute(){
        double yVal = OI.myjoystick.getY();
        Robot.motorSubsystem.setSpeed(yVal);
    }
    boolean isFinished(){
        return false;
    }
}