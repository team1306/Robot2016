package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Manually controls the turret using the triggers on the secondary Xbox
 * controller.
 * 
 * @author Finn Voichick
 */
public class ManualTarget extends CommandBase {

	/**
	 * Creates a new ManualTarget command. The turret is required because this
	 * command can't run at the same time as AutoTarget or SnapForward.
	 */
	public ManualTarget() {
		requires(turret);
	}

	/**
	 * Called just before this Command runs the first time. Currently does
	 * nothing.
	 */
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. This is where
	 * the turret's velocity is updated.
	 */
	protected void execute() {
		turret.setVel(oi.getSecondaryRightTrigger() - oi.getSecondaryLeftTrigger());
	}

	/**
	 * Make this return true when this Command no longer needs to run execute().
	 * This command only ends when it's interrupted.
	 * 
	 * @return false
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
	 * transfers control, so no new velocity needs to be set.
	 */
	protected void interrupted() {
	}
}
