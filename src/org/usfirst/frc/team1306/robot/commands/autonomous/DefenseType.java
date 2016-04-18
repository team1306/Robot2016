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

	LOWBAR(new TimedDrive(Constants.LOW_BAR_POWER, Constants.LOW_BAR_TIME)), OBSTACLE(
			new TimedDrive(Constants.OBSTACLE_POWER, Constants.OBSTACLE_TIME)), TERRAIN(
					new TimedDrive(Constants.TERRAIN_POWER, Constants.TERRAIN_TIME));

	/** The TimedDrive command associated with the particular defense */
	private final TimedDrive driveCommand;

	/**
	 * Constructs the DefenseType with a TimedDrive command. This command stores
	 * the throttle values for the motors, as well as the length of time to
	 * drive.
	 * 
	 * @param driveCommand
	 *            the command used to traverse the defense.
	 */
	private DefenseType(TimedDrive driveCommand) {
		this.driveCommand = driveCommand;
	}

	/**
	 * Gets the command used to traverse the defense.
	 * 
	 * @return the TimedDrive command associated with this type of defense.
	 */
	public TimedDrive getDriveCommand() {
		return driveCommand;
	}
}
