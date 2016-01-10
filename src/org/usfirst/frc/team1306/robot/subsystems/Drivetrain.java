package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.DriveTank;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private RobotDrive drivetrain = new RobotDrive(RobotMap.leftTalon1Port, RobotMap.leftTalon2Port,
			RobotMap.rightTalon1Port, RobotMap.rightTalon2Port);

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
