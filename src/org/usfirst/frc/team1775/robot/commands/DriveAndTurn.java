package org.usfirst.frc.team1775.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndTurn extends CommandGroup {

	public DriveAndTurn() {
		addParallel(new RotateWhileDriving(0.5, 8.8));
		addSequential(new DriveDistance(140));
		addSequential(new RotateWhileDriving(0, 0)); // Return the straight drive setpoint to 0
	}
	
}

//8.8 degrees after 10 inches 