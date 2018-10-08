
package org.usfirst.frc.team1775.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
// import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1775.robot.commands.autonomous.BlockOnLeftSwitchFromCenter;
import org.usfirst.frc.team1775.robot.commands.autonomous.BlockOnSwitchFromCenterCurve;
import org.usfirst.frc.team1775.robot.commands.autonomous.DetermineAuto;
import org.usfirst.frc.team1775.robot.commands.autonomous.DetermineAutoCenter;
import org.usfirst.frc.team1775.robot.commands.autonomous.DetermineAutoOppositeSwitch;
import org.usfirst.frc.team1775.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team1775.robot.commands.autonomous.DriveToAutoLineFromCenter;
import org.usfirst.frc.team1775.robot.commands.autonomous.DriveToAutoLineFromSides;
import org.usfirst.frc.team1775.robot.subsystems.BlinkyLightSubsystem;
import org.usfirst.frc.team1775.robot.subsystems.IntakeOpenSubsystem;
import org.usfirst.frc.team1775.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1775.robot.subsystems.MotorSubsystem;
import org.usfirst.frc.team1775.robot.subsystems.LiftSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static MotorSubsystem motorSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static LiftSubsystem liftSubsystem;
	public static BlinkyLightSubsystem blinkyLightSubsystem;
	public static IntakeOpenSubsystem intakeOpenSubsystem;
    
	public static UsbCamera driverCamera;
	
    Command autonomousCommand;
	private static SendableChooser<Command> chooser = new SendableChooser<>();
	private static SendableChooser<RobotStartingPosition> positionChooser = new SendableChooser<>();
	private static SendableChooser<PrioritizeSwitchScale> priorityChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		liftSubsystem = new LiftSubsystem();
		motorSubsystem = new MotorSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		blinkyLightSubsystem = new BlinkyLightSubsystem();
		intakeOpenSubsystem = new IntakeOpenSubsystem();
		OI.init();
		
		LiveWindow.add(liftSubsystem);
		initCamera(); //-- No camera for the first competition
		initDashboard();
	}
	
	private void initDashboard() {
		chooser.addDefault("Cross Auto line", new DriveToAutoLineFromCenter());
		chooser.addObject("Pick Best Way to Go from a Side", new DetermineAuto());
		chooser.addObject("Pick Best Way from a Side with Opposite Scale Potential", new DetermineAutoOppositeSwitch());
		chooser.addObject("Put a Block on the Switch From Center", new BlockOnLeftSwitchFromCenter());
		chooser.addObject("Cross Auto Line From Center (Drive Straight Forward)", new DriveToAutoLineFromCenter());
		chooser.addObject("Drive to Auto Line From Sides", new DriveToAutoLineFromSides());
		chooser.addObject("Forward 180", new ForwardOneEighty());
		SmartDashboard.putData("Auto mode", chooser);
		
		positionChooser.addDefault("Center", RobotStartingPosition.CENTER);
		positionChooser.addDefault("Left", RobotStartingPosition.LEFT);
		positionChooser.addDefault("Right", RobotStartingPosition.RIGHT);
		SmartDashboard.putData("Robot starting position", positionChooser);
		
		priorityChooser.addDefault("Switch", PrioritizeSwitchScale.SWITCH);
		priorityChooser.addDefault("Scale", PrioritizeSwitchScale.SCALE);
		SmartDashboard.putData("Prioritize", priorityChooser);
	}
	
	
	private void initCamera() {
		driverCamera = CameraServer.getInstance().startAutomaticCapture();
		//driverCamera.setResolution(320, 180);
		driverCamera.setFPS(30);
		//driverCamera.getProperty("focus_auto").set(1);
		
	}
	

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		OI.checkJoysticks();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	
	public final static String checkFMS() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData == null) {
			return "";
		}
		return gameData;
	}
	
	public static RobotStartingPosition getRobotStartingPosition() {
		return positionChooser.getSelected();
	}
	
	public static PrioritizeSwitchScale getPriority() {
		return priorityChooser.getSelected();
	}
	
	
	@Override
	public void autonomousInit() {
		
		// can put NULL in the FMS to test
//		gameData = ("NULL".equalsIgnoreCase(gameData)) ? null : gameData;
		autonomousCommand = chooser.getSelected();
		
		
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		System.out.println(autonomousCommand.getName());
		checkFMS();
//		RobotMap.gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		OI.checkJoysticks();
		RobotMap.driveEncoderLeft.reset();
		RobotMap.driveEncoderRight.reset();
		RobotMap.gyro.reset();
		RobotMap.gyro.zeroYaw();
		
		// Scheduler.getInstance().add(new LiftOffLimitSwitch());
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		OI.checkJoysticks();
		Scheduler.getInstance().run();
	}
}
