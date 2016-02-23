package org.usfirst.frc.team1306.robot;

/**
 * All random numbers that aren't port numbers should go here once they're
 * settled. Everything is prettier that way.
 * 
 * @author James Tautges
 */

public class Constants {

	public final static double JOYSTICK_POWER = 1.0;

	// drive constants
	// measured in encoder ticks per 1ms^2
	public final static double MAX_ACCELERATION = 0.01;
	public final static double MAX_SPEED = 7000; // placeholder

	// turret constants
	public final static double TURRET_VISION_TOLERANCE = 20.0;

	public final static double TURRET_P = 0.015;
	public final static double TURRET_I = 0.0008; // 0.001
	public final static double TURRET_D = 0.0;

	public final static double TURRET_EDGE = 12000;
	public final static double TURRET_SCAN_THRESHOLD = 11000;
	public final static double TURRET_SCAN_SPEED = 0.2;

	// shooter constants
	public final static double SHOOTER_MAX_SPEED = 23400.0;
	public final static int SHOOTER_TOLERANCE = 129; // 0.5% tolerance
	public final static double SHOOTER_SET_SPEED = 0.95;

	// hood constants
	public final static double HOOD_0_POS = 58.0;
	public final static double HOOD_90_POS = 800.0;
	public final static double HOOD_LOW_GOAL_POSITION = 10;
	public final static double HOOD_HIGH_POSITION = 45;
	public final static double HOOD_TOLERANCE = 8.0;
	public final static double HOOD_MAX_VOLTAGE = 6.0;

	// indexer constants
	public final static double INDEXER_POWER = 0.75;

	// intake constants
	public final static double INTAKE_LENGTH = 14.0; // placeholder
	public final static double TUSK_LENGTH = 5.0; // placeholder

	// intake arm constants
	public final static double INTAKE_LEFT_ARM_0_POS = 74.0;
	public final static double INTAKE_LEFT_ARM_90_POS = 540.0;
	public final static double INTAKE_RIGHT_ARM_0_POS = 836.0;
	public final static double INTAKE_RIGHT_ARM_90_POS = 401.0;
	public final static double INTAKE_PICKUP_POS = 10.0;
	public final static double INTAKE_VERTICAL_POS = 85.0;
	public final static double INTAKE_DROP_THRESHOLD = 15.0;
	public final static double INTAKE_DOWN_POS = -20.0;

	// OI constants
	public final static double DEADBAND = 0.15;

	// vision constants
	public final static double VISION_PERIOD = 0.1;
	public final static double DISTANCE_CONVERSION = 1000.0; // placeholder
	public final static double DEGREES_ABOVE_TARGET = 5.0;

	// autonomous constants
	public final static double LOW_BAR_POWER = 0.75;
	public final static double TERRAIN_POWER = 0.9;
	public final static double LOW_BAR_TIME = 3.5;
	public final static double TERRAIN_TIME = 3.0;

}
