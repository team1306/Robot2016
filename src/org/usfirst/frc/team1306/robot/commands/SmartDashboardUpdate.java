package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartDashboardUpdate extends CommandBase {

	public SmartDashboardUpdate() {
		// Not sure whether or not to use requires for the drivetrain.
		// I want to access it, but I don't want this to block DriveTank.
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// drivetrain
		SmartDashboard.putNumber("leftMotor1.get()", drivetrain.get(0));
		SmartDashboard.putNumber("leftMotor1.getError()", drivetrain.getError(0));
		SmartDashboard.putNumber("Encoder velocity", drivetrain.getEncVelocity(0));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}