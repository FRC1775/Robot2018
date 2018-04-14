package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnSwitchFromCenter extends CommandGroup {

	// For this command, we line the robot up so that it could drive straight forward to the switch 
	// without hitting the pyramid of cubes
    public BlockOnSwitchFromCenter() {
    	addSequential(new AutonomousStart());
    	addSequential(new DriveDistance((AutonomousConstants.AUTO_LINE - AutonomousConstants.ROBOT_LENGTH) 
    			+ AutonomousConstants.BLOCK_TO_CENTER_BUFFER));
    	addSequential(new RotateToAngle(90), 1.5);
    	addSequential(new DropBlock(AutonomousConstants.SWITCH_HEIGHT, -1));
    }
}
