package org.usfirst.frc.team1775.robot.commands.autonomous;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotStartingPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class DetermineAutoScaleOnly extends ConditionalCommand {
	private BooleanSupplier supplier;
	
	 public DetermineAutoScaleOnly() {
	    	this(
	    		DetermineAutoScaleOnly::ifScaleOnOurSide,
	    			new ChooseByRobotPosition(
	    				new JustScaleAutonomous(AutonomousConstants.RIGHT),
	    				new JustScaleAutonomous(AutonomousConstants.LEFT)
	    				),
	    			new DriveToAutoLineFromSides()
			);
	    }
    
    public DetermineAutoScaleOnly(BooleanSupplier supplier, Command a, Command b)
    {
    	super(a, b);
    	this.supplier = supplier;
    }
    
    public DetermineAutoScaleOnly(BooleanSupplier supplier, Command a)
    {
    	super(a);
    	this.supplier = supplier;
    }
    
    private static boolean ifScaleOnOurSide() {
    	return (isScaleLeft() && Robot.getRobotStartingPosition() == RobotStartingPosition.LEFT) ||
    			(isScaleRight() && Robot.getRobotStartingPosition() == RobotStartingPosition.RIGHT);
    }
    
    private static boolean isScaleLeft()
    {
    	return Robot.checkFMS().charAt(1) == 'L';
    }
    
    private static boolean isScaleRight()
    {
    	return Robot.checkFMS().charAt(1) == 'R';
    }

	@Override
	protected boolean condition() {
		if (supplier != null) {
			return supplier.getAsBoolean();
		}
		
		return false;
	}
}
