package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Sets the Turret to point directly forward (encoder position zero). This
 * command will continue until interrupted, either by manual or automatic
 * targeting.
 * 
 * @author Finn Voichick
 */
public class SnapForward extends CommandBase {

	/**
	 * Creates a new SnapForward command. The turret is required because this
	 * command can't run at the same time as AutoTarget or ManualTarget.
	 */
	public SnapForward() {
		requires(turret);
	}

	/**
	 * Called just before this Command runs the first time. This is where the
	 * turret's target is set to zero. Because the PID calculations are done on
	 * the CANTalon, the target only needs to be set once.
	 */
	protected void initialize() {
		turret.setTarget(0.0);
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing is here
	 * because nothing needs to happen repeatedly.
	 */
	protected void execute() {
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
