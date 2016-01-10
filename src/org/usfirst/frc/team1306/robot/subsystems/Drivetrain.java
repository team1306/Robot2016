package org.usfirst.frc.team1306.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;


import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.DriveTank;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    AHRS ahrs= new AHRS(SPI.Port.kMXP);


	private final CANTalon leftMotor1;
	private final CANTalon leftMotor2;
	private final CANTalon rightMotor1;
	private final CANTalon rightMotor2;
    double angle;
  

	private final RobotDrive drivetrain;

	public Drivetrain() {

		leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
		leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
		rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
		rightMotor2 = new CANTalon(RobotMap.rightTalon1Port);

		drivetrain = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
		
		SmartDashboard.putNumber("p", P);
		SmartDashboard.putNumber("i", I);
		SmartDashboard.putNumber("d", D);
		SmartDashboard.putNumber("f", F);
		SmartDashboard.putNumber("izone", IZONE);
		SmartDashboard.putNumber("rampRate", RAMP_RATE);
		
	}

	public void driveTank(double leftVel, double rightVel) {
		updateConstants();
		drivetrain.tankDrive(leftVel * MAX_SPEED, rightVel * MAX_SPEED);
	}

	public void driveHybrid(double velocity, double rotation) {
		updateConstants();
		drivetrain.arcadeDrive(velocity * MAX_SPEED, rotation * MAX_SPEED);
	}

	public void stop() {
		drivetrain.stopMotor();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveTank());
	}

	private void updateConstants() {

		double p = SmartDashboard.getNumber("p", P);
		double i = SmartDashboard.getNumber("i", I);
		double d = SmartDashboard.getNumber("d", D);
		double f = SmartDashboard.getNumber("f", F);
		int izone = (int)SmartDashboard.getNumber("izone", IZONE);
		double rampRate = SmartDashboard.getNumber("rampRate", RAMP_RATE);
		
		leftMotor1.setPID(p, i, d, f, izone, rampRate, 0);
		leftMotor2.setPID(p, i, d, f, izone, rampRate, 0);
		rightMotor1.setPID(p, i, d, f, izone, rampRate, 0);
		rightMotor2.setPID(p, i, d, f, izone, rampRate, 0);

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
