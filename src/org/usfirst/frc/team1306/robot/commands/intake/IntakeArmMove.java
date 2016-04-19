package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A Command to move the intake arm to a given position. It uses the Talon's
 * internal PID loop.
 * 
 * @author Finn Voichick
 */
public class IntakeArmMove extends CommandBase {

	/** The position to move the arm to. */
	private final IntakePosition position;

	/**
	 * Constructs an IntakeArmMove Command that requires the IntakeArm.
	 */
	public IntakeArmMove(IntakePosition position) {
		this.position = position;
		requires(intakeArm);
	}

	/**
	 * Called just before this Command runs the first time. This is where the
	 * arm's new position is set.
	 */
	protected void initialize() {
		intakeArm.setPosition(position);
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing needs to
	 * be run repeatedly because the PID calculations are done internally on the
	 * Talon SRX.
	 */
	protected void execute() {
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command doesn't have a finish condition because it runs until
	 * interrupted.
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
	 * subsystems is scheduled to run. Nothing needs to happen when this command
	 * is interrupted.
	 */
	protected void interrupted() {
	}
}
