package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * A command that drives the robot under the Low Bar and stops. It is
 * time-based.
 * 
 * @author Finn Voichick
 */
public class DriveUnderLowBar extends CommandBase {

	/** The timer that controls how long the robot drives before stopping */
	private Timer timer;

	/**
	 * Constructs a new DriveOverObstacle command. Initializes the timer and
	 * requires the drivetrain.
	 */
	public DriveUnderLowBar() {
		timer = new Timer();
		requires(drivetrain);
	}

	/**
	 * Called just before this Command runs the first time. Here, the timer is
	 * started.
	 */
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. It runs both
	 * motors at the designated throttle.
	 */
	protected void execute() {
		drivetrain.driveTank(Constants.LOW_BAR_POWER, Constants.LOW_BAR_POWER);
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command ends after a designated amount of time.
	 */
	protected boolean isFinished() {
		return timer.hasPeriodPassed(Constants.LOW_BAR_TIME);
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Stops the drivetrain so that whatever is
	 * interrupting can use it.
	 */
	protected void end() {
		drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
