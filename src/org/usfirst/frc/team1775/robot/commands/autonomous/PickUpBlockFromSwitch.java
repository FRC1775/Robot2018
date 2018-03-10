package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.IntakeIn;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpBlockFromSwitch extends CommandGroup {
	public PickUpBlockFromSwitch(int direction) {
		//150in from back
		//192in is far side of switch to back wall 
		addSequential (new DriveDistance(46));
		addSequential (new RotateToAngle(-direction*135));
		addParallel (new DriveDistance (5));
		addParallel (new IntakeIn ());
		//We don't know how to stop this. Will it just run forever? who knows?
	}
}
