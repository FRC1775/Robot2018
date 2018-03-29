package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.FlippyCube;
import org.usfirst.frc.team1775.robot.commands.LiftHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DropBlock extends CommandGroup {

    public DropBlock(double height, double speed) {
    	addSequential(new LiftHeight(height));
    	addSequential(new FlippyCube(speed), 1.5);
    	addSequential(new LiftHeight(0));
    }
}
