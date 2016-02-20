package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A Subsystem that controls the intake arm. This arm can be lowered, allowing
 * it to contact the ball. Both sides are controlled individually because there
 * wasn't a good place to put a single axle.
 * 
 * @author Finn Voichick
 */
public class IntakeArm extends Subsystem {

	/** The talon that lifts the left side of the arm. */
	private final CANTalon motor;
	/** The talon that lifts the right side of the arm. */
	private final CANTalon slave;

	/**
	 * Constructs a new IntakeArm with two motors. Each Talon is connected to a
	 * potentiometer which is used for PID position control.
	 */
	public IntakeArm() {

		motor = new CANTalon(RobotMap.intakeLeftMotorPort);
		slave = new CANTalon(RobotMap.intakeRightMotorPort);
		motor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		motor.changeControlMode(TalonControlMode.Position);
		motor.enable();
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(motor.getDeviceID());
		slave.reverseOutput(true);
		setPosition(getPosition());

	}

	/**
	 * Sets the default command for the IntakeArm to IntakeArmVertical (because
	 * the arm is vertical at the beginning of the match).
	 */
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
	public void setPosition(double angle) {
		motor.changeControlMode(TalonControlMode.Position);
		motor.enableBrakeMode(true);
		double left = Constants.INTAKE_LEFT_ARM_0_POS
				+ angle * (Constants.INTAKE_LEFT_ARM_90_POS - Constants.INTAKE_LEFT_ARM_0_POS) / 90.0;
//		double right = -(Constants.INTAKE_RIGHT_ARM_0_POS
//				+ angle * (Constants.INTAKE_RIGHT_ARM_90_POS - Constants.INTAKE_RIGHT_ARM_0_POS) / 90.0);
//		motor.set((left + right) / 2.0);
		motor.set(left);
	}

	public double getPosition() {
		double left = 90.0 * (motor.getPosition() - Constants.INTAKE_LEFT_ARM_0_POS)
				/ (Constants.INTAKE_LEFT_ARM_90_POS - Constants.INTAKE_LEFT_ARM_0_POS);
//		double right = 90.0 * (slave.getPosition() - Constants.INTAKE_RIGHT_ARM_0_POS)
//				/ (Constants.INTAKE_RIGHT_ARM_90_POS - Constants.INTAKE_RIGHT_ARM_0_POS);
//		return (left + right) / 2.0;
		return left;
	}

	public void releaseBrakes() {
		motor.enableBrakeMode(false);
		slave.enableBrakeMode(false);
		motor.changeControlMode(TalonControlMode.PercentVbus);
		slave.changeControlMode(TalonControlMode.PercentVbus);
		motor.set(0.0);
		slave.set(0.0);
	}

	public double getCurrent() {
		return motor.getOutputCurrent() + slave.getOutputCurrent();
	}

}
