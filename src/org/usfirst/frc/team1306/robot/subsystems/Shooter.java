package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem representing the shooter and its controllers. "Shooter" in this
 * case means the flywheel.
 * 
 * @author Finn Voichick, James Tautges
 */
public class Shooter extends Subsystem {

	private CANTalon flywheel;

	/**
	 * Constructs a new shooter that uses a single encoder as its feedback
	 * device. It controls for speed, making sure that the flywheel is spinning
	 * at the same speed each time it fires.
	 */
	public Shooter() {
		flywheel = new CANTalon(RobotMap.flyWheelTalonPort);

		flywheel.reverseOutput(true);
		flywheel.reverseSensor(true);

		flywheel.setFeedbackDevice(FeedbackDevice.EncRising);
		flywheel.changeControlMode(TalonControlMode.Speed);
		flywheel.enableBrakeMode(false);
		flywheel.enable();
	}

	public void initDefaultCommand() {
	}

	/**
	 * Set the target speed for the flywheel Talon, on a scale from 0.0 to 1.0.
	 * 
	 * @param speed
	 *            Speed to set the Talon
	 */
	public void set(double speed) {
		flywheel.set(Math.abs(speed) * Constants.SHOOTER_MAX_SPEED);
	}

	/**
	 * Set the Talon to full speed
	 */
	public void spinUp() {
		set(1.0);
	}

	/**
	 * Stop the flywheel Talon
	 */
	public void spinDown() {
		set(0.0);
	}

	/**
	 * Return whether or not the measured speed is within tolerance of our
	 * target value. This means we can fire when ready
	 * 
	 * @return Whether or not the measured speed is within tolerance of the
	 *         target
	 */
	public boolean onTarget() {
		return Math.abs(flywheel.getError()) < Constants.SHOOTER_TOLERANCE;
	}

}
