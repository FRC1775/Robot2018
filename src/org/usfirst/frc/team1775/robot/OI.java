package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1775.robot.commands.CloseIntakeArms;
import org.usfirst.frc.team1775.robot.commands.FlippyCube;
import org.usfirst.frc.team1775.robot.commands.IntakeIn;
import org.usfirst.frc.team1775.robot.commands.IntakeOut;
import org.usfirst.frc.team1775.robot.commands.LiftToHerdHeight;
import org.usfirst.frc.team1775.robot.commands.OpenIntakeArms;
import org.usfirst.frc.team1775.robot.commands.autonomous.BlockOnLeftSwitchFromCenter;
import org.usfirst.frc.team1775.robot.commands.autonomous.DriveAndTurn;

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
	private final static int BACK_BUTTON = 7;
	private final static int START_BUTTON = 8;
	private final static int LEFT_JOYSTICK_CLICK = 9;
	private final static int RIGHT_JOYSTICK_CLICK = 10;
	
	private final static int LEFT_ANALOG_Y_AXIS = 1;
	private final static int RIGHT_ANALOG_X_AXIS = 4;
	private final static int LEFT_TRIGGER = 2;
	private final static int RIGHT_TRIGGER = 3;
	
	private final static double CUBE_FLIP_SPEED = 1.0;
	public final static double INTAKE_SPEED = 0.9;
	
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
	
	public static int getPOVDirection() {
		if (operatorJoystickConnected) {
			return operatorJoystick.getPOV(0);
		}else if (driverJoystickConnected) {
			return driverJoystick.getPOV(0);
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
		configureIntakeOutButton(driverJoystick, true);
		configureCubeFlipRightButton(driverJoystick);
		configureCubeFlipLeftButton(driverJoystick);
//		configureDriveAndTurnTestButton(driverJoystick);
		
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
//		configureIntakeOpenButton(operatorJoystick);
		configureCubeFlipRightButton(operatorJoystick);
		configureCubeFlipLeftButton(operatorJoystick); 

		operatorJoystickConfigured = true;
	}
	
	private static void configureIntakeInButton(Joystick joystick) {
		JoystickButton intakeInButton = new JoystickButton(joystick, A_BUTTON);
		intakeInButton.whileHeld(new IntakeIn(INTAKE_SPEED));
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
	
	
	// to open the intake arms with the X button; the command OpenIntakeArms will have to be changed
	
//	private static void configureIntakeOpenButton(Joystick joystick) {
//		JoystickButton intakeOpenButton = new JoystickButton(joystick, X_BUTTON);
//		intakeOpenButton.whileHeld(new OpenIntakeArms(true));
//		intakeOpenButton.whenReleased(new OpenIntakeArms(false));
//	}
	
	private static void configureCubeFlipRightButton(Joystick joystick) {
		JoystickButton cubeFlipRightButton = new JoystickButton(joystick, RIGHT_BUMPER);
		cubeFlipRightButton.whileHeld(new FlippyCube(CUBE_FLIP_SPEED));
	}
	
	private static void configureCubeFlipLeftButton(Joystick joystick) {
		JoystickButton cubeFlipLeftButton = new JoystickButton(joystick, LEFT_BUMPER);
		cubeFlipLeftButton.whileHeld(new FlippyCube(-CUBE_FLIP_SPEED));
	}
	
	/*private static void configureDriveAndTurnTestButton(Joystick joystick) {
		JoystickButton driveAndTurnTestButton = new JoystickButton(joystick, START_BUTTON);
		driveAndTurnTestButton.whenPressed(new BlockOnLeftSwitchFromCenter());
	}*/
}
