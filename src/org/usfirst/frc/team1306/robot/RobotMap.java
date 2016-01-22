package org.usfirst.frc.team1306.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static int xboxPort = 0;
	public static int secondaryPort = 1;

	public static int leftTalon1Port = 1;
	public static int leftTalon2Port = 2;
	public static int rightTalon1Port = 3;
	public static int rightTalon2Port = 4;
	
	public static int flyWheelTalonPort = 5;
	public static int hoodTalonPort = 6;
	public static int turretTalonPort = 7;
	
	public static int turretEncoderPort = 2;
	
	public static int intakeLimitPort = 1;
}
