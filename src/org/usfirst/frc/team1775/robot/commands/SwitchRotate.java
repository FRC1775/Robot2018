package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SwitchRotate extends InstantCommand{

	public SwitchRotate() {
		Robot.motorSubsystem.switchRotate();
	}
}