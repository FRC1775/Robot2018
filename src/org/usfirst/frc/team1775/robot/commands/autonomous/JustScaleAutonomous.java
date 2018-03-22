package org.usfirst.frc.team1775.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JustScaleAutonomous extends CommandGroup {

    public JustScaleAutonomous() {
    //	addSequential(new AutonomousStart());
		addSequential(new BlockOnScaleFromSides());
    }
}