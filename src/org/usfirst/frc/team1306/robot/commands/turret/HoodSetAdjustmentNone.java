package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A command for removing the adjustment on the hood. Making this a command
 * allows it to be linked to buttons on the secondary controller.
 */
public class HoodSetAdjustmentNone extends CommandBase {
	
	public HoodSetAdjustmentNone() {
		setRunWhenDisabled(true);
	}

	/**
	 * Called just before this Command runs the first time. This command is a
	 * one-time action, so the target is set here.
	 */
	@Override
	protected void initialize() {
		hood.setAdjustment(Adjustment.NONE);
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing needs to
	 * be run repeatedly, so this does nothing.
	 */
	@Override
	protected void execute() {
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command never needs to run execute(), so it is finished as soon as it is
	 * initialized.
	 * 
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	/**
	 * Called once after isFinished returns true. Nothing is done after this
	 * command.
	 */
	@Override
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
	}
}
