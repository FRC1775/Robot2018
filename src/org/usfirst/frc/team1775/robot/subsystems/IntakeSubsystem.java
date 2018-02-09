package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Intake;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{
    

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Intake());
        // TODO Auto-generated method stub
    }
    
    public void setSpeed(double yVal) {
        RobotMap.intakeController.set(yVal);
        RobotMap.intakeController2.set(yVal);
    }
    
}