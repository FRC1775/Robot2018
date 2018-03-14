package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToAutoLineFromCenter extends CommandGroup {

    public DriveToAutoLineFromCenter() {
    	addSequential(new AutonomousStart());
    	addSequential(new DriveDistance((AutonomousConstants.AUTO_LINE - AutonomousConstants.ROBOT_LENGTH) + 12));
    }
}
