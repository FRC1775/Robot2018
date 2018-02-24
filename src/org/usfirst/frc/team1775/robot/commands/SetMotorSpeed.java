package org.usfirst.frc.team1775.robot.commands;
import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetMotorSpeed extends Command{
   public SetMotorSpeed(){
     requires(Robot.motorSubsystem);   
   }
   @Override
public void execute (){
     // double yVal = OI.myJoystick.getY();
     // Robot.motorSubsystem.setSpeed(yVal);
   
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
}