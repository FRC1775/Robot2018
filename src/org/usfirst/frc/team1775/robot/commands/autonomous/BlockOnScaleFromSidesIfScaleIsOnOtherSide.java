package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;
import org.usfirst.frc.team1775.robot.commands.autonomous.AutonomousConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnScaleFromSidesIfScaleIsOnOtherSide extends CommandGroup {

    public BlockOnScaleFromSidesIfScaleIsOnOtherSide(int direction) {
    	
    	// Left or right starting position, with outside edge of robot
    	// aligned with the slanted corner
    	addSequential(new AutonomousStart());
    	addSequential(new DriveDistance(AutonomousConstants.BACK_WALL_TO_FAR_SIDE_SWITCH + AutonomousConstants.FOOT + 6));
    	addSequential(new RotateToAngle(direction*90), 1);
    	addSequential(new DriveDistance(AutonomousConstants.LENGTH_OF_SCALE - 5));
//    	addSequential(new RotateToAngle(-direction*90), 1);
//    	addSequential(new DriveDistance(AutonomousConstants.SWITCH_TO_SCALE_MINUS_A_FOOT));

    	// check the sign on the direction here
//    	addSequential(new DropBlock(AutonomousConstants.SCALE_HEIGHT, direction));

    }
}
