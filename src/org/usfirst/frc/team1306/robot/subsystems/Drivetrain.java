package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveTank;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The subsystem representing the drivetrain and its motors
 * 
 * @author James Tautges, Finn Voichick
 */
public class Drivetrain extends Subsystem {

	private final CANTalon[] motors;
	private final CANTalon leftMotor1;
	private final CANTalon leftMotor2;
	private final CANTalon rightMotor1;
	private final CANTalon rightMotor2;

	private final DoubleSolenoid shifter;

	public Drivetrain() {

		leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
		leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
		rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
		rightMotor2 = new CANTalon(RobotMap.rightTalon2Port);

		motors = new CANTalon[] { leftMotor1, leftMotor2, rightMotor1, rightMotor2 };
		setupMotors(leftMotor1, leftMotor2);
		setupMotors(rightMotor1, rightMotor2);

		SmartDashboard.putNumber("maxSpeed", Constants.MAX_SPEED);

		shifter = new DoubleSolenoid(RobotMap.shifterForwardChannel, RobotMap.shifterReverseChannel);

		shifter.set(Value.kForward);

	}

	/**
	 * Takes two values from -1.0 to 1.0 for the right and left motors
	 * 
	 * @param leftVel
	 *            Speed of left motor
	 * @param rightVel
	 *            Speed of right motor
	 */
	public void driveTank(double leftVel, double rightVel) {
		double aveVel = (leftMotor1.get() + rightMotor1.get()) / 2.0 / Constants.MAX_SPEED;
		if (isHighGear()) {
			if (aveVel / Constants.GEAR_RATIO < Constants.FALLING_SHIFT_SPEED_THRESHOLD) {
				shiftDown();
			}
		} else {
			if (aveVel / Constants.GEAR_RATIO > Constants.RISING_SHIFT_SPEED_THRESHOLD) {
				shiftUp();
			}
		}
		if (isHighGear()) {
			leftMotor1.set(-leftVel * Constants.MAX_SPEED);
			rightMotor1.set(rightVel * Constants.MAX_SPEED);
		} else {
			leftMotor1.set(-leftVel * Constants.MAX_SPEED * Constants.GEAR_RATIO);
			rightMotor1.set(rightVel * Constants.MAX_SPEED * Constants.GEAR_RATIO);
		}
	}

	/**
	 * Takes values from -1.0 to 1.0 for velocity and rotation
	 * 
	 * @param velocity
	 *            Base speed forward
	 * @param rotation
	 *            Additional rotational rate
	 */
	public void driveHybrid(double velocity, double rotation) {
		double maxSpeed = SmartDashboard.getNumber("maxSpeed");
		double leftVel = maxSpeed * (velocity + rotation);
		double rightVel = maxSpeed * (velocity - rotation);

		leftMotor1.set(leftVel);
		rightMotor1.set(rightVel);
	}

	/**
	 * Sets zero speed to all motors
	 */
	public void stop() {
		leftMotor1.set(0.0);
		rightMotor1.set(0.0);
	}

	/**
	 * Start the default tank drive command to start driving
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveTank());
	}

	/**
	 * Configure all of the Talons with one as the PID controlled master and the
	 * other as a following slave. This also configures the parity of the output
	 * and the sensor value
	 * 
	 * @param master
	 *            PID controlled main Talon
	 * @param slave
	 *            First follower controller
	 */
	private void setupMotors(CANTalon master, CANTalon slave) {
		/*
		 * master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		 * master.changeControlMode(TalonControlMode.Speed);
		 * master.reverseSensor(true);
		 */
		master.changeControlMode(TalonControlMode.PercentVbus);
		master.set(0.0);
		master.enable();

		// slave.changeControlMode(TalonControlMode.Follower);
		// slave.set(master.getDeviceID());
	}

	/**
	 * Put both motors into high gear
	 */
	public void shiftUp() {
		shifter.set(Value.kReverse);
	}

	/**
	 * Put both motors into low gear
	 */
	public void shiftDown() {
		shifter.set(Value.kForward);
	}

	private boolean isHighGear() {
		return shifter.get().equals(Value.kReverse);
	}

	/**
	 * Get the value passed to the motor controller with the given index. (ie
	 * leftMotor1 = 0, leftMotor2 = 1, rightMotor1 = 2, rightMotor2 = 3)
	 * 
	 * @param motor
	 *            Index of the Talon to read
	 * @return Value set to the given Talon
	 */
	public double get(int motor) {
		return motors[motor].get();
	}

	/**
	 * Get the PID error of the motor controller with the given index.
	 * 
	 * @see get(int motor)
	 * @param motor
	 *            Index of the Talon to read
	 * @return PID error of given Talon
	 */
	public double getError(int motor) {
		return motors[motor].getError();
	}

	/**
	 * Get the encoder velocity for the given Talon
	 * 
	 * @param motor
	 *            Index of the Talon to read
	 * @return Encoder velocity in ticks per 10ms
	 */
	public double getEncVelocity(int motor) {
		return motors[motor].getEncVelocity();
	}

}
