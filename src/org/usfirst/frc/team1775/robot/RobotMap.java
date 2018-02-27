package org.usfirst.frc.team1775.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Encoder driveEncoderLeft;
	public static Encoder driveEncoderRight;
	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drive;
	public static Talon intakeMotorController1;
	public static Talon intakeMotorController2;
	public static Talon liftMotorController1;
	public static Talon liftMotorController2;
	public static DigitalInput liftBottomLimitSwitch;
	public static DigitalInput liftTopLimitSwitch;
	public static AHRS gyro;
	public static Compressor compressor;
	public static Solenoid solenoid;
 	
	public static void init(){
		double distancePerPulse = ((3.19*Math.PI)/250.0);
 		
	    leftDriveMotorController = new Talon(1);
	    leftDriveMotorController.setInverted(true);
	    rightDriveMotorController = new Talon(2);
	    rightDriveMotorController.setInverted(true);
		
		driveEncoderLeft = new Encoder(2, 3, false, Encoder.EncodingType.k1X);
		driveEncoderLeft.setDistancePerPulse(distancePerPulse);

		driveEncoderRight = new Encoder(4, 5, false, Encoder.EncodingType.k1X);
		driveEncoderRight.setDistancePerPulse(distancePerPulse);
		
		gyro = new AHRS(SPI.Port.kMXP);
		gyro.reset();
		
//		compressor = new Compressor();
//		solenoid = new Solenoid(0);
		
	    leftDriveMotorController = new Talon(0);
	    rightDriveMotorController = new Talon(1);
	    intakeMotorController1 = new Talon(3);
	    intakeMotorController2 = new Talon(4);
	    intakeMotorController2.setInverted(true);
	    liftMotorController1 = new Talon(5);
	    liftMotorController2 = new Talon(6);
	    liftMotorController1.setInverted(true);
	    liftBottomLimitSwitch = new DigitalInput(0);
	    liftTopLimitSwitch = new DigitalInput(1);
	    drive = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
	}
}
