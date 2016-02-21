package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The turret that controls the heading of the shooter relative to the robot.
 * This PIDSubsystem takes the camera image as feedback, and adjusts the turret
 * heading to match.
 * 
 * @author Finn Voichick, James Tautges
 */
public class Turret extends Subsystem {

	private final CANTalon turretTalon;

	/**
	 * Creates a new turret and enables PID position control using the camera
	 * feedback.
	 */
	public Turret() {

		turretTalon = new CANTalon(RobotMap.turretTalonPort);
		turretTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		turretTalon.changeControlMode(TalonControlMode.PercentVbus);
		turretTalon.enableBrakeMode(false);
	}

	/**
	 * Sets the default command for the turret to ResetTurret.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Set the velocity of the turret motor, on a scale from -1.0 to 1.0.
	 * 
	 * @param velocity
	 *            the new velocity
	 */
	public void setVel(double speed) {
		turretTalon.changeControlMode(TalonControlMode.PercentVbus);
		turretTalon.set(speed);
	}

	/**
	 * Set the target position for the turret to straight forward. The turret
	 * will then use its PID position control (using an encoder) to point
	 * forward.
	 * 
	 * @param position
	 *            The intended heading of the turret relative to the robot.
	 */
	public void setTurretForward() {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(0.0);
		turretTalon.enable();
	}

}
