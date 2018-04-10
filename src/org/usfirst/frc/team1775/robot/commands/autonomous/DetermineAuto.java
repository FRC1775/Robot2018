package org.usfirst.frc.team1775.robot.commands.autonomous;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotStartingPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class DetermineAuto extends ConditionalCommand {
	private BooleanSupplier supplier;
	
    public DetermineAuto() {
    	this(
    		DetermineAuto::ifBothSwitchAndScaleOnOurSide,
    		new ChooseByRobotPosition(
				new IdealAutonomous(AutonomousConstants.RIGHT),
				new IdealAutonomous(AutonomousConstants.LEFT)),
    		new DetermineAuto(
				DetermineAuto::ifSwitchOnlyOnOurSide,
				new ChooseByRobotPosition(
					new JustSwitchAutonomous(AutonomousConstants.RIGHT),
					new JustSwitchAutonomous(AutonomousConstants.LEFT)),
				new DetermineAuto(
					DetermineAuto::ifNotInCenter,
					new DriveToAutoLineFromSides(),
					new DriveToAutoLineFromCenter()
				)
			/*	comment starts here new DetermineAuto(
					DetermineAuto::ifScaleOnlyOnOurSide,
					new ChooseByRobotPosition(
						new JustScaleAutonomous(AutonomousConstants.LEFT),
						new JustScaleAutonomous(AutonomousConstants.RIGHT)),
					new DriveToAutoLineFromSides()
				) comment ends here */
			)
		);
    } 
    
	// this will hopefully drive the robot to the scale and then lift the lift to scale height
	// if the scale is on our side. otherwise, it will just drive to the auto line
   /* public DetermineAuto() {
    	this(
    		DetermineAuto::ifBothSwitchAndScaleOnOurSide,
    		new ChooseByRobotPosition(
				new IdealAutonomous(AutonomousConstants.RIGHT),
				new IdealAutonomous(AutonomousConstants.LEFT)),
    		new DetermineAuto(
				DetermineAuto::ifSwitchOnlyOnOurSide,
				new ChooseByRobotPosition(
					new JustSwitchAutonomous(AutonomousConstants.RIGHT),
					new JustSwitchAutonomous(AutonomousConstants.LEFT)),
//				new DetermineAuto(
//					DetermineAuto::ifNotInCenter,
//					new DriveToAutoLineFromSides(),
//					new DriveToAutoLineFromCenter()
//				)
				new DetermineAuto(
					DetermineAuto::ifScaleOnlyOnOurSide,
					new ChooseByRobotPosition(
						new JustScaleAutonomous(AutonomousConstants.RIGHT),
						new JustScaleAutonomous(AutonomousConstants.LEFT)),
					new DriveToAutoLineFromSides()
				)
			)
		);
    }
    */
    public DetermineAuto(BooleanSupplier supplier, Command a, Command b)
    {
    	super(a, b);
    	this.supplier = supplier;
    }
    
    public DetermineAuto(BooleanSupplier supplier, Command a)
    {
    	super(a);
    	this.supplier = supplier;
    }
    
    private static boolean ifBothSwitchAndScaleOnOurSide() {
    	return (isSwitchLeft() && Robot.checkFMS().charAt(1) == 'L' && Robot.getRobotStartingPosition() == RobotStartingPosition.LEFT) ||
				(isSwitchRight() && Robot.checkFMS().charAt(1) == 'R' && Robot.getRobotStartingPosition() == RobotStartingPosition.RIGHT);
    }
    
    private static boolean ifSwitchOnlyOnOurSide() {
    	return (isSwitchLeft() && Robot.getRobotStartingPosition() == RobotStartingPosition.LEFT) ||
		(isSwitchRight() && Robot.getRobotStartingPosition() == RobotStartingPosition.RIGHT);
    }
    
    private static boolean ifNotInCenter() {
    	return Robot.getRobotStartingPosition() != RobotStartingPosition.CENTER;
    }
    
    private static boolean ifScaleOnlyOnOurSide() {
    	return (isScaleLeft() && Robot.getRobotStartingPosition() == RobotStartingPosition.LEFT) ||
				(isScaleRight() && Robot.getRobotStartingPosition() == RobotStartingPosition.RIGHT);
    }
    
    private static boolean isSwitchLeft()
    {
    	return Robot.checkFMS().charAt(0) == 'L';
    }
    
    private static boolean isSwitchRight()
    {
    	return Robot.checkFMS().charAt(0) == 'R';
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
