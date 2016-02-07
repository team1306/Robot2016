package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The turret that controls the heading of the shooter relative to the robot.
 * This subsystem has methods for controlling both the position and the velocity
 * of the motor.
 * 
 * @author Finn Voichick
 */
public class Turret extends Subsystem {

	private final CANTalon turretTalon;

	/**
	 * Creates a new turret and enables PID position control using a quadrature
	 * encoder.
	 */
	public Turret() {

		// Configure the turret Talon with an encoder and position control
		turretTalon = new CANTalon(RobotMap.turretTalonPort);
		turretTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get());
		turretTalon.enable();

	}

	/**
	 * Sets the default command for the turret to ResetTarget.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ResetTurret());
	}

	/**
	 * Set the velocity of the turret motor, on a scale from -1.0 to 1.0.
	 * 
	 * @param velocity
	 *            the new velocity
	 */

	public void setVel(double velocity) {
		turretTalon.changeControlMode(TalonControlMode.Speed);
		turretTalon.set(velocity * Constants.TURRET_MAX_SPEED);
	}

	/**
	 * 
	 * Set the target position for the turret. 0 is straight forward, and the
	 * angle is measured in degrees.
	 * 
	 * @param position
	 *            The intended heading of the turret relative to the robot.
	 */
	public void setTarget(double position) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(position * Constants.TURRET_TICKS_PER_DEGREE);
	}

	/**
	 * Set the target position for the turret, relative to its current position.
	 * Measured in degrees.
	 * 
	 * @param angle
	 *            The intended angular displacement.
	 */
	public void setTargetRelative(double angle) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get() + angle * Constants.TURRET_TICKS_PER_DEGREE);
	}

	/**
	 * Stop the motor by setting its target position to its current position.
	 */
	public void stop() {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get());
	}

	/**
	 * Finds whether the turret is on target. The turret is on target if it is
	 * within the TURRET_TOLERANCE specified in Constants. If the turret is
	 * being controlled manually, it can't be considered on target.
	 * 
	 * @return true if the turret is on target, otherwise false.
	 */
	public boolean onTarget() {
		return turretTalon.getControlMode().equals(TalonControlMode.Position)
				&& turretTalon.getError() < Constants.TURRET_TOLERANCE;
	}

}
