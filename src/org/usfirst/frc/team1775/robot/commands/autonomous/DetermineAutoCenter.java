package org.usfirst.frc.team1775.robot.commands.autonomous;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotStartingPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class DetermineAutoCenter extends ConditionalCommand {
	private BooleanSupplier supplier;
	
    public DetermineAutoCenter() {
    	this(
			DetermineAutoCenter::ifSwitchOnRightSide,
			new BlockOnRightSwitchFromCenter(),
    		new BlockOnLeftSwitchFromCenter()
		);
    }
    
    public DetermineAutoCenter(BooleanSupplier supplier, Command a, Command b)
    {
    	super(a, b);
    	this.supplier = supplier;
    }
    
    public DetermineAutoCenter(BooleanSupplier supplier, Command a)
    {
    	super(a);
    	this.supplier = supplier;
    }
    
    private static boolean ifSwitchOnRightSide() {
    	return (isSwitchRight() && Robot.getRobotStartingPosition() == RobotStartingPosition.CENTER);
    }
    
    private static boolean isSwitchRight()
    {
    	return Robot.checkFMS().charAt(0) == 'R';
    }
    
    protected boolean condition() {
		if (supplier != null) {
			return supplier.getAsBoolean();
		}
		
		return false;
	}
}
