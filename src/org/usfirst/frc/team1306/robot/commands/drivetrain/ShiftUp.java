package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Manually shift into higher gear
 * 
 * @author James Tautges
 */
public class ShiftUp extends CommandBase {

	public ShiftUp() {
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		drivetrain.shiftUp();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
