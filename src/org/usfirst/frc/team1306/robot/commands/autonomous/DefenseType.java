package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;

public enum DefenseType {

	LOWBAR(new TimedDrive(Constants.LOW_BAR_POWER, Constants.LOW_BAR_TIME)), OBSTACLE(
			new TimedDrive(Constants.OBSTACLE_POWER, Constants.OBSTACLE_TIME)), TERRAIN(
					new TimedDrive(Constants.TERRAIN_POWER, Constants.TERRAIN_TIME));

	private final TimedDrive driveCommand;

	private DefenseType(TimedDrive driveCommand) {
		this.driveCommand = driveCommand;
	}

	public TimedDrive getDriveCommand() {
		return driveCommand;
	}
}
