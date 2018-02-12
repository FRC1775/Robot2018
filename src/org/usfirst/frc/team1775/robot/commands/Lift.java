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
		Robot.liftSubsystem.setSpeed(-OI.mechanismJoystick.getRawAxis(5));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}