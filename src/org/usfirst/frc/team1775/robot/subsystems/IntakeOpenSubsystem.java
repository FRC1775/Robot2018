package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.OpenIntakeArms;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeOpenSubsystem extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new OpenIntakeArms());
	}
}
