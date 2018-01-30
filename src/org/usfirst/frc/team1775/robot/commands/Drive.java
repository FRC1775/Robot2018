package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;


public class Drive extends Command{
    requires(Robot.MotorSubsystem);
    public void execute();
    double yVal2 = OI.myjoystick.getY();
    double xVal2 = OI.myjoystick2.getX();
        Robot.motorSubsystem.drive(yVal2,xVal2);
}