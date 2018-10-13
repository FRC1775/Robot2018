package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class OpenArms extends Command{
	private boolean isOpen;
	
	public OpenArms(boolean isOpen) {
		requires(Robot.intakeOpenSubsystem);
		this.isOpen = isOpen;
	}

	protected void execute() {
		RobotMap.intakeOpen.set(isOpen);
		RobotMap.intakeClose.set(!isOpen);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}