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
    	addSequential(new DriveDistance(12));
    	//Turning
    	addSequential(new RotateToAngle(direction*90));
    	//Brings robot back end to the edge of switch, plus an extra 3 inches
    	addSequential(new DriveDistance(95));
    	addSequential(new RotateToAngle(-direction*90));
    	// Brings back end of robot to level with switch
    	addSequential(new DriveDistance(148));
    	addSequential(new DropBlock(20, -direction));
    }
}
