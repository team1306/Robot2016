package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A command that automatically aims the turret at the tower goal. It repeatedly
 * (5 times per second) gets data about where the target is, then uses the
 * CANTalon's internal PID loop to point there, always updating its intended
 * target.
 */
public class AutoTarget extends CommandBase {

	/** The timestamp of the most recent vision data */
	private double recentTimestamp;

	/**
	 * Creates a new AutoTarget command. The turret is required because this
	 * command can't run at the same time as ManualTarget or SnapForward.
	 */
	public AutoTarget() {
		requires(turret);
	}

	/**
	 * Called just before this Command runs the first time. Currently does
	 * nothing.
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. If the target is
	 * seen, it sets the target there.
	 */
	protected void execute() {
		if (vision.canSeeTarget() && vision.getData().getTimestamp() > recentTimestamp) {
			turret.setTargetRelative(vision.getData().getYaw());
			recentTimestamp = vision.getData().getTimestamp();
		}
	}

	/**
	 * Make this return true when this Command no longer needs to run execute().
	 * This command only ends when it's interrupted.
	 */
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true. This command never does end.
	 */
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Nothing happens because it simply
	 * transfers control, so no new target needs to be set.
	 */
	protected void interrupted() {
	}
}
