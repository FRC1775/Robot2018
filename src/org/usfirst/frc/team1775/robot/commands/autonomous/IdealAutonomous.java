package org.usfirst.frc.team1775.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IdealAutonomous extends CommandGroup {

    public IdealAutonomous(int direction) {
		addSequential(new AutonomousStart());
		addSequential(new BlockOnSwitchFromSides(direction));
		addSequential(new PickUpBlockFromSwitch(direction));
		addSequential(new PickUpToScale(direction));
    }
}
