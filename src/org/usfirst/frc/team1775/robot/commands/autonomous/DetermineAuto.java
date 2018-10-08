package org.usfirst.frc.team1775.robot.commands.autonomous;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team1775.robot.PrioritizeSwitchScale;
import org.usfirst.frc.team1775.robot.Robot;
import org.usfirst.frc.team1775.robot.RobotStartingPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class DetermineAuto extends ConditionalCommand {
	private BooleanSupplier supplier;
    
	// this will hopefully drive the robot to the scale and then lift the lift to scale height
	// if the scale is on our side. otherwise, it will just drive to the auto line
//    public DetermineAuto() {
//    	this(
//    		DetermineAuto::ifBothSwitchAndScaleOnOurSide,
//    		new ChooseByRobotPosition(
//				new IdealAutonomous(AutonomousConstants.RIGHT),
//				new IdealAutonomous(AutonomousConstants.LEFT)),
//    		new DetermineAuto(
//				DetermineAuto::ifSwitchOnlyOnOurSide,
//				new ChooseByRobotPosition(
//					new DriveAndTurn(AutonomousConstants.RIGHT),
//					new DriveAndTurn(AutonomousConstants.LEFT)),
//				new DetermineAuto(
//					DetermineAuto::ifScaleOnlyOnOurSide,
//					new ChooseByRobotPosition(
//						new JustScaleAutonomous(AutonomousConstants.RIGHT),
//						new JustScaleAutonomous(AutonomousConstants.LEFT)),
//					new DriveToAutoLineFromSides()
//				)
//			)
//		);
//    }
	
	 public DetermineAuto() {
	    	this(
	    		DetermineAuto::ifBothSwitchAndScaleOnOurSide,
	    		new DetermineAuto(
	    				DetermineAuto::ifPrioritizeSwitch,
	    			new ChooseByRobotPosition(
	    				new BlockOnSwitchFromSides(AutonomousConstants.RIGHT),
	    				new BlockOnSwitchFromSides(AutonomousConstants.LEFT)
	    				),
	    			new ChooseByRobotPosition(
	    				new JustScaleAutonomous(AutonomousConstants.RIGHT),
	    				new JustScaleAutonomous(AutonomousConstants.LEFT)
	    				)	    		
					),
	    		new DetermineAuto(
					DetermineAuto::ifSwitchOnlyOnOurSide,
					new ChooseByRobotPosition(
						new DriveAndTurn(AutonomousConstants.RIGHT),
						new DriveAndTurn(AutonomousConstants.LEFT)),
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
    	return (isSwitchLeft() && isScaleLeft() && Robot.getRobotStartingPosition() == RobotStartingPosition.LEFT) ||
				(isSwitchRight() && isScaleRight() && Robot.getRobotStartingPosition() == RobotStartingPosition.RIGHT);
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
    
    private static boolean ifPrioritizeSwitch() {
    	return (Robot.getPriority() == PrioritizeSwitchScale.SWITCH);
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
