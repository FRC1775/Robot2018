package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Lift;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	public void initDefaultCommand() {
		setDefaultCommand(new Lift());

	}

	public void setSpeed(double speed) {
		RobotMap.liftMotorController1.set(speed);
		//RobotMap.liftMotorController2.set(speed * 0.05);
	}
}