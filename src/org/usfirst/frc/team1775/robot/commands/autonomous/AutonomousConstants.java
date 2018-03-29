package org.usfirst.frc.team1775.robot.commands.autonomous;

public final class AutonomousConstants{
	// direction constants
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	// length and widths are calculated with bumpers on and intake extended
	public static final int ROBOT_LENGTH = 45;
	public static final int ROBOT_WIDTH = 34;
	
	public static final int SCALE_HEIGHT = 77;
	public static final int SWITCH_HEIGHT = 40;
	
	public static final int FOOT = 12;
	public static final int BACK_WALL_TO_SCALE = 304;
	public static final int BACK_WALL_TO_SWITCH = 150;
	public static final int AUTO_LINE = 120;
	public static final int BACK_WALL_TO_FAR_SIDE_SWITCH = 196;
	// SWITCH_BUFFER is for picking up a block from the back side of the switch
	public static final int SWITCH_BUFFER = 5;
	// BLOCK_TO_CENTER_BUFFER is getting extra length to put a block on the switch from the center
	public static final int BLOCK_TO_CENTER_BUFFER = 21;
	
	// this value is untested!!!
	public static final int BLOCK_SWITCH_ANGLE = 120;
	
	public static final double AUTO_START_INTAKE_SPEED = 0.7;
	public static final double INTAKE_SPEED = 0.9;
}
