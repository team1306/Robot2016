package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.DriveTank;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

	
	private final CANTalon[] motors;
	private final CANTalon leftMotor1;
	private final CANTalon leftMotor2;
	private final CANTalon rightMotor1;
	private final CANTalon rightMotor2;

	private final RobotDrive drivetrain;
	private double maxSpeed;

	public Drivetrain() {

		leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
		leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
		rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
		rightMotor2 = new CANTalon(RobotMap.rightTalon1Port);
		
		motors = new CANTalon[]{leftMotor1, leftMotor2, rightMotor1, rightMotor2};
		for (CANTalon motor : motors) {
			setupMotor(motor);
			motor.set(0.0);
		}

		drivetrain = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
		
		SmartDashboard.putNumber("maxSpeed", MAX_SPEED);
		
		SmartDashboard.putNumber("p", P);
		SmartDashboard.putNumber("i", I);
		SmartDashboard.putNumber("d", D);
		SmartDashboard.putNumber("f", F);
		SmartDashboard.putNumber("izone", IZONE);
		SmartDashboard.putNumber("rampRate", RAMP_RATE);
		
	}

	public void driveTank(double leftVel, double rightVel) {
		updateConstants();
		drivetrain.tankDrive(leftVel * maxSpeed, rightVel * maxSpeed);
	}

	public void driveHybrid(double velocity, double rotation) {
		updateConstants();
		drivetrain.arcadeDrive(velocity * maxSpeed, rotation * maxSpeed);
	}

	public void stop() {
		drivetrain.stopMotor();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveTank());
	}

	private void updateConstants() {

		maxSpeed = SmartDashboard.getNumber("maxSpeed", MAX_SPEED);
		double p = SmartDashboard.getNumber("p", P);
		double i = SmartDashboard.getNumber("i", I);
		double d = SmartDashboard.getNumber("d", D);
		double f = SmartDashboard.getNumber("f", F);
		int izone = (int)SmartDashboard.getNumber("izone", IZONE);
		double rampRate = SmartDashboard.getNumber("rampRate", RAMP_RATE);
		
		for (CANTalon motor : motors) {
			motor.setPID(p, i, d, f, izone, rampRate, 0);
		}

	}

	private void setupMotor(CANTalon motor) {
		motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motor.changeControlMode(TalonControlMode.Speed);
		motor.set(0.0);
		motor.enable();
	}
	
	/** All of these are placeholder values. */
	private static double MAX_SPEED = 1.0;
	private static double P = 1.0;
	private static double I = 0.0;
	private static double D = 0.0;
	private static double F = 0.0;
	private static int IZONE = 0;
	private static double RAMP_RATE = 10.0;

}
