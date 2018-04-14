package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateWhileDrivingDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndTurn extends CommandGroup {
	
	public DriveAndTurn(double direction) {
		addParallel(new RotateWhileDrivingDistance(10, direction * AutonomousConstants.ANGLE_ROTATE_TO_SWITCH, false));
		addSequential(new DriveDistance(AutonomousConstants.BACK_WALL_TO_SWITCH));
		addSequential(new RotateWhileDrivingDistance(0, 0, false)); // Return the straight drive setpoint to 0
	}
}
