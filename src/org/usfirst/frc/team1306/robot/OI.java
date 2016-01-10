package org.usfirst.frc.team1306.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private final XboxController xbox;

	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
	}

	public double getRight() {
		return deadband(xbox.getY(Hand.kRight));
	}

	public double getLeft() {
		return deadband(xbox.getY(Hand.kLeft));
	}

	public double getLeftTrigger() {
		return xbox.getLT();
	}

	public double getRightTrigger() {
		return xbox.getRT();
	}

	public double getLeftX() {
		return deadband(xbox.getX(Hand.kLeft));
	}
	public int getPOV() {
		return xbox.getPOV();
	}
	
	private static double deadband(double value) {
		if (value < -DEADBAND) {
			return (value + DEADBAND) / (1.0 - DEADBAND);
		}
		if (value > DEADBAND) {
			return (value - DEADBAND) / (1.0 - DEADBAND);
		}
		return 0;
	}
	private static final double DEADBAND = 0.15;
}
