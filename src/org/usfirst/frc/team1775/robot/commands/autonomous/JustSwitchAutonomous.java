package org.usfirst.frc.team1775.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JustSwitchAutonomous extends CommandGroup {

    public JustSwitchAutonomous(int direction) {
    	addSequential(new AutonomousStart());
		addSequential(new BlockOnSwitchFromSides(direction));
		addSequential(new PickUpBlockFromSwitch(direction));
    }
}
