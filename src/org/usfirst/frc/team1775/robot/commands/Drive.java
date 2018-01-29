package org.usfirst.frc.team1775.robot.commands;

package org.usfirst.frc.team1775.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.OI;


public class Drive extends Command {
requires(Robot.MotorSubsystem);
    public void execute ();
    double yVal = OI.myJoystick.getY();
    double xVal = OI.rightJoystick.getX();
        Robot.motorSubsystem.drive(yVal,xVal);

}
