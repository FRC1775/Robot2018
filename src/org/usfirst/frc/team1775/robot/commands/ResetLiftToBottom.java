package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ResetLiftToBottom extends Command {

    public ResetLiftToBottom() {
        requires(Robot.liftSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (!Robot.liftSubsystem.checkBottomLimitSwitch()) {
    		Robot.liftSubsystem.setSpeed(-LiftSubsystem.DOWN_MIN_SPEED);
    	}
    }

    protected boolean isFinished() {
        return Robot.liftSubsystem.checkBottomLimitSwitch();
    }
}
