package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.IntakeIn;
//import org.usfirst.frc.team1775.robot.commands.IntakeLift;
import org.usfirst.frc.team1775.robot.commands.IntakeRelease;
import org.usfirst.frc.team1775.robot.commands.IntakeStop;
import org.usfirst.frc.team1775.robot.commands.LiftHeight;
import org.usfirst.frc.team1775.robot.commands.LiftOffLimitSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousStart extends CommandGroup {
    public AutonomousStart() {
    	//addSequential(new IntakeLift(true));
    	addSequential(new WaitCommand(0.5));
    	addParallel(new IntakeIn(AutonomousConstants.AUTO_START_INTAKE_SPEED));
    	addSequential(new IntakeRelease());
    	addSequential(new WaitCommand(1.5));
    	addSequential(new IntakeStop());
    	addSequential(new LiftOffLimitSwitch());
//    	addSequential(new LiftHeight(4));
    	addSequential(new LiftHeight(4), 1);
    }
}
