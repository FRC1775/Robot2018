package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpToScale extends CommandGroup {
	public PickUpToScale(int direction) {
		addSequential (new DriveDistance (-7));
		//we want to be in line with the scale so we can turn and place a block on the scale. Don't know if this is the right distance though
		addSequential (new RotateToAngle (direction*135));
		addSequential (new DriveDistance (10));
		//this is meant to bring the robot to be even with the switch but we haven't done the math soit's wrong
		addSequential (new DropBlock(-direction, 75));
		//THis lifts to 6 ft 3 inches, because the max scale height is 6 ft 
		//  It might need to be changed, because if the scale isn't at max height early on in the match, then this might be too much
	}
}