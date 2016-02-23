package org.usfirst.frc.team1306.robot.commands.turret;

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

	AUTO, LOW, HIGH;

}
