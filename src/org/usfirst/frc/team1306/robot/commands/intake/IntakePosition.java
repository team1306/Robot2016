package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * An enum that describes the positions for the intake. VERTICAL means that it
 * is up and out of the way (but in the way of the shooter). PICKUP means that
 * it's pointing directly horizontal, a position used to pick up balls from the
 * ground. DOWN means that it is forced down, used to traverse defenses.
 * 
 * @author Finn Voichick
 */
public enum IntakePosition {

	VERTICAL(Constants.INTAKE_VERTICAL_POS), PICKUP(Constants.INTAKE_PICKUP_POS), DOWN(Constants.INTAKE_DOWN_POS);

	/** The angle, in degrees, of this position. */
	private final double position;

	/**
	 * Constructs an IntakePosition at the given angle. The angle is in degrees,
	 * with 0 being horizontal and 90 being vertical.
	 * 
	 * @param position
	 *            the angle of the arm.
	 */
	private IntakePosition(double position) {
		this.position = position;
	}

	/**
	 * Gets the angle of the intake arm in degrees.
	 * 
	 * @return the angle of the intake arm at this position.
	 */
	public double getPosition() {
		return position;
	}
}
