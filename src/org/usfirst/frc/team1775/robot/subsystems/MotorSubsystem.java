package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Drive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem{
    

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
        // TODO Auto-generated method stub
    }
    public void drive(double xSpeed, double zRotation) {
        RobotMap.drive.arcadeDrive(-xSpeed, zRotation, true);
    }
    
    public void setRotateAngle(double ang) {}
    public void rotateAngle() {}
}