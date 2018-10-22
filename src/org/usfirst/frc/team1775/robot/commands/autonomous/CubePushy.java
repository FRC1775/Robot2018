package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CubePushy extends CommandGroup{
	public CubePushy() {
		addSequential(new AutonomousStart());
		addSequential(new DriveDistance(12));
		addSequential(new OpenArms(true), 1);
		addSequential(new OpenArms(false));
	}
}