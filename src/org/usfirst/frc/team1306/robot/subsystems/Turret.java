package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The turret that controls the heading of the shooter relative to the robot.
 * This PIDSubsystem takes the camera image as feedback, and adjusts the turret
 * heading to match.
 * 
 * @author Finn Voichick, James Tautges
 */
public class Turret extends PIDSubsystem {

	private final CANTalon turretTalon;

	/**
	 * Creates a new turret and enables PID position control using the camera
	 * feedback.
	 */
	public Turret() {
		super("Turret PID", Constants.TURRET_P, Constants.TURRET_I, Constants.TURRET_D);
		//setAbsoluteTolerance(Constants.TURRET_TOLERANCE);

		turretTalon = new CANTalon(RobotMap.turretTalonPort);
		turretTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		turretTalon.changeControlMode(TalonControlMode.PercentVbus);
		turretTalon.enableBrakeMode(false);
		setSetpoint(0.0);
		//enable();
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
		setPosition(0.0);
	}
	
	public void setRight() {
		setPosition(Constants.TURRET_EDGE);
	}
	
	public void setLeft() {
		setPosition(-Constants.TURRET_EDGE);
	}
	
	public boolean isRight() {
		return turretTalon.getPosition() > Constants.TURRET_SCAN_THRESHOLD;
	}
	
	public boolean isLeft() {
		return turretTalon.getPosition() < -Constants.TURRET_SCAN_THRESHOLD;
	}
	
	private void setPosition(double position) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(position);
		turretTalon.enable();
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
		double yaw = Vision.getData().getYaw();
//		boolean inRange = Vision.canSeeTarget();
//		if (inRange) {
			return -yaw;
//		} else {
//			return getSetpoint();
//		}
	}

	/**
	 * Sets the velocity of the turret based on the output of the PID loop.
	 */
	protected void usePIDOutput(double output) {
		turretTalon.set(output);
		//setVel(output);
	}
}
