package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.FlippyCube;
import org.usfirst.frc.team1775.robot.commands.LiftHeight;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlockOnSwitchFromSides extends CommandGroup {

    public BlockOnSwitchFromSides(double direction) {
    	// direction should be 1 or -1
    	
    	// From either the left or right starting position, with outside edge of robot
    	// aligned with the slanted corner, drive so that back edge of robot is  aligned
    	// with back edge of switch.
    	addSequential(new DriveDistance(150));
    	
    	// The switch has a height of 18.75 in
    	addSequential(new DropBlock(20, direction));
    }
}
