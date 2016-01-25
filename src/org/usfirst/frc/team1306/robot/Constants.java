package org.usfirst.frc.team1306.robot;

public class Constants {
	// drive constants
	// measured in encoder ticks per 1ms^2
	public final static double MAX_ACCELERATION = 0.01;
	public final static double MAX_SPEED = 500.0;

	// turret constants
	public final static double TURRET_MAX_SPEED = 1.0; // placeholder
	public final static double TURRET_TICKS_PER_DEGREE = 100.0;
	public final static double TURRET_TOLERANCE = 1.0; // placeholder

	public final static double HOOD_TICKS_PER_DEGREE = 100.0; // placeholder
	public final static double HOOD_TOLERANCE = 1.0;

	// OI constants
	public final static double DEADBAND = 0.15;
}
