package org.usfirst.frc.team1306.robot.triggers;

import org.usfirst.frc.team1306.robot.XboxController;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DPadUp extends Trigger {

	private XboxController xbox;

	public DPadUp(XboxController xbox) {
		this.xbox = xbox;
	}

	public boolean get() {
		return xbox.getPOV() == 0;
	}
}
