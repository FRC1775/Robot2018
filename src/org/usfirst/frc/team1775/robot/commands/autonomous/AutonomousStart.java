package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.IntakeIn;
import org.usfirst.frc.team1775.robot.commands.IntakeLift;
import org.usfirst.frc.team1775.robot.commands.IntakeRelease;
import org.usfirst.frc.team1775.robot.commands.IntakeStop;
import org.usfirst.frc.team1775.robot.commands.LiftOffLimitSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousStart extends CommandGroup {
    public AutonomousStart() {
    	addSequential(new IntakeLift(true));
    	addSequential(new WaitCommand(0.5));
    	addParallel(new IntakeIn());
    	addSequential(new IntakeRelease());
    	addSequential(new IntakeStop());
    	addSequential(new LiftOffLimitSwitch());
    }
}
