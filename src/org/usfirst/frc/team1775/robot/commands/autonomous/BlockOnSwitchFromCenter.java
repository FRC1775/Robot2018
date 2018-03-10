package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlockOnSwitchFromCenter extends CommandGroup {

    public BlockOnSwitchFromCenter(int direction) {
    	//One foot turning space
    	addSequential(new DriveDistance(AutonomousConstants.FOOT));
    	//Turning
    	addSequential(new RotateToAngle(direction*90));
    	//Brings robot back end to the edge of switch, plus an extra 3 inches
    	addSequential(new DriveDistance(AutonomousConstants.CENTER_TO_SWITCH));
    	addSequential(new RotateToAngle(-direction*90));
    	// Brings back end of robot to level with switch
    	addSequential(new DriveDistance(AutonomousConstants.BACK_WALL_TO_SWITCH - AutonomousConstants.FOOT));
    	addSequential(new DropBlock(AutonomousConstants.SWITCH_HEIGHT, -direction));
    }
}