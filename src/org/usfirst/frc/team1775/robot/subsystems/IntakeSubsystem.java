package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.IntakeIn;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	private static final double IN_SPEED = 0.9;
	private static final double OUT_SPEED = -0.9;

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeIn());

	}
	
	public void runIn() {
		setSpeed(IN_SPEED);
	}
	
	public void runOut() {
		setSpeed(OUT_SPEED);
	}

	public void setSpeed(double speed) {
		RobotMap.intakeMotorController1.set(speed);
		RobotMap.intakeMotorController2.set(speed);
	}
}