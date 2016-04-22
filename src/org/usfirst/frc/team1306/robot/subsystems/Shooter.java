package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem representing the shooter and its controllers. "Shooter" in this
 * case means the flywheel.
 * 
 * @author Finn Voichick, James Tautges
 */
public class Shooter extends Subsystem {

	private final static boolean ENABLED = true;

	/** The Talon SRX that controls the flywheel motor. */
	private final CANTalon flywheel;
	private boolean lowSpin;

	/**
	 * Constructs a new shooter that uses a quadrature encoder as its feedback
	 * device. It controls for speed, making sure that the flywheel is spinning
	 * at the same speed each time it fires. It also has a tolerance that stops
	 * controlling for speed when the speed is within a certain range.
	 */
	public Shooter() {
		if (ENABLED) {

			flywheel = new CANTalon(RobotMap.FLYWHEEL_TALON_PORT);
			lowSpin = false;

			flywheel.reverseSensor(true);
			flywheel.reverseOutput(true);

			SmartDashboard.putNumber("flywheel power", Constants.SHOOTER_SET_SPEED);

			flywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			flywheel.setSafetyEnabled(false);
			flywheel.enableBrakeMode(false);
			flywheel.enable();
			spinDown();

		} else {
			flywheel = null;
		}
	}

	/**
	 * Sets the default command for the Shooter. Nothing is done to the shooter
	 * until commands are called, so no default command must be specified.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Set the Talon to its set speed. This is less than the maximum velocity
	 * because it needs to be able to get to this velocity quickly. Also, it
	 * needs to be consistent and unaffected by battery power.
	 */
	public void spinUp() {
		if (!ENABLED) {
			return;
		}

		if (!flywheel.isEnabled()) {
			System.err.println("Flywheel Talon disconnected");
			return;
		}
		double speed = SmartDashboard.getNumber("flywheel power");
		flywheel.changeControlMode(TalonControlMode.Speed);
		flywheel.set(speed * Constants.SHOOTER_CONVERSION_FACTOR);
	}

	/**
	 * Stop the flywheel Talon. It is put into PercentVbus mode and allowed to
	 * coast to a stop. If it's in low spin mode, then it only coasts to a
	 * slower speed. Note: if in low spin mode, the spinDown method must be
	 * called repeatedly.
	 */
	public void spinDown() {
		if (!ENABLED) {
			return;
		}

		if (lowSpin && flywheel.getSpeed() < Constants.SHOOTER_LOW_SPIN + Constants.SHOOTER_TOLERANCE) {
			flywheel.changeControlMode(TalonControlMode.Speed);
			flywheel.set(Constants.SHOOTER_LOW_SPIN * Constants.SHOOTER_CONVERSION_FACTOR);
		} else {
			flywheel.changeControlMode(TalonControlMode.PercentVbus);
			flywheel.set(0.0);
		}
	}

	public void setLowSpin(boolean lowSpin) {
		if (!ENABLED) {
			return;
		}

		this.lowSpin = lowSpin;
	}

	/**
	 * Gets the current speed of the flywheel in rotations per minute.
	 * 
	 * @return the current flywheel speed.
	 */
	public double getSpeed() {
		if (!ENABLED) {
			return 0.0;
		}

		if (!flywheel.isEnabled()) {
			System.err.println("Flywheel Talon disconnected");
			return 0.0;
		}
		return flywheel.getSpeed() / Constants.SHOOTER_CONVERSION_FACTOR;
	}

	/**
	 * Gets the amperage going through the flywheel motor. Used to see if it's
	 * too high.
	 * 
	 * @return the output current of the flywheel Talon.
	 */
	public double getCurrent() {
		if (!ENABLED) {
			return 0.0;
		}

		if (!flywheel.isEnabled()) {
			System.err.println("Flywheel Talon disconnected");
			return 0.0;
		}
		return flywheel.getOutputCurrent();
	}

	/**
	 * Return whether or not the measured speed is within tolerance of our
	 * target value. This means we can fire when ready.
	 * 
	 * @return Whether or not the measured speed is within tolerance of the
	 *         target
	 */
	public boolean isSpunUp() {
		if (!ENABLED) {
			return false;
		}

		if (!flywheel.isEnabled()) {
			System.err.println("Flywheel Talon disconnected");
			return false;
		}
		return flywheel.getControlMode().equals(TalonControlMode.Speed)
				&& Math.abs(flywheel.getError()) <= Constants.SHOOTER_TOLERANCE * Constants.SHOOTER_CONVERSION_FACTOR;
	}

}
