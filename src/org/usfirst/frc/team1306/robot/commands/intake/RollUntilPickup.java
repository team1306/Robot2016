package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This command starts the rollers and runs them until a ball is picked up. It
 * uses the Intake and Indexer subsystems.
 * 
 * @author Finn Voichick
 */
public class RollUntilPickup extends CommandBase {

	private double maxCompression;

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
	 * indexer is started.
	 */
	@Override
	protected void initialize() {
		maxCompression = 0.0;
		indexer.driveMotor();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Here, the intake
	 * rollers are driven only if the intake arm is down, because otherwise
	 * they're pointless. This allows the driver to move the intake arm up and
	 * down while intaking a ball.
	 */
	@Override
	protected void execute() {

		maxCompression = Math.max(maxCompression, indexer.getCompression());
		SmartDashboard.putNumber("max compression", maxCompression);

		if (intakeArm.isVertical()) {
			intake.stopRollers();
		} else {
			intake.startRollers();
		}

	}

	/**
	 * Returns true when this Command no longer needs to run execute(). In this
	 * case, the command is finished when it has a ball.
	 * 
	 * @return true if the indexer picked up a ball, otherwise false.
	 */
	@Override
	protected boolean isFinished() {
		return indexer.hasBall();
	}

	/**
	 * Called once after isFinished returns true. At this point, both motors are
	 * stopped. Also, the intake arm is put in a resting position because it no
	 * longer needs to be powered.
	 */
	@Override
	protected void end() {
		intake.stopRollers();
		indexer.stop();

		if (maxCompression > Constants.PRESSURE_THRESHOLD) {
			indexer.setQuality(BallQuality.NEW);
		} else {
			indexer.setQuality(BallQuality.OLD);
		}

		new IntakeArmRest().start();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. If the intake or indexer are required by
	 * other subsystems, they will first be stopped.
	 */
	@Override
	protected void interrupted() {
		end();
	}
}
