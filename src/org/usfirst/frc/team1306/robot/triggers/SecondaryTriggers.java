package org.usfirst.frc.team1306.robot.triggers;

import org.usfirst.frc.team1306.robot.XboxController;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class SecondaryTriggers extends Trigger {

	private XboxController xbox;

	public SecondaryTriggers(XboxController xbox) {
		this.xbox = xbox;
	}

	public boolean get() {
		return xbox.getTrigger(Hand.kLeft) != xbox.getTrigger(Hand.kRight);
	}
}
