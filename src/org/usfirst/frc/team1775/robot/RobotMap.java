package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Encoder driveEncoder;
	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drive;
	public static Talon intakeMotorController1;
	public static Talon intakeMotorController2;
	public static Talon liftMotorController1;
	public static Talon liftMotorController2;
	
	public static void init(){
		driveEncoder = new Encoder(3, 2, false, Encoder.EncodingType.k1X);
		double distancePerPulse = ((3.19*Math.PI)/250.0);
		driveEncoder.setDistancePerPulse(distancePerPulse);
		
	    leftDriveMotorController = new Talon(1);
	    leftDriveMotorController.setInverted(true);
	    rightDriveMotorController = new Talon(2);
	    rightDriveMotorController.setInverted(true);
	    intakeMotorController1 = new Talon(3);
	    intakeMotorController2 = new Talon(4);
	    intakeMotorController2.setInverted(true);
	    liftMotorController1 = new Talon(5);
	    liftMotorController2 = new Talon(6);
	    drive = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
	}
}
