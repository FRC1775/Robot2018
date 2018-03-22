package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.FlippyCube;
import org.usfirst.frc.team1775.robot.commands.LiftHeight;
import org.usfirst.frc.team1775.robot.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlockOnSwitchFromCenter extends CommandGroup {

    public BlockOnSwitchFromCenter() {
    	System.out.println("trying to put a block on switch from center");
    	addSequential(new DriveToAutoLineFromCenter());
    	addSequential(new RotateToAngle(90));
    	addSequential(new LiftHeight(AutonomousConstants.SWITCH_HEIGHT));
    	addSequential(new FlippyCube(-1));
    }
}