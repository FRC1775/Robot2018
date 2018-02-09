package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Intake;
import org.usfirst.frc.team1775.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem{
    

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Lift());
        // TODO Auto-generated method stub
    }
    
    public void setSpeed(double yVal) {
        RobotMap.liftController.set(yVal);
        RobotMap.liftController2.set(yVal);
    }
}