package org.usfirst.frc.team1775.robot.subsystems;

public class IntakeSubsystem extends Subsystem{
@Override
    protected void initDefaultCommand(){
        setDefaultCommand(new Intake());
        
    }
    
    public void setSpeed(double speed){
        RobotMap.intakeController1.set(speed);
        RobotMap.intakeController2.set(speed);
    
    public void drive (double moveValue, double rotateValue){
        RobotMap.driveTrain.arcadeDrive()
    }
}
