package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchScaleLogic extends CommandGroup{
	public SwitchScaleLogic(int direction) {
		String gameData = Robot.checkFMS();
	
		if(gameData.length() > 0) {
			if((gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L' && direction == AutonomousConstants.LEFT) ||
					(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R' && direction == AutonomousConstants.RIGHT)) {
				addSequential(new BlockOnSwitchFromSides(direction));
				addSequential(new PickUpBlockFromSwitch(direction));
				addSequential(new PickUpToScale(direction));
			} else {
				// account for the other situations
			}
		}
	}
}