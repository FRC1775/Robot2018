package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;
import org.usfirst.frc.team1775.robot.commands.Drive;
import org.usfirst.frc.team1775.robot.commands.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	
	public void drive (double xSpeed, double zRotation) {
	    driver.arcadeDrive(moveValue, rotateValue);
	}
}