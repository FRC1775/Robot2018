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
		if(upTrigger != 0) {
			RobotMap.liftBrake.set(false);
			RobotMap.liftUnbrake.set(true);
			Robot.liftSubsystem.setSpeed(upTrigger);
		}else if(downTrigger != 0) {
			RobotMap.liftBrake.set(false);
			RobotMap.liftUnbrake.set(true);
			Robot.liftSubsystem.setSpeed(-downTrigger);
		}else if(upTrigger == 0 && downTrigger == 0) {
			RobotMap.liftBrake.set(true);
			RobotMap.liftUnbrake.set(false);
		}
		// Robot.liftSubsystem.setSpeed(-OI.operatorJoystick.getRawAxis(5));
		// Robot.liftSubsystem.flipCube(-OI.operatorJoystick.getRawAxis(2)+OI.operatorJoystick.getRawAxis(3));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}