package org.usfirst.frc.team1306.robot.vision;

import edu.wpi.first.wpilibj.Timer;

/**
 * A simple class to store basic data from the Jetson. It stores pitch, yaw,
 * distance, and the time.
 * 
 * @author Finn Voichick
 */
public final class VisionData {

	/** The pitch of the center of the target in arbitrary units. */
	private final double pitch;
	/** The yaw of the center of the target in arbitrary units. */
	private final double yaw;
	/** The straight-line distance to the target, in arbitrary units. */
	private final double distance;
	/** The time that these calculations were made. */
	private final double timestamp;

	/**
	 * Constructs a VisionData object using the given pitch, yaw and distance.
	 * The timestamp is calculated in this constructor.
	 * 
	 * @param pitch
	 *            The pitch of the center of the target in degrees, relative to
	 *            the horizontal.
	 * @param yaw
	 *            The yaw of the center of the target in degrees, relative to
	 *            the heading of the turret.
	 * @param distance
	 *            The straight-line distance to the target, in inches.
	 */
	public VisionData(double pitch, double yaw, double distance) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.distance = distance;
		this.timestamp = Timer.getFPGATimestamp();
	}

	/**
	 * Gets the pitch of the center of the target in arbitrary units.
	 * 
	 * @return the pitch of the center of the target.
	 */
	public double getPitch() {
		return pitch;
	}

	/**
	 * Gets the yaw of the center of the target in arbitrary units.
	 * 
	 * @return the yaw of the center of the target.
	 */
	public double getYaw() {
		return yaw;
	}

	/**
	 * Gets the straight-line distance to the target, in arbitrary units.
	 * 
	 * @return the distance to the target.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Gets the time that these calculations were made.
	 * 
	 * @return the time that these calculations were made.
	 */
	public double getTimestamp() {
		return timestamp;
	}
}
