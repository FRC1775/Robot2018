package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;
import org.usfirst.frc.team1775.robot.commands.RotateWhileDrivingDistance;
import org.usfirst.frc.team1775.robot.commands.RotateWhileDrivingTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnLeftSwitchFromCenter extends CommandGroup {
	
	public BlockOnLeftSwitchFromCenter() {
	/*	addSequential(new RotateWhileDrivingDistance(0, 0));
//		addParallel(new RotateWhileDrivingTimed(0.4, -45));
		addParallel(new RotateWhileDrivingTimed(8, -45)); // Return the straight drive setpoint to 0
		addSequential(new DriveDistance(45));
//		addSequential(new RotateToAngle(15));
//		addParallel(new RotateWhileDrivingTimed(0.5, -45));
		addParallel(new RotateWhileDrivingDistance(14, -60));
		addSequential(new DriveDistance(69));
		addSequential(new RotateWhileDrivingDistance(0, 0));  */
		
		addSequential(new RotateWhileDrivingDistance(0, 0, true));
		addParallel(new RotateWhileDrivingDistance(14, -45, true));
		addParallel(new RotateWhileDrivingDistance(116, -90, true));
		addSequential(new DriveDistance(127));
		addSequential(new RotateWhileDrivingDistance(0, 0, false));
	}
}

