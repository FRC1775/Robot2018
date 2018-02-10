package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1775.robot.commands.DriveDistance;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick myJoystick;
	public static Joystick myJoystick2;

	public void init() {
		myJoystick = new Joystick(0);
		myJoystick2 = new Joystick(1);
		JoystickButton aButton = new JoystickButton(myJoystick, 1);
		aButton.whenPressed(new DriveDistance(30));
	}
}