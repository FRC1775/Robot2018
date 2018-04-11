package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;
import org.usfirst.frc.team1775.robot.commands.autonomous.AutonomousConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnScaleFromSidesIfScaleIsOnOtherSide extends CommandGroup {

    public BlockOnScaleFromSidesIfScaleIsOnOtherSide(int direction) {
    	// direction should be 1 or -1
    	
    	// From either the left or right starting position, with outside edge of robot
    	// aligned with the scale, drive so that front edge of robot is  aligned
    	// with back edge of scale.
    	
    	addSequential(new DriveDistance(AutonomousConstants.BACK_WALL_TO_SCALE - AutonomousConstants.ROBOT_LENGTH));
    	addSequential(new RotateToAngle(direction*90));
    	addSequential(new DriveDistance(AutonomousConstants.LENGTH_OF_SCALE));
    	addSequential(new DropBlock(AutonomousConstants.SCALE_HEIGHT, -direction));

    }
}
