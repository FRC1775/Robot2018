package org.usfirst.frc.team1775.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
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
	public static Encoder liftEncoder;
	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drive;
	public static Talon intakeMotorController1;
	public static Talon intakeMotorController2;
	public static Talon liftMotorController1;
	public static DigitalInput liftBottomLimitSwitch;
	public static DigitalInput liftTopLimitSwitch;
	public static Talon cubeFlip; 
	public static AHRS gyro;
	public static Compressor compressor;
	public static Solenoid intakeLiftUp;
	public static Solenoid intakeLiftDown;
	public static Servo leftIntakeRelease;
	public static Servo rightIntakeRelease;
	public static Solenoid liftBrake;
	public static Solenoid liftUnbrake;
	public static DigitalInput cubeInRobot;
	
	public static DigitalOutput pinZero;
	public static DigitalOutput pinOne;
	public static DigitalOutput pinTwo;
	public static DigitalOutput pinThree;
	
	public static void init(){
		double distancePerPulse = ((6*Math.PI)/250.0);
		double liftDistancePerPulse = ((1.375*Math.PI)/250.0);
		
		driveEncoderLeft = new Encoder(2, 3, false, Encoder.EncodingType.k1X);
		driveEncoderLeft.setDistancePerPulse(distancePerPulse);

		driveEncoderRight = new Encoder(4, 5, false, Encoder.EncodingType.k1X);
		driveEncoderRight.setDistancePerPulse(distancePerPulse);
		
		liftEncoder = new Encoder(6, 7, false, Encoder.EncodingType.k1X);
		liftEncoder.setDistancePerPulse(liftDistancePerPulse);
		
		gyro = new AHRS(SPI.Port.kMXP);
		gyro.reset();
		
		compressor = new Compressor();
		intakeLiftUp = new Solenoid(0);
		intakeLiftDown = new Solenoid(1);
		
		leftIntakeRelease = new Servo(8);
		rightIntakeRelease = new Servo(9);
		
	    leftDriveMotorController = new Talon(0);
	    rightDriveMotorController = new Talon(1);
	    // left intake
	    intakeMotorController1 = new Talon(3);
	    // right intake
	    intakeMotorController2 = new Talon(4);
	    intakeMotorController2.setInverted(true);
	    liftMotorController1 = new Talon(2);
	    liftMotorController1.setInverted(true);
	    liftBottomLimitSwitch = new DigitalInput(0);
	    liftTopLimitSwitch = new DigitalInput(1);
	    drive = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
	    
	    cubeFlip = new Talon(5);

	    liftBrake = new Solenoid(2);
	    liftUnbrake = new Solenoid(3);
	    
	    cubeInRobot = new DigitalInput(8); 
	    
	    pinZero = new DigitalOutput(10);
	    pinOne = new DigitalOutput(11);
	    pinTwo = new DigitalOutput(12);
	    pinThree = new DigitalOutput(13); //sends information about cube in robot
	}
}
