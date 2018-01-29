package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem{
    
    @Override
    protected void initDefaultCommand(){
        setDefaultCommand(new Drive());
        
    }
    
    public void setSpeed(double speed){
        RobotMap.motorController.set(speed);
    }
    
    public void drive (double moveValue, double rotateValue){
        RobotMap.driveTrain.arcadeDrive()
    }
}
