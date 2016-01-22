package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 *
 */
public class Fire extends CommandBase {

	public Fire() {
		requires(shooter);
		// requires(intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		shooter.spinUp();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;//shooter.isSpunUp();
	}

	// Called once after isFinished returns true
	protected void end() {
		// spin intake to pull in ball
		shooter.spinDown();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
