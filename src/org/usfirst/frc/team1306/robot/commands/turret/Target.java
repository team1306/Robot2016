package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that aims the turret at the tower goal. If the target can be seen,
 * it enables automatic turret and hood targeting. The hood and turret positions
 * can also be manually overridden, or the hood can be set to a certain angle.
 * 
 * @author Finn Voichick
 */
public class Target extends CommandBase {

	/**
	 * Creates a new Target command. The turret is required because this command
	 * can't run at the same time as anything that requires these subsystems.
	 */
	public Target() {
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
		hood.setTarget(HoodTarget.AUTO);
		turret.getPIDController().reset();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. If the target is
	 * seen, it enables the Turret PIDSubsystem and sets the hood angle
	 * accordingly. Otherwise, allows for manual control. If the hood's target
	 * is set to something other than automatic, it will go to its set position
	 * instead.
	 */
	protected void execute() {
		boolean canSeeTarget = Vision.getData().getDistance() > 0.0;
		if (canSeeTarget && !oi.getTurretOverrride()) {
			turret.enable();
			turret.setSetpoint(0.0);
		} else {
			turret.getPIDController().reset();
			turret.setVel(oi.getTurretVel());
		}
		if (oi.getHoodOverride()) {
			hood.setVel(oi.getHoodVel());
		} else if (hood.getTarget().equals(HoodTarget.LOW)) {
			hood.setHeight(Constants.HOOD_LOW_GOAL_POSITION);
		} else if (hood.getTarget().equals(HoodTarget.HIGH)) {
			hood.setHeight(Constants.HOOD_HIGH_POSITION);
		} else if (canSeeTarget) {
			// TODO re-add automatic targeting when the math is done
			// hood.setDistance(Vision.getData().getDistance());
			hood.setHeight(SmartDashboard.getNumber("set hood height"));
		} else {
			hood.setVel(oi.getHoodVel());
		}
	}

	/**
	 * Returns true when this Command no longer needs to run execute().
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
		turret.getPIDController().reset();
		turret.setVel(0.0);
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. It disables the PID controller and sets
	 * the turret's velocity to zero.
	 */
	protected void interrupted() {
		end();
	}
}
