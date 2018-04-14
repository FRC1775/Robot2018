package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateWhileDrivingDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnLeftSwitchFromCenter extends CommandGroup {
	
	// Line the robot up with the edge of the exchange closest to the center
	public BlockOnLeftSwitchFromCenter() {
		
		double secondDistance;
		int direction;
		boolean reduceMotorSpeed = Robot.checkFMS().charAt(0) == 'R';
		if(Robot.checkFMS().charAt(0) == 'R') {
			direction = 1;
			secondDistance = AutonomousConstants.CENTER_SWITCH_SECOND_LEG_RIGHT;
		} else {
			direction = -1;
			secondDistance = AutonomousConstants.CENTER_SWITCH_SECOND_LEG_LEFT;
		}
		
		
		addSequential(new RotateWhileDrivingDistance(0, 0, false));
		addParallel(new RotateWhileDrivingDistance(AutonomousConstants.CENTER_SWITCH_FIRST_LEG, direction * 45, false));
		addParallel(new RotateWhileDrivingDistance(secondDistance, direction * 90, true));
		addSequential(new DriveDistance(AutonomousConstants.CENTER_SWITCH_TOTAL_DISTANCE));
		addSequential(new RotateWhileDrivingDistance(0, 0, false));
		if(reduceMotorSpeed) {
			addSequential(new DriveDistance(-16));
		}	
	}
}
