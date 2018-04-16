package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.PrioritizeSwitchScale;
import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IdealAutonomous extends CommandGroup {

    public IdealAutonomous(int direction) {
		addSequential(new AutonomousStart());
		System.out.println(Robot.getPriority());
		if(Robot.getPriority() == PrioritizeSwitchScale.SCALE) {
			addSequential(new BlockOnScaleFromSides(direction));
		} else {
			addSequential(new BlockOnSwitchFromSides(direction));
		}
    }
}
