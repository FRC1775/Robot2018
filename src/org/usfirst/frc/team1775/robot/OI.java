package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick driverJoystick;
	public static Joystick mechanismJoystick;

	public void init() {
		driverJoystick = new Joystick(0);
		mechanismJoystick = new Joystick(1);
		JoystickButton aButton = new JoystickButton(driverJoystick, 1);
		aButton.whenPressed(new DriveDistance(30));
		
		JoystickButton bButton = new JoystickButton(driverJoystick, 2);
		bButton.whenPressed(new RotateToAngle(40));
	}
}