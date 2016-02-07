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

	public static int leftShifterPortA = 0;
	public static int leftShifterPortB = 1;
	public static int rightShifterPortA = 2;
	public static int rightShifterPortB = 3;

	// shooter and turret controller ports
	public static int flyWheelTalonPort = 7;
	public static int hoodTalonPort = 8;
	public static int turretTalonPort = 9;

	// intake control ports
	public static int intakeMotor1Port = 10;
	public static int intakeMotor2Port = 11;

	public static int intakeRoller1Port = 0;
	public static int intakeRoller2Port = 1;

	public static int intakeSol1PortA = 4;
	public static int intakeSol1PortB = 5;
	public static int intakeSol2PortA = 6;
	public static int intakeSol2PortB = 7;

	// intake sensor ports
	public static int intakeLimitPort = 1;

	// arm control ports
	public static int armPort = 2;

}
