package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A command for setting the hood to target the low goal. This can be used when
 * the robot is too close to make a high goal shot, or Vision targeting isn't
 * working for some reason. Making this a command allows it to be linked to
 * buttons on the secondary controller.
 */
public class HoodSetTargetLow extends CommandBase {

	/**
	 * Called just before this Command runs the first time. This command is a
	 * one-time action, so the target is set here.
	 */
	protected void initialize() {
		if (hood.getCurrentCommand() instanceof Target) {
			hood.setTarget(HoodTarget.LOW);
		}
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing needs to
	 * be run repeatedly, so this does nothing.
	 */
	protected void execute() {
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command never needs to run execute(), so it is finished as soon as it is
	 * initialized.
	 * 
	 * @return true
	 */
	protected boolean isFinished() {
		return true;
	}

	/**
	 * Called once after isFinished returns true. Nothing is done after this
	 * command.
	 */
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	protected void interrupted() {
	}
}
