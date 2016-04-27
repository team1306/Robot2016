package org.usfirst.frc.team1306.robot.commands.intake;

/**
 * Represents the quality of a ball. Used to alert the drivers when a ball is
 * too old to shoot well.
 * 
 * @author Finn Voichick
 *
 */
public enum BallQuality {

	OLD("DEAD"), NEW("Good");

	/** The human-friendly name to show on the SmartDashboard. */
	private final String name;

	/**
	 * Constructs a BallQuality object with the given name.
	 * 
	 * @param name
	 *            the human-friendly name of this BallQuality.
	 */
	private BallQuality(String name) {
		this.name = name;
	}

	/**
	 * Returns the quality as a human-friendly String.
	 * 
	 * @return the name of this ball quality.
	 */
	@Override
	public String toString() {
		return name;
	}

}
