package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem representing the shooter and its controllers
 * 
 * @author James Tautges
 */
public class Shooter extends Subsystem {

	private CANTalon flywheel;

	private double target = 0;

	public Shooter() {
		flywheel = new CANTalon(RobotMap.flyWheelTalonPort);

		flywheel.reverseOutput(true);
		flywheel.reverseSensor(true);

		flywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		flywheel.changeControlMode(TalonControlMode.Speed);
		flywheel.enable();
	}

	public void initDefaultCommand() {
	}

	/**
	 * Set the target speed for the flywheel Talon. This value should be in
	 * ticks per 10ms
	 * 
	 * @param speed
	 *            Speed to set the Talon
	 */

	public void spinTo(double speed) {
		flywheel.set(speed);
		target = speed;
	}

	/**
	 * Set the Talon to full speed
	 */

	public void spinUp() {
		spinTo(1.0);
	}

	/**
	 * Stop the flywheel Talon
	 */

	public void spinDown() {
		spinTo(0.0);
	}

	/**
	 * Return whether or not the measured speed is within tolerance of our
	 * target value. This means we can fire when ready
	 * 
	 * @return Whether or not the measured speed is within tolerance of the
	 *         target
	 */

	public boolean isSpunUp() {
		return Math.abs(flywheel.getEncVelocity() - target) < Constants.HOOD_TOLERANCE;
	}

}
