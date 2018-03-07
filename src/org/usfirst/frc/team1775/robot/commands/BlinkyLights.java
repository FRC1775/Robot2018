package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BlinkyLights extends Command{
	
	@Override
	protected void initialize() {
		super.initialize();
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		checkMotorController();
		checkElevator();
		checkCubeInRobot();
		super.execute();
	}
	
	private void checkMotorController() {
		if(RobotMap.intakeMotorController1.get()<0 && RobotMap.intakeMotorController2.get()>0) {
			setPinConfiguration(true, true, true, true);
			// outtaking
		}else if(RobotMap.intakeMotorController1.get()>0 && RobotMap.intakeMotorController2.get()<0) {
			setPinConfiguration(false, true, true, false);
		}
	}
	
	private void checkElevator() {
		if(!RobotMap.liftBottomLimitSwitch.get()) {
			setPinConfiguration(true, false, false, true);
		}else if(!RobotMap.liftTopLimitSwitch.get()) {
			setPinConfiguration(false, false, true, true);
		}else if(RobotMap.liftEncoder.getDistance()>20 && RobotMap.liftEncoder.getDistance()<24) {
			setPinConfiguration(true, false, false, false);
		}
	}
	
	// if sensor sees a cube, then pin value is high. if it doesn't see a cube the 
	// pin value is low. this toggles independently of the other pins
	public void checkCubeInRobot() {
		if(!RobotMap.cubeInRobot.get()) {
			setPinConfiguration(true, true, false, false);	
		}else {
			setPinConfiguration(true, true, true, false);
		}
		SmartDashboard.putBoolean("IR Sensor", RobotMap.cubeInRobot.get());
	}
	
	private void setPinConfiguration(boolean pin0, boolean pin1, boolean pin2, boolean pin3) {
		RobotMap.pinZero.set(pin0);
		RobotMap.pinOne.set(pin1);
		RobotMap.pinTwo.set(pin2);
		RobotMap.pinThree.set(pin3);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}