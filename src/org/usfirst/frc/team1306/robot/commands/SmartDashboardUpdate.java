package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartDashboardUpdate extends CommandBase {

	public SmartDashboardUpdate() {
		// Not sure whether or not to use requires for the drivetrain.
		// I want to access it, but I don't want this to block DriveTank.
		setRunWhenDisabled(true);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// drivetrain
		SmartDashboard.putNumber("leftMotor1.get()", drivetrain.get(0));
		SmartDashboard.putNumber("leftMotor1.getError()", drivetrain.get(0) + drivetrain.getEncVelocity(0));
		SmartDashboard.putNumber("Encoder velocity", drivetrain.getEncVelocity(0));
		
		SmartDashboard.putNumber("rightMotor1.get()", drivetrain.get(2));
		SmartDashboard.putNumber("rightMotor1.getError()", drivetrain.getError(2));
		
		SmartDashboard.putNumber("talon current", shooter.getCurrent());
		SmartDashboard.putNumber("trigger", oi.getRightTrigger());
		
		vision.getData();
		
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
