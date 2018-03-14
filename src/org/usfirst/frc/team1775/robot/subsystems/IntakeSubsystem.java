package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	private static final double IN_SPEED = 0.7;
	private static final double OUT_SPEED = -0.9;
	
	public void runIn() {
		setSpeed(IN_SPEED);
	}
	
	public void runOut() {
		setSpeed(OUT_SPEED);
	}
	
	public void stop() {
		setSpeed(0);
	}

	public void setSpeed(double speed) {
		RobotMap.intakeMotorController1.set(speed);
		RobotMap.intakeMotorController2.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
