package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeArm extends Subsystem {

	private final CANTalon leftMotor;
	private final CANTalon rightMotor;

	public IntakeArm() {

		leftMotor = new CANTalon(RobotMap.intakeMotor1Port);
		rightMotor = new CANTalon(RobotMap.intakeMotor2Port);
		leftMotor.enableBrakeMode(false); // TODO remove this
		rightMotor.enableBrakeMode(false); // TODO remove this
		leftMotor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		rightMotor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		leftMotor.changeControlMode(TalonControlMode.Position);
		rightMotor.changeControlMode(TalonControlMode.Position);
		leftMotor.set(leftMotor.get());
		rightMotor.set(rightMotor.get());

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setPosition(double angle) {
		leftMotor.changeControlMode(TalonControlMode.Position);
		rightMotor.changeControlMode(TalonControlMode.Position);
		leftMotor.set(Constants.INTAKE_LEFT_ARM_0_POS
				+ angle * (Constants.INTAKE_LEFT_ARM_90_POS - Constants.INTAKE_LEFT_ARM_0_POS) / 90.0);
		rightMotor.set(Constants.INTAKE_RIGHT_ARM_0_POS
				+ angle * (Constants.INTAKE_RIGHT_ARM_90_POS - Constants.INTAKE_RIGHT_ARM_0_POS) / 90.0);
	}

}
