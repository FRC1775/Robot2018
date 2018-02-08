package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Drive;
import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsytem extends Subsystem{
    

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Intake());
        // TODO Auto-generated method stub
    }
    
    protected void setSpeed(double yVal) {
        RobotMap.intakeCotnroller.set(yVal);
        RobotMap.intakeCotnroller2.set(yVal);
    }
    
}