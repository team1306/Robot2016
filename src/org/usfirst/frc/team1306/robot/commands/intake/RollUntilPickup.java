package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * This command starts the rollers and runs them until a ball is picked up. It
 * uses the Intake and Indexer subsystems.
 * 
 * @author Finn Voichick
 */
public class RollUntilPickup extends CommandBase {

	/**
	 * Creates a new RollUntilPickup command that requires the intake and the
	 * indexer. Both of these are required because both of their motors are
	 * turning to pick up a ball.
	 */
	public RollUntilPickup() {
		requires(intake);
		requires(indexer);
	}

	/**
	 * Called just before this Command runs the first time. This is where the
	 * rollers and indexer are started.
	 */
	protected void initialize() {
		intake.startRollers();
		indexer.driveMotor();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing happens
	 * here because the speed of the intake rollers and indexer motor has
	 * already been set.
	 */
	protected void execute() {
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). In this
	 * case, the command is finished when it has a ball.
	 * 
	 * @return true if the indexer picked up a ball, otherwise false.
	 */
	protected boolean isFinished() {
		return indexer.hasBall();
	}

	/**
	 * Called once after isFinished returns true. At this point, both motors are
	 * stopped.
	 */
	protected void end() {
		intake.stopRollers();
		indexer.stop();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. If the intake or indexer are required by
	 * other subsystems, they will first be stopped.
	 */
	protected void interrupted() {
		end();
	}
}
