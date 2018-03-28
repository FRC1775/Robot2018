package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.FlippyCube;
import org.usfirst.frc.team1775.robot.commands.LiftHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *We are on the sides here dog.
 */
public class DropBlock extends CommandGroup {

    public DropBlock(double height, double speed) {
    	//LiftToHeight(19)
    	//Push block using the conveyor belt
    	//Reset the lift
    	
    	addSequential(new LiftHeight(height));
    	addSequential(new FlippyCube(speed), 3);
    	addSequential(new LiftHeight(0));
    }
}
