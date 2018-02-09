package org.usfirst.frc.team1775.robot.subsystems;

import org.usfirst.frc.team1775.robot.commands.Drive;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	
	public void drive (double xSpeed, double zRotation) {
	    RobotMap.drive.arcadeDrive(-xSpeed, zRotation, true);
	    // the "true" as the third parameter makes a "ramp up" on the speed,
	    // so the driving is less jerky
	}
}