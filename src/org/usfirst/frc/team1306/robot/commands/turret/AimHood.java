package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.vision.Vision;

/**
 *
 */
public class AimHood extends CommandBase {

	private double recentTimestamp;

	public AimHood() {
		requires(hood);
		recentTimestamp = 0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Vision.canSeeTarget() && Vision.getData().getTimestamp() > recentTimestamp) {
			hood.setHeight(Vision.getData().getPitch());
		}
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
