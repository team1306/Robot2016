package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.intake.IntakePosition;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A Subsystem that controls the intake arm. This arm can be lowered, allowing
 * it to contact the ball. The left side is controlled using PID position
 * control, and the right motor is its slave.
 * 
 * @author Finn Voichick
 */
public class IntakeArm extends Subsystem {

	private static final boolean ENABLED = false;

	/** The talon that lifts the left side of the arm. */
	private final CANTalon motor;
	/** The talon that lifts the right side of the arm. */
	private final CANTalon slave;

	/**
	 * Constructs a new IntakeArm with two motors. The left Talon is connected
	 * to a potentiometer which is used for PID position control. The right
	 * motor (the slave) is reversed because it is facing the opposite
	 * direction.
	 */
	public IntakeArm() {
		if (ENABLED) {

			motor = new CANTalon(RobotMap.INTAKE_ARM_LEFT_TALON_PORT);
			slave = new CANTalon(RobotMap.INTAKE_ARM_RIGHT_TALON_PORT);
			motor.setFeedbackDevice(FeedbackDevice.AnalogPot);
			motor.changeControlMode(TalonControlMode.Position);
			motor.enable();
			slave.changeControlMode(TalonControlMode.Follower);
			slave.set(motor.getDeviceID());
			slave.reverseOutput(true);
			slave.enable();

		} else {
			motor = null;
			slave = null;
		}
	}

	/**
	 * Sets the default command for the IntakeArm. Nothing is done to the intake
	 * arm until commands are called, so no default command must be specified.
	 */
	@Override
	public void initDefaultCommand() {
	}

	/**
	 * Sets the position of the intake arm to a specified angle. 0 is
	 * horizontal, and 90 is vertical, so a negative number means that the
	 * intake is angled downward.
	 * 
	 * @param angle
	 *            The new setpoint for the arm.
	 */
	public void setPosition(IntakePosition position) {
		if (!ENABLED) {
			return;
		}

		motor.changeControlMode(TalonControlMode.Position);
		motor.enableBrakeMode(true);
		double setpoint = Constants.INTAKE_LEFT_ARM_0_POS
				+ position.getPosition() * (Constants.INTAKE_LEFT_ARM_90_POS - Constants.INTAKE_LEFT_ARM_0_POS) / 90.0;
		motor.set(setpoint);

	}

	/**
	 * Gets the position of the intake arm on the same scale as setPosition.
	 * 
	 * @return the current angle of the intake arm
	 */
	public double getPosition() {
		if (!ENABLED) {
			return 0.0;
		}

		return 90.0 * (motor.getPosition() - Constants.INTAKE_LEFT_ARM_0_POS)
				/ (Constants.INTAKE_LEFT_ARM_90_POS - Constants.INTAKE_LEFT_ARM_0_POS);

	}

	/**
	 * Gets whether the intake arm is vertical. If it's vertical, it the turret
	 * shouldn't turn and the rollers shouldn't spin.
	 * 
	 * @return true if the intake arm is vertical, otherwise false.
	 */
	public boolean isVertical() {
		if (!ENABLED) {
			return false;
		}

		return getPosition() > Constants.INTAKE_ROLL_THRESHOLD;

	}

	/**
	 * Releases brakes on the intake motors. The motors are put into coast mode
	 * with zero throttle.
	 */
	public void releaseBrakes() {
		if (!ENABLED) {
			return;
		}

		motor.enableBrakeMode(false);
		motor.changeControlMode(TalonControlMode.PercentVbus);
		motor.set(0.0);

	}

	/**
	 * Gets the sum of the amperages going through the two motors.
	 * 
	 * @return the sum of the current going through the two motors.
	 */
	public double getCurrent() {
		if (!ENABLED) {
			return 0.0;
		}

		return motor.getOutputCurrent() + slave.getOutputCurrent();

	}

}
