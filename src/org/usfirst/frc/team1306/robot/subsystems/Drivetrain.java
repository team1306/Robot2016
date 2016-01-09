package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private RobotDrive drivetrain = new RobotDrive(RobotMap.leftTalon1Port, RobotMap.leftTalon2Port,
			RobotMap.rightTalon1Port, RobotMap.rightTalon2Port);
	
	public void driveTank(double rightVel, double leftVel) {
		drivetrain.tankDrive(leftVel, rightVel);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TankDrive());
	}
}
