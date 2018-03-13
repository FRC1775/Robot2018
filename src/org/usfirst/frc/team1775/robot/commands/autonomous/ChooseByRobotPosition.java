package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotStartingPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class ChooseByRobotPosition extends ConditionalCommand {

    public ChooseByRobotPosition(Command leftCommand, Command rightCommand) {
    	super(leftCommand, rightCommand);
    }

	@Override
	protected boolean condition() {
		return Robot.getRobotStartingPosition() == RobotStartingPosition.LEFT;
	}
}
