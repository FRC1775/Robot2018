package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Talon motorController;
	public static Talon motorController2;
	public static DifferentialDrive drive;
	public static Talon motorController3;
	public static Talon motorController4;
	
	public static void init(){
	    motorController = new Talon(1);
	    motorController.setInverted(true);
	    motorController2 = new Talon(2);
	    motorController2.setInverted(true);
	    motorController3 = new Talon(3);
	    motorController4 = new Talon(4);
	    drive = new DifferentialDrive(motorController, motorController2);
	}
}
