package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsytem extends Subsystem{
    

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SetMotorSpeed());
        // TODO Auto-generated method stub
        public void setSpeed(double speed)
    }
    
    
    }