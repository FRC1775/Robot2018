package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class BlinkyLights extends Command{
	private static double SWITCH_FENCE_HEIGHT = 20;
	
	public BlinkyLights() {
		requires(Robot.blinkyLightSubsystem);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		setPinConfiguration(false, false, false);
	}
		
	@Override
	protected void execute() {
		checkTeleop();
		checkElevator();
		checkIntake();
		checkCubeInRobot();
		checkEndMatchOrAuton();
		super.execute();
	}
	
	private void checkTeleop() {
		if(DriverStation.getInstance().isOperatorControl()) {
			setPinConfiguration(false, false, true);
		}
	}
	
	private void checkIntake() {
		if(RobotMap.intakeMotorController1.get() <= -0.1) {
			// left motor is turning counter-clockwise and right is turning clockwise
			// the robot is releasing a cube
			setPinConfiguration(false, true, true);
			return;
		}else if(RobotMap.intakeMotorController1.get() >= 0.1) {
			// right motor is turning counter-clockwise and left is turning clockwise
			// robot is intaking a cube
			setPinConfiguration(false, true, false);
			return;
		}
	}
	
	private void checkElevator() {
		if(RobotMap.liftEncoder.getDistance() <= 1) {
			// elevator is at lowest point
			setPinConfiguration(true, false, false);
		}else if(Robot.liftSubsystem.checkTopLimitSwitch()) {
			// elevator is at highest point
			setPinConfiguration(true, true, false);
		}else if(RobotMap.liftEncoder.getDistance() >= SWITCH_FENCE_HEIGHT) {
			// elevator is at switch height
			setPinConfiguration(true, false, true);
		}
	}
	
	// if sensor sees a cube, then pin value is high. if it doesn't see a cube the 
	// pin value is low. this toggles independently of the other pins
	public void checkCubeInRobot() {
		RobotMap.pinThree.set(!RobotMap.cubeInRobot.get());
	}
	
	private void checkEndMatchOrAuton() {
		if(DriverStation.getInstance().isAutonomous()) {
			setPinConfiguration(false, false, false);
		}
		
		if(DriverStation.getInstance().getMatchNumber() <= 0) {
			return;
		}
		if(DriverStation.getInstance().getMatchTime() <= 30) {
			setPinConfiguration(true, true, true);
		}
	}
	
	private void setPinConfiguration(boolean pin2, boolean pin1, boolean pin0) {
		RobotMap.pinTwo.set(pin2);
		RobotMap.pinOne.set(pin1);
		RobotMap.pinZero.set(pin0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
