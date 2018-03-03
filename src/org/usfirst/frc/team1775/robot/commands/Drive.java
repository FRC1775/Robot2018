package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;

public class Drive extends Command {
	public Drive() {
		requires(Robot.motorSubsystem);
	}

	public void execute() {
		double yVal2 = OI.getLeftAnalogY();
		double xVal2 = OI.getRightAnalogX();
		Robot.motorSubsystem.drive(yVal2, xVal2);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}