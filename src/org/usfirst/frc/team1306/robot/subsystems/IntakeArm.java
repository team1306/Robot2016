package org.usfirst.frc.team1306.robot.subsystems;

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

		leftMotor = new CANTalon(RobotMap.leftIntakeTalonPort);
		rightMotor = new CANTalon(RobotMap.rightIntakeTalonPort);
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

}
