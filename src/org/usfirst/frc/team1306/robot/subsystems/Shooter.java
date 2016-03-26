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

	/** The Talon SRX that controls the flywheel motor. */
	private CANTalon flywheel;

	/**
	 * Constructs a new shooter that uses a quadrature encoder as its feedback
	 * device. It controls for speed, making sure that the flywheel is spinning
	 * at the same speed each time it fires. It also has a tolerance that stops
	 * controlling for speed when the speed is within a certain range.
	 */
	public Shooter() {
		flywheel = new CANTalon(RobotMap.flyWheelTalonPort);

		// flywheel.reverseOutput(false);
		// flywheel.reverseSensor(true);
		
		SmartDashboard.putNumber("flywheel power", Constants.SHOOTER_SET_SPEED);

		flywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		flywheel.setSafetyEnabled(false);
		flywheel.enableBrakeMode(false);
		flywheel.setAllowableClosedLoopErr(Constants.SHOOTER_TOLERANCE);
		flywheel.enable();
		spinDown();
	}

	/**
	 * Sets the default command for the Shooter. Nothing is done to the shooter
	 * until commands are called, so no default command must be specified.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Set the Talon to its set speed. In this case, it's 95% of the maximum
	 * velocity so that it doesn't drop over the course of a match
	 */
	public void spinUp() {
		double speed = SmartDashboard.getNumber("flywheel power");
		flywheel.changeControlMode(TalonControlMode.Speed);
//		flywheel.set(-Constants.SHOOTER_SET_SPEED * Constants.SHOOTER_MAX_SPEED);
		flywheel.set(-speed * Constants.SHOOTER_MAX_SPEED);
	}

	/**
	 * Stop the flywheel Talon. It is put into PercentVbus mode and allowed to
	 * coast to a stop.
	 */
	public void spinDown() {
		flywheel.changeControlMode(TalonControlMode.PercentVbus);
		flywheel.set(0.0);
	}

	/**
	 * Gets the current speed of the flywheel, on a scale from 0.0 to 1.0.
	 * 
	 * @return the current flywheel speed.
	 */
	public double getSpeed() {
		return -flywheel.getSpeed() / Constants.SHOOTER_MAX_SPEED;
	}

	/**
	 * Gets the amperage going through the flywheel motor. Used to see if it's
	 * too high.
	 * 
	 * @return the output current of the flywheel Talon.
	 */
	public double getCurrent() {
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
		return getSpeed() > 0.5 && Math.abs(flywheel.getError()) <= Constants.SHOOTER_TOLERANCE;
	}

}
