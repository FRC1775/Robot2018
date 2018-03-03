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
		// Robot.liftSubsystem.setSpeed(-OI.operatorJoystick.getRawAxis(5));
		
		// Robot.liftSubsystem.flipCube(-OI.operatorJoystick.getRawAxis(2)+OI.operatorJoystick.getRawAxis(3));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}