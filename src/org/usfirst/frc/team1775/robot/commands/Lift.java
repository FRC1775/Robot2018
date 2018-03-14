package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.OI;

public class Lift extends Command {
	public Lift() {
		requires(Robot.liftSubsystem);
	}

	public void execute() {
		double upTrigger = OI.getRightTrigger();
		double downTrigger = OI.getLeftTrigger();
		
		if(upTrigger > 0.15) {
			System.out.println("reading trigger values for up");
			Robot.liftSubsystem.setSpeed(upTrigger);
		} else if(downTrigger > 0.15) {
			Robot.liftSubsystem.setSpeed(-downTrigger);
		} else {
			Robot.liftSubsystem.stop();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
