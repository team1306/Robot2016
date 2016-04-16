package org.usfirst.frc.team1306.robot;

/**
 * All random numbers that aren't port numbers should go here once they're
 * settled. Everything is prettier that way.
 * 
 * @author James Tautges
 */

public class Constants {

	// drive constants
	// measured in encoder ticks per 1ms^2
	// commented out because drive encoders aren't currently used
	// public final static double MAX_ACCELERATION = 0.01;
	// public final static double MAX_SPEED = 7000;

	// turret constants
	public final static double TURRET_VISION_TOLERANCE = 1.0;

	public final static double TURRET_P = 0.015;
	public final static double TURRET_I = 0.001;
	public final static double TURRET_D = 0.0;

	public final static double TURRET_EDGE = 12000;
	public final static double TURRET_SCAN_THRESHOLD = 11000;
	public final static double TURRET_SCAN_SPEED = 0.2;

	// shooter constants
	public final static double SHOOTER_CONVERSION_FACTOR = 3.413333333;
	public final static int SHOOTER_TOLERANCE = 50;
	public final static double SHOOTER_SET_SPEED = 9000;
	public final static double SHOOTER_LOW_SPIN = 3000;

	// hood constants
	public final static double HOOD_0_POS = 80.0;
	public final static double HOOD_90_POS = 800.0;
	public final static double HOOD_LOW_GOAL_POSITION = 10;
	public final static double HOOD_NORMAL_TARGET_POSITION = 50.5;
	public final static double HOOD_BATTER_CLOSE_POSITION = 67.0;
	public final static double HOOD_BATTER_FAR_POSITION = 61;
	public final static double HOOD_TOLERANCE = 0.25;

	// indexer constants
	public final static double INDEXER_POWER = 0.6;

	// intake arm constants
	public final static double INTAKE_LEFT_ARM_0_POS = 74.0;
	public final static double INTAKE_LEFT_ARM_90_POS = 390.0;
	public final static double INTAKE_RIGHT_ARM_0_POS = 836.0;
	public final static double INTAKE_RIGHT_ARM_90_POS = 401.0;
	public final static double INTAKE_PICKUP_POS = 11.0;
	public final static double INTAKE_VERTICAL_POS = 85.0;
	public final static double INTAKE_DROP_THRESHOLD = 10.0;
	public final static double INTAKE_DOWN_POS = -30.0;

	// OI constants
	public final static double DEADBAND = 0.15;
	public final static double JOYSTICK_POWER = 1.0;

	// vision constants
	public final static String VISION_HOSTNAME = "10.13.6.19";
	public final static int VISION_PORT_NUMBER = 5802;
	public final static double VISION_PERIOD = 0.1;
	public final static double MIN_DISTANCE = 7.0;
	public final static double MAX_DISTANCE = 10.0;

	// autonomous constants
	public final static double LOW_BAR_POWER = 0.75;
	public final static double TERRAIN_POWER = 0.9;
	public final static double OBSTACLE_POWER = 1.0;
	public final static double LOW_BAR_TIME = 3.5;
	public final static double TERRAIN_TIME = 2.5;
	public final static double OBSTACLE_TIME = 2.75;

}
