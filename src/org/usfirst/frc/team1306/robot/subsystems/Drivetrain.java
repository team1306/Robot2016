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

	private CANTalon leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
	private CANTalon leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
	private CANTalon rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
	private CANTalon rightMotor2 = new CANTalon(RobotMap.rightTalon1Port);

	private RobotDrive drivetrain = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);

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

	/** The max speed, in encoder ticks/10ms. Currently a placeholder value. */
	private static double MAX_SPEED = 1.0;
}
