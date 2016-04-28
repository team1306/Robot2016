package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A Command to fire a ball. This command assumes that the shooter has already
 * spun up. Running this command will then use the indexer to push the ball into
 * the shooter flywheel, after which both of the will turn off. There is a timed
 * delay for the motors to stop.
 * 
 * @author Finn Voichick
 */
public class Fire extends CommandBase {

	/** A Timer that waits until the ball has been fired. */
	private Timer timer;

	/**
	 * Constructs a new Fire Command. It runs both the shooter and the indexer,
	 * so both subsystems are required. It doesn't require the turret or hood
	 * because they are controlled separately by the Target command.
	 */
	public Fire() {
		timer = new Timer();
		requires(shooter);
		requires(indexer);
	}

	/**
	 * Called just before this Command runs the first time. It makes sure that
	 * the shooter keeps spinning and begins driving the indexer.
	 */
	@Override
	protected void initialize() {

		SmartDashboard.putBoolean("momentum shot", SmartDashboard.getBoolean("momentum shot", false));

		timer.reset();
		timer.start();
		shooter.spinUp();
		indexer.driveMotor();
		
		SmartDashboard.putString("shots", SmartDashboard.getString("shots") + "dist:" + Vision.distanceFeet() +";turret:" + Vision.getData().getYaw() + ";hood:" + hood.getHeight() + " ");

	}

	/**
	 * Called repeatedly when this Command is scheduled to run. It waits until
	 * the ball is pulled into the shooter before stopping the intake and
	 * setting fired to true.
	 */
	@Override
	protected void execute() {

		if (SmartDashboard.getBoolean("momentum shot") && !indexer.hasBall()) {
			shooter.spinDown();
		} else {
			shooter.spinUp();
		}

		indexer.driveMotor();

	}

	/**
	 * Returns true when this Command no longer needs to run execute(). In this
	 * case, the period of time required by the timer must have passed.
	 */
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(Constants.SHOT_TIME);
	}

	/**
	 * Called once after isFinished returns true. This stops the motors.
	 */
	@Override
	protected void end() {
		indexer.stop();
		shooter.spinDown();
		new ResetTurret().start();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. It will make sure that neither motor is
	 * spinning anymore.
	 */
	@Override
	protected void interrupted() {
		indexer.stop();
		shooter.spinDown();
	}
}
