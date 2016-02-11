package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * This command starts the rollers and runs them until a ball is picked up. It
 * uses the Intake and Indexer subsystems.
 * 
 * @author Finn Voichick
 */
public class RollUntilPickup extends CommandBase {

	public RollUntilPickup() {
		requires(intake);
		requires(indexer);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		intake.startRollers();
		indexer.driveMotor();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return indexer.hasBall();
	}

	// Called once after isFinished returns true
	protected void end() {
		intake.stopRollers();
		indexer.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
