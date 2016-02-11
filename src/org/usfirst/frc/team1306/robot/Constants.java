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
	public final static double MAX_ACCELERATION = 0.01;
	public final static double MAX_SPEED = 500.0;

	// turret constants
	public final static double TURRET_MAX_SPEED = 1.0; // placeholder
	public final static double TURRET_TICKS_PER_DEGREE = 100.0;
	public final static double TURRET_TOLERANCE = 2.0; // placeholder
	
	public final static double TURRET_P = 0.02;
	public final static double TURRET_I = 0.001; // 0.001
	public final static double TURRET_D = 0.0;

	public final static double SHOOTER_MAX_SPEED = 500.0; // placeholder
	public final static double SHOOTER_TOLERANCE = 0.05; // placeholder
	
	// hood constants
	public final static double HOOD_VOLTS_PER_DEGREE = 0.1; // placeholder
	public final static double HOOD_LOW_GOAL_POSITION = 10.0; // placeholder
	public final static double HOOD_TOLERANCE = 1.0; // placeholder

	// intake constants
	public final static double INTAKE_LENGTH = 14.0; // placeholder
	public final static double TUSK_LENGTH = 5.0; // placeholder

	// OI constants
	public final static double DEADBAND = 0.15;

	// vision constants
	public final static double VISION_PERIOD = 0.1;
}
