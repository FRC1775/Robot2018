package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Lift;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	private static final double START_RAMP_TIME_MS = 500.0;
	private static final double MAX_SPEED = 0.6;
	private static final double LIFT_SPEED_DEADBAND = 0.1;

	private double startTime = System.currentTimeMillis();
	
	public void initDefaultCommand() {
		setDefaultCommand(new Lift());
	}

	public void setSpeed(double speed) {
		double outputSpeed = 0;

		if (isAllowedToGoUp(speed)) {
			outputSpeed = speed * getRampAndMaxSpeedMultiplier();
		} else if (isAllowedToGoDown(speed)) {
			outputSpeed = speed * getRampAndMaxSpeedMultiplier();
		} else {
			outputSpeed = 0;
			startTime = System.currentTimeMillis();
		}
		
		RobotMap.liftMotorController1.set(outputSpeed); // Must call set, not setSpeed, to take into account the setInverted on the controller
	}
	
	private boolean isAllowedToGoUp(double inputLiftSpeed) {
		return inputLiftSpeed > LIFT_SPEED_DEADBAND && RobotMap.liftTopLimitSwitch.get();
	}
	
	private boolean isAllowedToGoDown(double inputLiftSpeed) {
		return inputLiftSpeed < -LIFT_SPEED_DEADBAND && RobotMap.liftBottomLimitSwitch.get();
	}
	
	private double getRampAndMaxSpeedMultiplier() {
		return Math.min((System.currentTimeMillis() - startTime) / START_RAMP_TIME_MS, 1) * MAX_SPEED;
	}
}