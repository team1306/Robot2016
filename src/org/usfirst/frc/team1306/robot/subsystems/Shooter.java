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

	private CANTalon flywheel;

	/**
	 * Constructs a new shooter that uses a single encoder as its feedback
	 * device. It controls for speed, making sure that the flywheel is spinning
	 * at the same speed each time it fires.
	 */
	public Shooter() {
		flywheel = new CANTalon(RobotMap.flyWheelTalonPort);

		// flywheel.reverseOutput(false);
		// flywheel.reverseSensor(true);

		flywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		flywheel.setSafetyEnabled(false);
		flywheel.enableBrakeMode(false);
		flywheel.setAllowableClosedLoopErr(Constants.SHOOTER_TOLERANCE);
		flywheel.enable();
		spinDown();
	}

	public void initDefaultCommand() {
	}

	/**
	 * Set the Talon to full speed
	 */
	public void spinUp() {
		flywheel.changeControlMode(TalonControlMode.Speed);
		flywheel.set(-Constants.SHOOTER_SET_SPEED * Constants.SHOOTER_MAX_SPEED);
	}

	/**
	 * Stop the flywheel Talon
	 */
	public void spinDown() {
		flywheel.changeControlMode(TalonControlMode.PercentVbus);
		flywheel.set(0.0);
	}

	public double getSpeed() {
		return flywheel.getSpeed() / Constants.SHOOTER_MAX_SPEED;
	}

	public double getCurrent() {
		return flywheel.getOutputCurrent();
	}

	/**
	 * Return whether or not the measured speed is within tolerance of our
	 * target value. This means we can fire when ready
	 * 
	 * @return Whether or not the measured speed is within tolerance of the
	 *         target
	 */
	public boolean isSpunUp() {
		return flywheel.getSpeed() > Constants.SHOOTER_TOLERANCE / 2.0
				&& Math.abs(flywheel.getError()) <= Constants.SHOOTER_TOLERANCE;
	}

}
