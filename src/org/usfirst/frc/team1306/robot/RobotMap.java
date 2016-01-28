package org.usfirst.frc.team1306.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author James Tautges
 */
public class RobotMap {

	// oi ports
	public static int xboxPort = 0;
	public static int secondaryPort = 1;

	// drivetrain controllers ports
	public static int leftTalon1Port = 1;
	public static int leftTalon2Port = 2;
	public static int leftTalon3Port = 3;
	public static int rightTalon1Port = 4;
	public static int rightTalon2Port = 5;
	public static int rightTalon3Port = 6;
	
	// shooter and turret controller ports
	public static int flyWheelTalonPort = 7;
	public static int hoodTalonPort = 8;
	public static int turretTalonPort = 9;
	
	// intake sensor ports
	public static int intakeLimitPort = 1;
}
