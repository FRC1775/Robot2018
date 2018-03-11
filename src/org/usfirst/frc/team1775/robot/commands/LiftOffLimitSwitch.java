package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class LiftOffLimitSwitch extends Command {

    public LiftOffLimitSwitch() {
        requires(Robot.liftSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (!RobotMap.liftBottomLimitSwitch.get()) {
    		Robot.liftSubsystem.setSpeed(LiftSubsystem.UP_MIN_SPEED);
    	} else {
    		Robot.liftSubsystem.setSpeed(0);
    	}
    }

    protected boolean isFinished() {
        return RobotMap.liftBottomLimitSwitch.get();
    }

    protected void end() {
    	if (RobotMap.liftBottomLimitSwitch.get()) {
        	RobotMap.liftEncoder.reset();
    	}
    	Robot.liftSubsystem.setSpeed(0);
    }

    protected void interrupted() {
    	Robot.liftSubsystem.setSpeed(0);
    }
}
