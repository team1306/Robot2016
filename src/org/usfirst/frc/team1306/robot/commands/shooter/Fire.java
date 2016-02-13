package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 *
 */
public class Fire extends CommandBase {
	
	private boolean fired;

	public Fire() {
		requires(shooter);
		requires(indexer);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		fired = false;
		shooter.spinUp();
		indexer.driveMotor();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!indexer.hasBall() && !shooter.onTarget()) {
			indexer.stop();
			fired = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return fired && shooter.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		shooter.spinDown();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		indexer.stop();
		shooter.spinDown();
	}
}
