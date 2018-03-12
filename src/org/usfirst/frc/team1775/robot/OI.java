package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.FlippyCube;
import org.usfirst.frc.team1775.robot.commands.IntakeIn;
import org.usfirst.frc.team1775.robot.commands.IntakeLift;
import org.usfirst.frc.team1775.robot.commands.IntakeOut;
import org.usfirst.frc.team1775.robot.commands.LiftOffLimitSwitch;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;
import org.usfirst.frc.team1775.robot.commands.autonomous.AutonomousConstants;
import org.usfirst.frc.team1775.robot.commands.autonomous.DriveToAutoLineFromCenter;
import org.usfirst.frc.team1775.robot.subsystems.LiftSubsystem;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final static int DRIVER_JOYSTICK_PORT = 0;
	public final static int OPERATOR_JOYSTICK_PORT = 1;
	
	private final static int A_BUTTON = 1;
	private final static int B_BUTTON = 2;
	private final static int X_BUTTON = 3;
	private final static int Y_BUTTON = 4;
	private final static int LEFT_BUMPER = 5;
	private final static int RIGHT_BUMPER = 6;
	

	
	private final static int LEFT_ANALOG_Y_AXIS = 1;
	private final static int RIGHT_ANALOG_X_AXIS = 4;
	private final static int LEFT_TRIGGER = 2;
	private final static int RIGHT_TRIGGER = 3;
	
	private final static double CUBE_FLIP_SPEED = 1.0;
	
	private static Joystick driverJoystick;
	private static Joystick operatorJoystick;
	
	private static boolean driverJoystickConfigured = false;
	private static boolean operatorJoystickConfigured = false;
	private static boolean driverJoystickConnected = false;
	private static boolean operatorJoystickConnected = false;

	public static void init() {
		checkJoysticks();
	}
	
	public static void checkJoysticks() {
		boolean isDriverConnected = checkDriverJoystickConnected();
		boolean isOperatorConnected = checkOperatorJoystickConnected();
		
		if (isDriverConnected) {
			tryConfigureDriverJoystick();
			driverJoystickConnected = true;
		} else {
			driverJoystickConnected = false;
		}
		
		if (isOperatorConnected) {
			tryConfigureOperatorJoystick();
			operatorJoystickConnected = true;
		} else {
			operatorJoystickConnected = false;
		}
	}
	
	public static boolean isOnlyJoystick(Joystick joystick) {
		if (joystick == driverJoystick) {
			return !operatorJoystickConnected;
		} else if (joystick == operatorJoystick) {
			return !driverJoystickConnected;
		}
		return false;
	}
	
	public static double getLeftAnalogY() {
		if (driverJoystickConnected) {
			return driverJoystick.getRawAxis(LEFT_ANALOG_Y_AXIS);
		} else if (operatorJoystickConnected) {
			return operatorJoystick.getRawAxis(LEFT_ANALOG_Y_AXIS);
		}
		return 0;
	}
	
	public static double getRightAnalogX() {
		if (driverJoystickConnected) {
			return driverJoystick.getRawAxis(RIGHT_ANALOG_X_AXIS);
		} else if (operatorJoystickConnected) {
			return operatorJoystick.getRawAxis(RIGHT_ANALOG_X_AXIS);
		}
		return 0;
	}
	
	public static double getLeftTrigger() {
		if (operatorJoystickConnected) {
			return operatorJoystick.getRawAxis(LEFT_TRIGGER);
		} else if (driverJoystickConnected) {
			return driverJoystick.getRawAxis(LEFT_TRIGGER);
		}
		return 0;
	}
	
	public static double getRightTrigger() {
		if (operatorJoystickConnected) {
			return operatorJoystick.getRawAxis(RIGHT_TRIGGER);
		} else if (driverJoystickConnected) {
			return driverJoystick.getRawAxis(RIGHT_TRIGGER);
		}
		return 0;
	}
	
	private static boolean checkDriverJoystickConnected() {
		try {
			if (DriverStation.getInstance().getJoystickType(DRIVER_JOYSTICK_PORT) >= 0) {
				if (!driverJoystickConnected) {
					System.out.println("Driver joystick connected!");
				}
				return true;
			}
		} catch (Exception e) { /* Do nothing */ }
		
		if (driverJoystickConnected) {
			System.err.println("Driver joystick NOT connected!");
		}
		return false;
	}
	
	private static boolean checkOperatorJoystickConnected() {
		try {
			if (DriverStation.getInstance().getJoystickType(OPERATOR_JOYSTICK_PORT) >= 0) {
				if (!operatorJoystickConnected) {
					System.out.println("Operator joystick connected!");
				}
				return true;
			}
		} catch (Exception e) { /* Do nothing */ }

		if (operatorJoystickConnected) {
			System.err.println("Operator joystick NOT connected!");
		}
		return false;
	}
	
	private static void tryConfigureDriverJoystick() {
		if (driverJoystickConfigured) {
			return;
		}
		driverJoystick = new Joystick(DRIVER_JOYSTICK_PORT);
		
		//configureIntakeInButton(driverJoystick);
		//configureIntakeOutButton(driverJoystick);
		configureIntakeUpButton(driverJoystick);
		configureIntakeDownButton(driverJoystick);
		configureCubeFlipRightButton(driverJoystick);
		configureCubeFlipLeftButton(driverJoystick);
		
		JoystickButton b = new JoystickButton(driverJoystick, 7);
		b.whenPressed(new DriveToAutoLineFromCenter(AutonomousConstants.RIGHT));
		
		driverJoystickConfigured = true;
	}
	
	private static void tryConfigureOperatorJoystick() {
		if (operatorJoystickConfigured) {
			return;
		}
		operatorJoystick = new Joystick(OPERATOR_JOYSTICK_PORT);
		
		configureIntakeInButton(operatorJoystick);
		// this was set as a fallback command, but we need it to fall back to the driver joystick
		configureIntakeOutButton(operatorJoystick);
		configureIntakeUpButton(operatorJoystick);
		configureIntakeDownButton(operatorJoystick);
		configureCubeFlipRightButton(operatorJoystick);
		configureCubeFlipLeftButton(operatorJoystick);

		operatorJoystickConfigured = true;
	}
	
	private static void configureIntakeInButton(Joystick joystick) {
		JoystickButton intakeInButton = new JoystickButton(joystick, A_BUTTON);
		intakeInButton.whileHeld(new IntakeIn());
	}
	
	private static void configureIntakeOutButton(Joystick joystick) {
		configureIntakeOutButton(joystick, false);
	}
	
	private static void configureIntakeOutButton(Joystick joystick, boolean fallbackOnly) {
		JoystickButton intakeOutButton = new JoystickButton(joystick, B_BUTTON);
		IntakeOut command = new IntakeOut();
		if (fallbackOnly) {
			command.setFallback(joystick);
		}
		intakeOutButton.whileHeld(command);
	}
	
	private static void configureIntakeDownButton(Joystick joystick) {
		JoystickButton intakeDownButton = new JoystickButton(joystick, X_BUTTON);
		intakeDownButton.whenPressed(new IntakeLift(false));
	}
	
	private static void configureIntakeUpButton(Joystick joystick) {
		JoystickButton intakeUpButton = new JoystickButton(joystick, Y_BUTTON);
		intakeUpButton.whenPressed(new IntakeLift(true));
	}
	
	private static void configureCubeFlipRightButton(Joystick joystick) {
		JoystickButton cubeFlipRightButton = new JoystickButton(joystick, RIGHT_BUMPER);
		cubeFlipRightButton.whileHeld(new FlippyCube(CUBE_FLIP_SPEED));
	}
	
	private static void configureCubeFlipLeftButton(Joystick joystick) {
		JoystickButton cubeFlipLeftButton = new JoystickButton(joystick, LEFT_BUMPER);
		cubeFlipLeftButton.whileHeld(new FlippyCube(-CUBE_FLIP_SPEED));
	}
}
