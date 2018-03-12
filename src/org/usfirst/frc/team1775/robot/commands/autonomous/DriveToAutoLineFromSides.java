package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToAutoLineFromSides extends CommandGroup {

    public DriveToAutoLineFromSides() {
    	
    	addSequential(new DriveDistance(AutonomousConstants.AUTO_LINE));	
    }
}