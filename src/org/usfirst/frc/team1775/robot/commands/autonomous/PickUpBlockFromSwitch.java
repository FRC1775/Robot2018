package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.OI;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.IntakeIn;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpBlockFromSwitch extends CommandGroup {
	public PickUpBlockFromSwitch(int direction) {
		addSequential (new DriveDistance((AutonomousConstants.BACK_WALL_TO_FAR_SIDE_SWITCH
				- AutonomousConstants.BACK_WALL_TO_SWITCH) + AutonomousConstants.SWITCH_BUFFER));
		addSequential (new RotateToAngle(direction*AutonomousConstants.BLOCK_SWITCH_ANGLE));
		addParallel (new IntakeIn (OI.INTAKE_SPEED));
		addSequential (new DriveDistance (AutonomousConstants.SWITCH_BUFFER));
		if(!RobotMap.cubeInRobot.get()) {
			Robot.intakeSubsystem.stop();		
		}
		// This is meant to stop the intake once it acquires a cube
		// I'm worried that it will stop too early, so maybe adding a delay would be good
	}
}
