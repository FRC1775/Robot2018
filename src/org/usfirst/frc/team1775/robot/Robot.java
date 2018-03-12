
package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1775.robot.commands.autonomous.AutonomousConstants;
import org.usfirst.frc.team1775.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team1775.robot.commands.autonomous.DriveToAutoLineFromCenter;
import org.usfirst.frc.team1775.robot.subsystems.ExampleSubsystem;
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

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static MotorSubsystem motorSubsystem;
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static LiftSubsystem liftSubsystem;
    
	//public static DriverCamera camera;
	
    Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		liftSubsystem = new LiftSubsystem();
		OI.init();
		motorSubsystem = new MotorSubsystem();
					// choosetype name = new type(arguments);r.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData(motorSubsystem);
		LiveWindow.add(motorSubsystem);
		initCamera();
		initDashboard();
	}
	
	private void initDashboard() {
		
		chooser.addDefault("Do Nothing", new DoNothing(RobotMap.drive));
		chooser.addObject("Cross Auto Line From Center by Going Left", new DriveToAutoLineFromCenter(AutonomousConstants.LEFT));
		chooser.addObject("Cross Auto Line From Center by Going Right", new DriveToAutoLineFromCenter(AutonomousConstants.RIGHT));
		/**
		 * choser.addObject("Place Block on Switch and Scale from Left", new SwitchScaleLogic(AutonomousConstants.LEFT));
		 * choser.addObject("Place Block on Switch and Scale from Right", new SwitchScaleLogic(AutonomousConstants.RIGHT));
		 * choser.addObject("Place Block on Switch Only", );
		 * choser.addObject("Place Block on Scale Only", );
		 * choser.addObject("Cross Auto Line and Stay Out of the Way", );
		 */
	}
	
	private void initCamera() {
		//cameras = new Cameras();
		//cameras.init();
//		camera = new DriverCamera();
//		camera.init();
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
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
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
		RobotMap.driveEncoderLeft.reset();
		RobotMap.liftEncoder.reset();
		RobotMap.gyro.reset();
		RobotMap.gyro.zeroYaw();
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
