package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.ManualTarget;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {

	private final CANTalon turretTalon;
	private final CANTalon hoodTalon;

	public Turret() {

		// Configure the turret Talon with an encoder and position control
		turretTalon = new CANTalon(RobotMap.turretTalonPort);
		turretTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get());
		turretTalon.enable();

		// Configure the hood Talon with an encoder and position control
		hoodTalon = new CANTalon(RobotMap.hoodTalonPort);
		hoodTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.set(hoodTalon.get());
		hoodTalon.enable();

	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualTarget());
	}

	/**
	 * Set the velocity of the turret motor, on a scale from -1.0 to 1.0
	 * 
	 * @param velocity
	 *            the new velocity
	 */

	public void setVel(double velocity) {
		turretTalon.changeControlMode(TalonControlMode.Speed);
		turretTalon.set(velocity * Constants.TURRET_MAX_SPEED);
	}

	/**
	 * Set the target position of the turret measured in encoder ticks
	 * 
	 * @param position
	 *            Target position in encoder ticks
	 */

	public void setTarget(double position) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(position);
	}

	/**
	 * Set the target relative angle from the current angle.
	 * 
	 * @param angle
	 *            Target relative angle
	 */

	public void setTargetRelative(double angle) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get() + angle);
	}

	/**
	 * Stop the turret
	 */

	public void stop() {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get());
	}

	/**
	 * Return whether or not the turret is within tolerance of our target angle
	 * 
	 * @return Whether or not the turret is on target
	 */

	public boolean onTarget() {
		return turretTalon.getControlMode().equals(TalonControlMode.Position)
				&& turretTalon.getError() < Constants.TURRET_TOLERANCE;
	}

	/**
	 * Set the target position of the hood
	 * 
	 * @param position
	 *            Position of the hood in encoder ticks
	 */

	public void setHoodHeight(double position) {
		hoodTalon.set(position);
	}
}
