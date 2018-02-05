package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Drive;
import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsytem extends Subsystem{
    @Override
    protected void initDefaultCommand() {
        Drive d = new Drive();
        setDefaultCommand(d);
    }
    public void drive(double xSpeed, double zRotation) {
        RobotMap.drive.arcadeDrive(-xSpeed, zRotation, true);
    }
}
