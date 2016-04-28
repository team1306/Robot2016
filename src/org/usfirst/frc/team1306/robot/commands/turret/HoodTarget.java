package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * An enum that contains the three different hood targeting mode. LOW means that
 * it's at a set position that aims for the low goal. AUTO means that the hood
 * is automatically targeting using the Vision targets. BATTER_FAR means that
 * it's at a set position aiming from the batter edge, and BATTER_CLOSE means
 * that it's at a set position up on the batter.
 * 
 * @author Finn Voichick
 */
public enum HoodTarget {

	LOW(Constants.HOOD_LOW_GOAL_POSITION, "Low Goal"), AUTO(Constants.HOOD_NORMAL_TARGET_POSITION,
			"Auto Shot"), SETPOINT(Constants.HOOD_NORMAL_TARGET_POSITION, "Setpoint Shot"), BATTER_FAR(Constants.HOOD_BATTER_FAR_POSITION,
					"Batter Edge Shot"), BATTER_CLOSE(Constants.HOOD_BATTER_CLOSE_POSITION, "Batter Shot");

	/** The set angle for the hood for this target. */
	private final double height;
	/** The name of this target on the SmartDashboard. */
	private final String name;

	/** Gets the set angle for the hood for this target. */
	public double getHeight() {
		return height;
	}

	/** Gets the name of this target for the SmartDashboard. */
	@Override
	public String toString() {
		return name;
	}

	/** Constructs a HoodTarget with the given height and name */
	private HoodTarget(double height, String name) {
		this.height = height;
		this.name = name;
	}

}
