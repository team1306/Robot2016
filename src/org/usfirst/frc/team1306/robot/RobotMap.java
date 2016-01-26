package org.usfirst.frc.team1306.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int xboxPort = 0;
	public static final int secondaryPort = 1;

	public static final int leftTalon1Port = 1;
	public static final int leftTalon2Port = 2;
	public static final int rightTalon1Port = 3;
	public static final int rightTalon2Port = 4;

	public static final int flyWheelTalonPort = 5;
	public static final int hoodTalonPort = 6;
	public static final int turretTalonPort = 7;

	public static final int leftIntakeTalonPort = 8;
	public static final int rightIntakeTalonPort = 9;

	public static final int turretEncoderPort = 2;

	public static final int intakeLimitPort = 1;
}
