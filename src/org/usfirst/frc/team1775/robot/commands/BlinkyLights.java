package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BlinkyLights extends Command{
	private static double SWITCH_FENCE_HEIGHT = 18.75;
	
	public BlinkyLights() {
		requires(Robot.blinkyLightSubsystem);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	}
		
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		checkIntake();
		checkElevator();
		checkCubeInRobot();
		//SmartDashboard.updateValues();
		super.execute();
	}
	
	private void checkIntake() {
		if(RobotMap.intakeMotorController1.get() < 0 && RobotMap.intakeMotorController2.get() > 0) {
			// left motor is turning counter-clockwise and right is turning clockwise
			// the robot is releasing a cube
			setPinConfiguration(false, true, true);
//			System.out.println("it's working at least");
			SmartDashboard.putString("Intake state", "spit it out");
			return;
		}else if(RobotMap.intakeMotorController1.get() > 0 && RobotMap.intakeMotorController2.get() < 0) {
			// right motor is turning counter-clockwise and left is turning clockwise
			// robot is intaking a cube
			setPinConfiguration(false, true, false);
//			SmartDashboard.putString("Intake state", "take it in");
			return;
		}
		setPinConfiguration(false, false, true);
//		SmartDashboard.putString("Intake state", "nothing hete");
	}
	
	private void checkElevator() {
		if(RobotMap.liftBottomLimitSwitch.get()) {
			// elevator is at lowest point
			setPinConfiguration(true, false, false);
			SmartDashboard.putString("Lift state", "bottom");
		}else if(RobotMap.liftTopLimitSwitch.get()) {
			// elevator is at highest point
			setPinConfiguration(true, true, false);
			SmartDashboard.putString("Lift state", "top ;)");
		}else if(RobotMap.liftEncoder.getDistance() >= SWITCH_FENCE_HEIGHT) {
			// elevator is at halfway point
			// probably this will change to height of switch and/or scale
			setPinConfiguration(true, false, true );
			SmartDashboard.putString("Lift state", "greater than scale height");
		}
		SmartDashboard.putString("Lift state", "in the void (like me)");
	}
	
	// if sensor sees a cube, then pin value is high. if it doesn't see a cube the 
	// pin value is low. this toggles independently of the other pins
	public void checkCubeInRobot() {
		if(!RobotMap.cubeInRobot.get()) {
			// there is a cube in the robot
			RobotMap.pinThree.set(true);
		//	SmartDashboard.putString("Lift state", "found the cube myguys");
		}else {
			// there is no cube in the robot
			RobotMap.pinThree.set(false);
		//	SmartDashboard.putString("Lift state", "nothing here xd");
		}
//		SmartDashboard.putBoolean("IR Sensor", RobotMap.cubeInRobot.get());
//		System.out.println(RobotMap.cubeInRobot.get());
	}
	
	private void setPinConfiguration(boolean pin0, boolean pin1, boolean pin2) {
		RobotMap.pinZero.set(pin0);
		RobotMap.pinOne.set(pin1);
		RobotMap.pinTwo.set(pin2);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
