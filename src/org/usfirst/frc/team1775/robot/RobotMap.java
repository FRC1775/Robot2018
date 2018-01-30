package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static Servo servo;
	
	public static void init() {
	    servo = new Servo(0);
	}
	public static Talon motorController;
	
	public static void init(){
	    servo = new Servo(0);
	    motorController = new Talon(0);
	    MotorController = new Talon(1);
	}
	public static robotDrive driveTrain(motorController,MotorController){
	    
	}
	
}
