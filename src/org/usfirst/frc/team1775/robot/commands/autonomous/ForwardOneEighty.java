package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardOneEighty extends CommandGroup{
	public ForwardOneEighty() {
		addSequential(new AutonomousStart());
		addSequential(new DriveDistance(5));
		addSequential(new RotateToAngle(180));
	}
}
