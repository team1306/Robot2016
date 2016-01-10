package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.DriveTank;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private final CANTalon leftMotor1;
	private final CANTalon leftMotor2;
	private final CANTalon rightMotor1;
	private final CANTalon rightMotor2;

	private final RobotDrive drivetrain;

	public Drivetrain() {

		leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
		leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
		rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
		rightMotor2 = new CANTalon(RobotMap.rightTalon1Port);

		drivetrain = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);

		leftMotor1.setPID(P, I, D, F, IZONE, CLOSED_LOOP_RAMP_RATE, 0);
		leftMotor2.setPID(P, I, D, F, IZONE, CLOSED_LOOP_RAMP_RATE, 0);
		rightMotor1.setPID(P, I, D, F, IZONE, CLOSED_LOOP_RAMP_RATE, 0);
		rightMotor2.setPID(P, I, D, F, IZONE, CLOSED_LOOP_RAMP_RATE, 0);
	}

	public void driveTank(double leftVel, double rightVel) {
		drivetrain.tankDrive(leftVel * MAX_SPEED, rightVel * MAX_SPEED);
	}

	public void driveHybrid(double velocity, double rotation) {
		drivetrain.arcadeDrive(velocity * MAX_SPEED, rotation * MAX_SPEED);
	}

	public void stop() {
		drivetrain.stopMotor();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveTank());
	}

	/** All of these are placeholder values. */
	private static double MAX_SPEED = 1.0;
	private static double P = 1.0;
	private static double I = 0.0;
	private static double D = 0.0;
	private static double F = 0.0;
	private static int IZONE = 0;
	private static double CLOSED_LOOP_RAMP_RATE = 10.0;

}
