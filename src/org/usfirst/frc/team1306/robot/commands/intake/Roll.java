package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * This command simply starts and stops the rollers. This will be coupled with a
 * whileHeld trigger, so we don't need a specific stopping command. If we decide
 * to do press on press off, we will need one.
 * 
 * @author James Tautges
 */
public class Roll extends CommandBase {

	public Roll() {
		// Intentionally not requiring the intake since other commands might
		// simultaneously want to access the other parts
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		intake.startRollers();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		intake.startRollers();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		intake.stopRollers();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
