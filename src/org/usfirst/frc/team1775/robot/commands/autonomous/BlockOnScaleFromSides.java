package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.FlippyCube;
import org.usfirst.frc.team1775.robot.commands.LiftHeight;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlockOnScaleFromSides extends CommandGroup {

    public BlockOnScaleFromSides(double direction) {
    	// direction should be 1 or -1
    	
    	// From either the left or right starting position, with outside edge of robot
    	// aligned with the slanted corner, drive so that back edge of robot is  aligned
    	// with back edge of scale.
    	addSequential(new DriveDistance(324));
    	// The Scale has a max height of 6 ft (72 in), with the plate wall at 3.5 in. We 
    	// added 1.5 inches, just to be safe |:-)
    	addSequential(new LiftHeight(77));
    	addSequential(new FlippyCube(direction));
    }
}
