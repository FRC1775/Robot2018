package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.commands.SetMotorSpeed;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	
	public void drive (double moveValue, double rotateValue) {
	    driver.arcadeDrive(moveValue, rotateValue);
	}
}