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
			} else if ((gameData.charAt(0) == 'L' && direction == AutonomousConstants.LEFT)||
			(gameData.charAt(0) == 'R' && direction == AutonomousConstants.RIGHT)) {
				addSequential(new BlockOnSwitchFromSides(direction));
				addSequential(new PickUpBlockFromSwitch(direction));
			} else if ((gameData.charAt(1) == 'L' && direction == AutonomousConstants.LEFT)||
					(gameData.charAt(1) == 'R' && direction == AutonomousConstants.RIGHT)) {
				addSequential(new BlockOnScaleFromSides(direction));
			} else {
				if (gameData.charAt(0) == 'L') {
					addSequential(new BlockOnSwitchFromCenter(AutonomousConstants.LEFT));
				} else {
					addSequential(new BlockOnSwitchFromCenter(AutonomousConstants.RIGHT));
				}
			}
		}
	}
}