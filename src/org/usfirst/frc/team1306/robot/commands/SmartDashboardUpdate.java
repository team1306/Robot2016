package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that keeps the SmartDashboard updated, used both for debugging and
 * getting information to the drivers.
 * 
 * @author Finn Voichick, James Tautges
 */
public class SmartDashboardUpdate extends CommandBase {

	/**
	 * Constructs a new SmartDashboardUpdate command. This command is started
	 * when the robot is initialized, so to prevent it from being interupted
	 * when the robot goes into disabled mode, this flag needs to be set.
	 */
	public SmartDashboardUpdate() {
		setRunWhenDisabled(true);
	}

	/**
	 * Called just before this Command runs the first time. Nothing is here
	 * because everything needs to happen repeatedly.
	 */
	@Override
	protected void initialize() {
		SmartDashboard.putString("shots", "");
	}

	/**
	 * Print various debugging values to the SmartDashboard. These commands can
	 * invoke any methods of any subsystem provided they're read only
	 * operations. Since we don't require any subsystems, we can't guarantee
	 * that other commands aren't accessing it. Capitalized SmartDashboard
	 * values are typically for the drivers, while lowercase values are
	 * typically for debugging purposes.
	 */
	@Override
	protected void execute() {

		SmartDashboard.putNumber("Game Time", Math.floor(Timer.getMatchTime()));

		SmartDashboard.putNumber("hood angle", hood.getHeight());
		SmartDashboard.putNumber("shooter speed", shooter.getSpeed());
		SmartDashboard.putNumber("shooter current", shooter.getCurrent());

		SmartDashboard.putNumber("intake arm pos", intakeArm.getPosition());
		SmartDashboard.putString("intake command", intakeArm.getCurrentCommand() + "");

		SmartDashboard.putNumber("target distance", Vision.getData().getDistance());
		SmartDashboard.putNumber("target yaw", Vision.getData().getYaw() + Math.random() * 0.001);

		SmartDashboard.putBoolean("Vision", Vision.isConnected());

		SmartDashboard.putBoolean("Ball", indexer.hasBall());
		SmartDashboard.putBoolean("Visibility", Vision.canSeeTarget());
		SmartDashboard.putBoolean("Shooter", shooter.isSpunUp());
		SmartDashboard.putBoolean("Turret", turret.getPIDController().isEnabled() && turret.onTarget());
		SmartDashboard.putBoolean("Hood", hood.onTarget());

		SmartDashboard.putString("Distance", displayFeet(Vision.distanceFeet()));
		SmartDashboard.putString("In Range", Vision.getStatus().toString());

		SmartDashboard.putString("Aiming", hood.isManuallyControlled() ? "Manual Aim" : hood.getTarget() + "");
		SmartDashboard.putString("Adjustment", hood.getAdjustment().toString() + "");
		SmartDashboard.putString("Scan Direction", turret.getScanDirection() + "");

		SmartDashboard.putString("Ball Quality",
				indexer.getQuality() == null ? "No Ball" : indexer.getQuality().toString());

		SmartDashboard.putNumber("current compression", indexer.getCompression());

	}

	/**
	 * Make this return true when this Command no longer needs to run execute().
	 * This command only ends when the robot is powered off (or the code dies).
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
	 * subsystems is scheduled to run. This command requires no subsystems, so
	 * it is never interrupted.
	 */
	@Override
	protected void interrupted() {
	}

	/**
	 * Converts a double value in feet to a human-friendly String, in feet and
	 * inches.
	 * 
	 * @param feet
	 *            a value in feet.
	 * @return a more readable String.
	 */
	private static String displayFeet(double feet) {
		return (int) Math.floor(feet + 1.0 / 24.0) + " ft " + (int) Math.floor(feet * 12 + 0.5) % 12 + " in";
	}
}
