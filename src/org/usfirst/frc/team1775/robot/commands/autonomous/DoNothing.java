package org.usfirst.frc.team1775.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DoNothing extends Command {
	public DoNothing() {}

	@Override
	protected boolean isFinished() {
		return false;
	}

}