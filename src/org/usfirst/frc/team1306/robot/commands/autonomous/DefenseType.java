package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * An enum to represent the different types of defenses to cross in the
 * autonomous period. Acts as a wrapper for the TimedDrive command associated
 * with that defense.
 * 
 * @author Finn Voichick
 */
public enum DefenseType {

	LOWBAR(Constants.LOW_BAR_POWER, Constants.LOW_BAR_TIME), MOAT(Constants.MOAT_POWER, Constants.MOAT_TIME), RAMPARTS(
			Constants.RAMPARTS_POWER, Constants.RAMPARTS_TIME), TERRAIN(Constants.TERRAIN_POWER,
					Constants.TERRAIN_TIME), WALL(Constants.WALL_POWER, Constants.WALL_TIME);

	/** The thottle value used for this defense's TimedDrive command. */
	private final double throttle;
	/** The time value used for this defense's TimedDrive command. */
	private final double time;

	/**
	 * Constructs the DefenseType with a TimedDrive command. This command stores
	 * the throttle values for the motors, as well as the length of time to
	 * drive.
	 * 
	 * @param driveCommand
	 *            the command used to traverse the defense.
	 */
	private DefenseType(double throttle, double time) {
		this.throttle = throttle;
		this.time = time;
	}

	/**
	 * Gets the command used to traverse the defense.
	 * 
	 * @return the TimedDrive command associated with this type of defense.
	 */
	public TimedDrive getDriveCommand() {
		return new TimedDrive(throttle, time);
	}
}
