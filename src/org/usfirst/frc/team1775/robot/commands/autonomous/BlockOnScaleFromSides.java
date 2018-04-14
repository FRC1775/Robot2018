package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.autonomous.AutonomousConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnScaleFromSides extends CommandGroup {

    public BlockOnScaleFromSides(int direction) {
    	
    	// Left or right starting position, with outside edge of robot
    	// aligned with the slanted corner
    	addSequential(new DriveDistance(AutonomousConstants.BACK_WALL_TO_SCALE));
    //	addSequential(new DropBlock(AutonomousConstants.SCALE_HEIGHT, direction));
    	
    }
}
