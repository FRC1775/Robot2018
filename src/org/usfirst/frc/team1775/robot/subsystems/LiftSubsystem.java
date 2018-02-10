package org.usfirst.frc.team1775.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;
import org.usfirst.frc.team1775.robot.commands.Intake;
import org.usfirst.frc.team1775.robot.commands.Lift;
import org.usfirst.frc.team1775.robot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {

	public void initDefaultCommand() {
		setDefaultCommand(new Lift());

	}

	public void setSpeed(double speed) {
		RobotMap.intakeMotorController1.set(speed);
		RobotMap.intakeMotorController2.set(speed);
	}
}