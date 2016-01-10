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
		drivetrain.tankDrive(leftVel, rightVel);
	}
	
	public void driveHybrid(double velocity, double rotation) {
		drivetrain.arcadeDrive(velocity, rotation);
	}

	public void stop() {
		drivetrain.tankDrive(0.0, 0.0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveTank());
	}
}
