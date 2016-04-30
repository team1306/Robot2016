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
	public final static double TURRET_VISION_TOLERANCE = 2.0;

//	public final static double TURRET_P = 0.05;
//	public final static double TURRET_I = 0.0;
	public final static double TURRET_P = 0.015;
	public final static double TURRET_I = 0.001;
	public final static double TURRET_D = 0.0;

	public final static double TURRET_EDGE = 12000.0;
	public final static double TURRET_SCAN_THRESHOLD = 11000.0;
	public final static double TURRET_SCAN_SPEED = 0.3;
	public final static double TURRET_SETPOINT = 12000.0;

	// shooter constants
	public final static double SHOOTER_CONVERSION_FACTOR = 3.413333333;
	public final static int SHOOTER_TOLERANCE = 100;
	public final static double SHOOTER_SET_SPEED = 7500;
	public final static double SHOOTER_LOW_SPIN = 3000;
	public final static double SHOT_TIME = 2.0;
	// hood constants
	public final static double HOOD_0_POS = -70.0;
	public final static double HOOD_90_POS = 650.0;
	public final static double HOOD_LOW_GOAL_POSITION = 10;
	public final static double HOOD_NORMAL_TARGET_POSITION = 43.0;
	public final static double HOOD_BATTER_CLOSE_POSITION = 67.0;
	public final static double HOOD_BATTER_FAR_POSITION = 61;
	public final static double HOOD_TOLERANCE = 0.25;
	public final static double HOOD_AUTOTARGET_A = 0.1509;
	public final static double HOOD_AUTOTARGET_B = -4.5271;
	public final static double HOOD_AUTOTARGET_C = 69.905;

	// indexer constants
	public final static double INDEXER_POWER = 0.6;
	public final static double PRESSURE_THRESHOLD = 3.0;

	// intake arm constants
	public final static double INTAKE_LEFT_ARM_0_POS = 540.0;
	public final static double INTAKE_LEFT_ARM_90_POS = 880.0;
	public final static double INTAKE_PICKUP_POS = 10.0;
	public final static double INTAKE_VERTICAL_POS = 60.0;
	public final static double INTAKE_DOWN_POS = -58.0;
	public final static double INTAKE_DROP_THRESHOLD = 30.0;
	public final static double INTAKE_ROLL_THRESHOLD = 80.0;

	// OI constants
	public final static double DEADBAND = 0.15;
	public final static double JOYSTICK_POWER = 1.0;

	// vision constants
	public final static String VISION_HOSTNAME = "10.13.6.19";
	public final static int VISION_PORT_NUMBER = 5802;
	public final static double VISION_PERIOD = 0.1;
	public final static double MIN_DISTANCE = 5.0;
	public final static double MAX_DISTANCE = 8.5;

	// autonomous constants
	public final static double LOW_BAR_POWER = 0.75;
	public final static double MOAT_POWER = 1.0;
	public final static double RAMPARTS_POWER = 1.0;
	public final static double TERRAIN_POWER = 0.9;
	public final static double WALL_POWER = 1.0;
	public final static double LOW_BAR_TIME = 3.5;
	public final static double MOAT_TIME = 2.7;
	public final static double RAMPARTS_TIME = 2.8;
	public final static double TERRAIN_TIME = 2.5;
	public final static double WALL_TIME = 2.7;
	public final static double TARGET_TIME = 5.0;
	public final static double SLOW_DRIVE = 0.3;
	/*
	 * ramparts: 2.7
	 * 
	 * 
	 */

}
