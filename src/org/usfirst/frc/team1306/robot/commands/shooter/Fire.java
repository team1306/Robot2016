package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A Command to fire a ball. This command assumes that the shooter has already
 * spun up. Running this command will then use the indexer to push the ball into
 * the shooter flywheel, after which both of the will turn off. The indexer
 * stops turning when the flywheel's speed drops (when the ball is pulled in)
 * and the shooter stops spinning when it returns to full speed.
 * 
 * @author Finn Voichick
 */
public class Fire extends CommandBase {

	/** A variable to track whether or not the ball has been pulled in. */
	private boolean fired;

	/**
	 * Constructs a new Fire Command. It runs both the shooter and the indexer,
	 * so both subsystems are required.
	 */
	public Fire() {
		requires(shooter);
		requires(indexer);
	}

	/**
	 * Called just before this Command runs the first time. It makes sure that
	 * the shooter keeps spinning and begins driving the indexer.
	 */
	protected void initialize() {
		fired = false;
		shooter.spinUp();
		indexer.driveMotor();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. It waits until
	 * the ball is pulled into the shooter before stopping the intake and
	 * setting fired to true.
	 */
	protected void execute() {
		shooter.spinUp();
		indexer.driveMotor();
		if (!fired && !indexer.hasBall() && !shooter.onTarget()) {
			indexer.stop();
			fired = true;
		}
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). In this
	 * case, the shooter must have pulled in the ball and returned to its full
	 * speed.
	 */
	protected boolean isFinished() {
		return !indexer.hasBall();//fired;// && shooter.onTarget();
	}

	/**
	 * Called once after isFinished returns true. This stops the flywheel motor
	 * after firing.
	 */
	protected void end() {
		shooter.spinDown();
		indexer.stop();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. It will make sure that neither motor is
	 * spinning anymore.
	 */
	protected void interrupted() {
		indexer.stop();
		shooter.spinDown();
	}
}
