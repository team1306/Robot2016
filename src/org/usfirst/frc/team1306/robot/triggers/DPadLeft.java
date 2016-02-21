package org.usfirst.frc.team1306.robot.triggers;

import org.usfirst.frc.team1306.robot.XboxController;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DPadLeft extends Trigger {
	
	private final XboxController xbox;
	
	public DPadLeft(XboxController xbox) {
		this.xbox = xbox;
	}
    
    public boolean get() {
        return xbox.getPOV() == 270;
    }
}
