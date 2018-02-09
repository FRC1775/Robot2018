package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{
    
    public void initDefaultCommand(){
    setDefaultCommand(new Intake());
    
    }
    public void setSpeed(double speed){
        RobotMap.motorController3.set(speed);
        RobotMap.motorController4.set(speed);
    }
    
}