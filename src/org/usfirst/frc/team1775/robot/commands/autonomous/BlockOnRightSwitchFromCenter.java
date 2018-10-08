package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateWhileDrivingDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnRightSwitchFromCenter extends CommandGroup {
	
	// Line the robot up with the edge of the exchange closest to the center
	public BlockOnRightSwitchFromCenter() {
		
		
		addSequential(new AutonomousStart());
		addSequential(new RotateWhileDrivingDistance(0, 0, false));
		addParallel(new RotateWhileDrivingDistance(AutonomousConstants.CENTER_SWITCH_FIRST_LEG, 45, false));
		addParallel(new RotateWhileDrivingDistance(AutonomousConstants.CENTER_SWITCH_SECOND_LEG_RIGHT, 90, true));
		addSequential(new DriveDistance(AutonomousConstants.CENTER_SWITCH_TOTAL_DISTANCE));
		addSequential(new RotateWhileDrivingDistance(0, 0, false));
		addSequential(new DriveDistance(-16));
		addSequential(new DropBlock(AutonomousConstants.SWITCH_HEIGHT, -1));
	}
}
