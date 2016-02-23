package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * The turret that controls the heading of the shooter relative to the robot.
 * This PIDSubsystem takes the camera image as feedback, and adjusts the turret
 * heading to match. It uses PID in two distinct ways. One PID loop is run here,
 * on this PIDSubsystem, taking the camera image as feedback and setting the
 * throttle on the motor accordingly. The TalonSRX also uses the encoder to run
 * its own PID loop that resets the turret to the forward position when it isn't
 * targeting.
 * 
 * @author Finn Voichick, James Tautges
 */
public class Turret extends PIDSubsystem {

	/** The Talon SRX that controls the turret motor. */
	private final CANTalon turretTalon;

	/**
	 * Creates a new turret with the PID constants set in the Constants file and
	 * the right settings.
	 */
	public Turret() {
		super("Turret PID", Constants.TURRET_P, Constants.TURRET_I, Constants.TURRET_D);
		setAbsoluteTolerance(Constants.TURRET_VISION_TOLERANCE);

		turretTalon = new CANTalon(RobotMap.turretTalonPort);
		turretTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		turretTalon.changeControlMode(TalonControlMode.PercentVbus);
		turretTalon.enableBrakeMode(false);
		setSetpoint(0.0);

	}

	/**
	 * Sets the default command for the Turret. Nothing is done to the turret
	 * until commands are called, so no default command must be specified.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Set the velocity of the turret motor, on a scale from -1.0 to 1.0. This
	 * doesn't use PID control; it just directly sets the throttle.
	 * 
	 * @param velocity
	 *            the new velocity.
	 */
	public void setVel(double speed) {
		turretTalon.changeControlMode(TalonControlMode.PercentVbus);
		turretTalon.set(-speed);
	}

	/**
	 * Set the target position for the turret to straight forward (encoder
	 * position 0). The Talon will then use its PID position control to point
	 * forward.
	 */
	public void setTurretForward() {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(0.0);
		turretTalon.enable();
	}

	/**
	 * Determines whether the turret is all the way to the right. This is used
	 * when scanning back and forth, so it knows when to change direction.
	 * 
	 * @return true if the turret is far to the right, otherwise false.
	 */
	public boolean isRight() {
		return turretTalon.getPosition() > Constants.TURRET_SCAN_THRESHOLD;
	}

	/**
	 * Determines whether the turret is all the way to the left. This is used
	 * when scanning back and forth, so it knows when to change direction.
	 * 
	 * @return true if the turret is far to the left, otherwise false.
	 */
	public boolean isLeft() {
		return turretTalon.getPosition() < -Constants.TURRET_SCAN_THRESHOLD;
	}

	/**
	 * Gets the input from the camera that gives the angle of the target
	 * relative to the current position. This is the difference between the
	 * intended position and the current position. Because this is already the
	 * error, the setpoint stays at zero.
	 * 
	 * @return The position of the target relative to the robot, or 0.0 if it
	 *         can't be seen.
	 */
	protected double returnPIDInput() {
		return -Vision.getData().getYaw();
	}

	/**
	 * Sets the velocity of the turret based on the output of the PID loop.
	 */
	protected void usePIDOutput(double output) {
		turretTalon.changeControlMode(TalonControlMode.PercentVbus);
		turretTalon.set(output);
	}
}
