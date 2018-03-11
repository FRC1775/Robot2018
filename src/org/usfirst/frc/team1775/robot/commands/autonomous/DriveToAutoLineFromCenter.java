package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToAutoLineFromCenter extends CommandGroup {

    public DriveToAutoLineFromCenter(int direction) {
    	addSequential(new AutonomousStart());
    	addSequential(new DriveDistance(AutonomousConstants.FOOT));
    	addSequential(new RotateToAngle(direction*90));
    	addSequential(new DriveDistance(AutonomousConstants.CENTER_TO_EDGE));
    	addSequential(new RotateToAngle(direction*-90));
    	addSequential(new DriveDistance(AutonomousConstants.AUTO_LINE));
    }
}
