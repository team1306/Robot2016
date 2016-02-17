package org.usfirst.frc.team1306.robot.triggers;

import org.usfirst.frc.team1306.robot.XboxController;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DPadDown extends Trigger {

	private final XboxController xbox;
	
	public DPadDown(XboxController xbox) {
		this.xbox = xbox;
	}

	public boolean get() {
		return xbox.getPOV() == 180;
	}
}
