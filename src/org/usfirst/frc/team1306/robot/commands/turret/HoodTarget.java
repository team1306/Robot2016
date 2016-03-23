package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * An enum that contains the three different hood targeting mode. AUTO means
 * that the hood is automatically targeting using the Vision targets. LOW means
 * that it's at a set position that aims for the low goal. HIGH means that it's
 * at a set high position, used for when Vision targeting isn't working or
 * another robot is defending us.
 * 
 * @author Finn Voichick
 */
public enum HoodTarget {

	FLAT(90.0, "Flat"), LOW(Constants.HOOD_LOW_GOAL_POSITION, "Low Goal"), NEW(Constants.HOOD_NEW_BALL_POSITION, "New Ball"), OLD(
			Constants.HOOD_OLD_BALL_POSITION, "Old Ball"), MEDIUM(Constants.HOOD_MEDIUM_BALL_POSITION, "Medium Ball"), BATTER(
					Constants.HOOD_BATTER_POSITION, "Batter Shot");

	private final double height;
	private final String name;

	public double getHeight() {
		return height;
	}
	
	@Override
	public String toString() {
		return name;
	}

	private HoodTarget(double height, String name) {
		this.height = height;
		this.name = name;
	}

}
