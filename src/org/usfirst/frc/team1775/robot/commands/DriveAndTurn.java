package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndTurn extends CommandGroup {

	public DriveAndTurn() {
		addSequential(new DriveDistance(40));
		addParallel(new RotateWhileDriving(15));
		addSequential(new DriveDistance(130));
	}
	
}

//8.8 degrees after 10 inches 