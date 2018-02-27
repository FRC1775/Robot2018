package org.usfirst.frc.team1775.robot;

import java.lang.invoke.SwitchPoint;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
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
	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drive;
	public static Talon intakeMotorController1;
	public static Talon intakeMotorController2;
	public static Talon liftMotorController1;
	public static DigitalInput liftBottomLimitSwitch;
	public static DigitalInput liftTopLimitSwitch;
	public static AHRS gyro;
	public static Compressor compressor;
	public static Solenoid intakeLiftUp;
	public static Solenoid intakeLiftDown;
	public static Servo leftIntakeRelease;
	public static Servo rightIntakeRelease;
	
	public static void init(){
		double distancePerPulse = ((6*Math.PI)/250.0);
		
		driveEncoderLeft = new Encoder(2, 3, false, Encoder.EncodingType.k1X);
		driveEncoderLeft.setDistancePerPulse(distancePerPulse);

		driveEncoderRight = new Encoder(4, 5, false, Encoder.EncodingType.k1X);
		driveEncoderRight.setDistancePerPulse(distancePerPulse);
		
		gyro = new AHRS(SPI.Port.kMXP);
		gyro.reset();
		
		compressor = new Compressor();
		intakeLiftUp = new Solenoid(0);
		intakeLiftDown = new Solenoid(1);
		
		leftIntakeRelease = new Servo(8);
		rightIntakeRelease = new Servo(9);
		
	    leftDriveMotorController = new Talon(0);
	    rightDriveMotorController = new Talon(1);
	    intakeMotorController1 = new Talon(3);
	    intakeMotorController2 = new Talon(4);
	    intakeMotorController2.setInverted(true);
	    liftMotorController1 = new Talon(5);
	    liftMotorController1.setInverted(true);
	    liftBottomLimitSwitch = new DigitalInput(0);
	    liftTopLimitSwitch = new DigitalInput(1);
	    drive = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
	}
}
