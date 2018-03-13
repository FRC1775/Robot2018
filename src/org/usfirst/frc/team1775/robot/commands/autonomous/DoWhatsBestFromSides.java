package org.usfirst.frc.team1775.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoWhatsBestFromSides extends CommandGroup {

    public DoWhatsBestFromSides() {
        addSequential(new AutonomousStart());
        addSequential(new DetermineAuto());
    }
}
