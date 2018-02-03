package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;
import org.usfirst.frc.team1775.robot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.usfirst.frc.team1775.robot.commands.drive;

public class MotorSubsystem extends Subsystem{
    
    @Override
    protected void initDefaultCommand(){
        setDefaultCommand(new Drive());
        
    }
    
    public void drive(double xSpeed, double zRotation){
        RobotMap.drive.arcadeDrive(xSpeed,zrotation);
    }
    
    public void drive (double moveValue, double rotateValue){
        RobotMap.driveTrain.arcadeDrive()
    }
}
