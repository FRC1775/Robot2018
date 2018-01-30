package org.usfirst.frc.team1775.robot;
import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;
import org.usfirst.frc.team1775.robot.Subsystem;

public class MotorSubsystem extend Subsytem{
    
    public void initDefaultCommand(){
    @overide 
    setDefaultCommand(new Drive());
    
    }
    public void setSpeed(double speed){
        RobotMap.motorController.set(speed);
    }
    public void drive(double moveValue, double rotateValue){
    RobotMap.driveTrain.arcadeDrive();
    }
}