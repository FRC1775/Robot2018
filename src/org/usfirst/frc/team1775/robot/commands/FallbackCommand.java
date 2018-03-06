package org.usfirst.frc.team1775.robot.commands;

import org.usfirst.frc.team1775.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public abstract class FallbackCommand extends Command {
	private Joystick joystick;
	
    public void setFallback(Joystick joystick) {
    	this.joystick = joystick;
    }

    @Override
    public synchronized boolean isCanceled() {
		if (this.joystick != null && !OI.isOnlyJoystick(joystick)) {
			return true;
		}
		return super.isCanceled();
    }
}
