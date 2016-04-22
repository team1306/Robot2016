package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A command that aims the turret at the tower goal from up on the batter. The
 * turret is set straight forward and the hood is set to a specific angle.
 * 
 * @author Finn Voichick
 */
public class BatterTargetClose extends CommandBase {

	/**
	 * Creates a new BatterTargetClose command. The turret and hood are required
	 * because this command can't run at the same time as anything that requires
	 * these subsystems.
	 */
	public BatterTargetClose() {
		requires(turret);
		requires(hood);
	}

	/**
	 * Called just before this Command runs the first time. Sets the turret
	 * pointing straight forward.
	 */
	@Override
	protected void initialize() {
		turret.setTurretForward();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Allows for
	 * manual hood override, but will otherwise just go to the set position.
	 */
	@Override
	protected void execute() {
		if (oi.getHoodOverride()) {
			hood.setVel(oi.getHoodVel());
		} else {
			hood.setTarget(HoodTarget.BATTER_CLOSE);
		}
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command only ends when it's interrupted.
	 * 
	 * @return false
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true. This command never does end.
	 */
	@Override
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Nothing needs to happen when this command
	 * is interrupted.
	 */
	@Override
	protected void interrupted() {
	}
}
