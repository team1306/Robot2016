package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveTank;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem representing the drivetrain and its motors
 * 
 * @author James Tautges
 */
public class Drivetrain extends Subsystem {

	private final static boolean ENABLED = false;

	private final CANTalon[] motors;
	private final CANTalon leftMotor1;
	private final CANTalon leftMotor2;
	private final CANTalon rightMotor1;
	private final CANTalon rightMotor2;

	private final DoubleSolenoid leftShifter;
	private final DoubleSolenoid rightShifter;

	public Drivetrain() {
		if (ENABLED) {

			leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
			leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
			rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
			rightMotor2 = new CANTalon(RobotMap.rightTalon2Port);

			motors = new CANTalon[] { leftMotor1, leftMotor2, rightMotor1, rightMotor2 };
			setupMotors(leftMotor1, leftMotor2);
			setupMotors(rightMotor1, rightMotor2);

			leftShifter = new DoubleSolenoid(0, 1);
			rightShifter = new DoubleSolenoid(2, 3);

			shiftDown();

		} else {
			leftMotor1 = null;
			leftMotor2 = null;
			rightMotor1 = null;
			rightMotor2 = null;
			motors = null;
			leftShifter = null;
			rightShifter = null;
		}
	}

	/**
	 * Takes two values from -1.0 to 1.0 for the right and left motors.
	 * 
	 * @param leftVel
	 *            Speed of left motor
	 * @param rightVel
	 *            Speed of right motor
	 */
	public void driveTank(double leftVel, double rightVel) {
		if (ENABLED) {

			leftMotor1.changeControlMode(TalonControlMode.PercentVbus);
			rightMotor1.changeControlMode(TalonControlMode.PercentVbus);
			leftMotor1.set(-leftVel);
			rightMotor1.set(rightVel);

		}
	}

	/**
	 * Sets zero speed to all motors.
	 */
	public void stop() {
		if (ENABLED) {

			leftMotor1.set(0.0);
			rightMotor1.set(0.0);

		}
	}

	/**
	 * Start the default tank drive command to start driving.
	 */
	public void initDefaultCommand() {
		if (ENABLED) {

			setDefaultCommand(new DriveTank());

		}
	}

	/**
	 * Configure all of the Talons with one as the PID controlled master and the
	 * other as a following slave. This also configures the parity of the output
	 * and the sensor value.
	 * 
	 * @param master
	 *            PID controlled main Talon.
	 * @param slave
	 *            First follower controller.
	 */
	private void setupMotors(CANTalon master, CANTalon slave) {
		if (ENABLED) {

			master.changeControlMode(TalonControlMode.PercentVbus);
			master.set(0.0);

			slave.changeControlMode(TalonControlMode.Follower);
			slave.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			slave.set(master.getDeviceID());
			slave.enable();

		}
	}

	/**
	 * Put both motors into high gear.
	 */
	public void shiftUp() {
		if (ENABLED) {

			leftShifter.set(DoubleSolenoid.Value.kForward);
			rightShifter.set(DoubleSolenoid.Value.kForward);
			leftMotor1.setProfile(1);
			rightMotor1.setProfile(1);

		}
	}

	/**
	 * Put both motors into low gear.
	 */
	public void shiftDown() {
		if (ENABLED) {

			leftShifter.set(DoubleSolenoid.Value.kReverse);
			rightShifter.set(DoubleSolenoid.Value.kReverse);
			leftMotor1.setProfile(0);
			rightMotor1.setProfile(0);

		}
	}

	/**
	 * Gets the sum of the current going through the left two motors. This value
	 * should be less than 90 (45 amps for each one).
	 * 
	 * @return the sum of the current going through the left two motors.
	 */
	public double getLeftCurrent() {
		if (ENABLED) {

			return leftMotor1.getOutputCurrent() + leftMotor2.getOutputCurrent();

		} else {
			return 0.0;
		}
	}

	/**
	 * Gets the sum of the current going through the right two motors. This
	 * value should be less than 90 (45 amps for each one).
	 * 
	 * @return the sum of the current going through the right two motors.
	 */
	public double getRightCurrent() {
		if (ENABLED) {

			return rightMotor1.getOutputCurrent() + rightMotor2.getOutputCurrent();

		} else {
			return 0.0;
		}
	}

	/**
	 * Get the value passed to the motor controller with the given index. (ie
	 * leftMotor1 = 0, leftMotor2 = 1, rightMotor1 = 2, rightMotor2 = 3). Useful
	 * for debugging.
	 * 
	 * @param motor
	 *            Index of the Talon to read.
	 * @return Value set to the given Talon.
	 */
	public double get(int motor) {
		if (ENABLED) {

			return motors[motor].get();

		} else {
			return 0.0;
		}
	}

	/**
	 * Get the PID error of the motor controller with the given index. Useful
	 * for debugging.
	 * 
	 * @see get(int motor)
	 * @param motor
	 *            Index of the Talon to read.
	 * @return PID error of given Talon.
	 */
	public double getError(int motor) {
		if (ENABLED) {

			return motors[motor].getError();

		} else {
			return 0.0;
		}
	}

	/**
	 * Get the encoder velocity for the given Talon. Useful for debugging.
	 * 
	 * @param motor
	 *            Index of the Talon to read.
	 * @return Encoder velocity in ticks per 10ms.
	 */
	public double getEncVelocity(int motor) {
		if (ENABLED) {

			return motors[motor].getEncVelocity();

		} else {
			return 0.0;
		}
	}

}
