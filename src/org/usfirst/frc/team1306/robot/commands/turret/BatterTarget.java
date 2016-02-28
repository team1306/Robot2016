package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that aims the turret at the tower goal. If the target can be seen,
 * it enables automatic turret and hood targeting. The hood and turret positions
 * can also be manually overridden, or the hood can be set to a certain angle.
 * 
 * @author Finn Voichick
 */
public class BatterTarget extends CommandBase {

	/**
	 * Creates a new BatterTarget command. The turret is required because this
	 * command can't run at the same time as anything that requires these
	 * subsystems.
	 */
	public BatterTarget() {
		requires(turret);
		requires(hood);
	}

	/**
	 * Called just before this Command runs the first time. Puts the hood into
	 * automatic targeting mode and resets the turret PID controller (to reset
	 * the integral to zero).
	 */
	protected void initialize() {
		SmartDashboard.putNumber("set hood height", SmartDashboard.getNumber("set hood height", 45.0));
		turret.getPIDController().reset();
		turret.setTurretForward();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Allows for
	 * manual hood override, but will otherwise just go to the set high
	 * position.
	 */
	protected void execute() {
		if (oi.getHoodOverride()) {
			hood.setVel(oi.getHoodVel());
		} else {
			hood.setHeight(Constants.HOOD_HIGH_POSITION);
		}
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command only ends when it's interrupted.
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
	 * subsystems is scheduled to run. It disables the PID controller and sets
	 * the turret's velocity to zero.
	 */
	protected void interrupted() {
	}
}
