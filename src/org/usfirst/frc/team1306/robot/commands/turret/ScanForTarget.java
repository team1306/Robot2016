package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.vision.Vision;

/**
 * A command that scans for a target. This is used in autonomous mode.
 * Essentially, it moves the turret back and forth until a Vision target is
 * found.
 */
public class ScanForTarget extends CommandBase {

	/**
	 * Constructs a new ScanForTarget command. This command requires the turret
	 * so that it doesn't conflict with other turret-related commands.
	 */
	public ScanForTarget() {
		requires(turret);
	}

	/**
	 * Called just before this Command runs the first time. The velocity is
	 * originally set to a low power so that it doesn't gain too much momentum.
	 */
	protected void initialize() {
		turret.setVel(Constants.TURRET_SCAN_SPEED);
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. If the turret
	 * gets to the edge of its range, it reverses the direction.
	 */
	protected void execute() {
		if (turret.isRight()) {
			turret.setVel(-Constants.TURRET_SCAN_SPEED);
		} else if (turret.isLeft()) {
			turret.setVel(Constants.TURRET_SCAN_SPEED);
		}
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). In this
	 * case, it's finished scanning when it can see the target.
	 */
	protected boolean isFinished() {
		return Vision.canSeeTarget();
	}

	/**
	 * Called once after isFinished returns true. Stops the turret when it can
	 * see the target.
	 */
	protected void end() {
		turret.setVel(0.0);
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Stops the turret from scanning before the
	 * next command takes over.
	 */
	protected void interrupted() {
		end();
	}
}
