package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A command that enables the low spin option. This means that when the shooter
 * isn't spinning up, it's spinning at a slow speed. The intention is to reduce
 * spin-up time.
 * 
 * @author Finn Voichick
 */
public class LowSpinOn extends CommandBase {

	/**
	 * Constructs a LowSpinOn command.
	 */
	public LowSpinOn() {
	}

	/**
	 * Called just before this Command runs the first time. This is where the
	 * low spin setting is enabled.
	 */
	@Override
	protected void initialize() {
		shooter.setLowSpin(true);
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing is run
	 * repeatedly; this is a one-time command.
	 */
	@Override
	protected void execute() {
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * never needs to run execute(), so it returns true right away.
	 * 
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	/**
	 * Called once after isFinished returns true. Nothing happens here.
	 */
	@Override
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Nothing happens here.
	 */
	@Override
	protected void interrupted() {
	}
}
