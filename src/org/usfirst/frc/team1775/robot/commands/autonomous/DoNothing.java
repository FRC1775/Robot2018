package org.usfirst.frc.team1775.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DoNothing extends Command {

	public static DifferentialDrive drive;
	
	public DoNothing(DifferentialDrive driveD) {
		drive  = driveD;
	}
	
	public void execute() {
		drive.arcadeDrive(0, 0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}