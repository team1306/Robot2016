package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmVertical;

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
	private final CANTalon leftMotor;
	/** The talon that lifts the right side of the arm. */
	private final CANTalon rightMotor;

	/**
	 * Constructs a new IntakeArm with two motors. Each Talon is connected to a
	 * potentiometer which is used for PID position control.
	 */
	public IntakeArm() {

		leftMotor = new CANTalon(RobotMap.intakeLeftMotorPort);
		rightMotor = new CANTalon(RobotMap.intakeRightMotorPort);
		leftMotor.enableBrakeMode(false); // TODO remove this
		rightMotor.enableBrakeMode(false); // TODO remove this
		leftMotor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		rightMotor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		leftMotor.changeControlMode(TalonControlMode.Position);
		rightMotor.changeControlMode(TalonControlMode.Position);
		leftMotor.set(leftMotor.get());
		rightMotor.set(rightMotor.get());
		leftMotor.enable();
		rightMotor.enable();

	}

	/**
	 * Sets the default command for the IntakeArm to IntakeArmVertical (because
	 * the arm is vertical at the beginning of the match).
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeArmVertical());
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
		SmartDashboard.putNumber("intake arm error", leftMotor.getError());
		leftMotor.changeControlMode(TalonControlMode.Position);
		rightMotor.changeControlMode(TalonControlMode.Position);
		leftMotor.set(Constants.INTAKE_LEFT_ARM_0_POS
				+ angle * (Constants.INTAKE_LEFT_ARM_90_POS - Constants.INTAKE_LEFT_ARM_0_POS) / 90.0);
		rightMotor.set(Constants.INTAKE_RIGHT_ARM_0_POS
				+ angle * (Constants.INTAKE_RIGHT_ARM_90_POS - Constants.INTAKE_RIGHT_ARM_0_POS) / 90.0);
	}
	
	public double getCurrent() {
		return leftMotor.getOutputCurrent() + rightMotor.getOutputCurrent();
	}

}
