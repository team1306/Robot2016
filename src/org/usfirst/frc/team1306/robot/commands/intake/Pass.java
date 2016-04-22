package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 *
 */
public class Pass extends CommandBase {

	public Pass() {
		requires(intake);
		requires(indexer);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		if (intakeArm.getCurrentCommand() instanceof IntakeArmRest) {
			new IntakeArmMove(IntakePosition.PICKUP).start();
		}
		indexer.reverse();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (intakeArm.isVertical()) {
			intake.stopRollers();
		} else {
			intake.startRollers();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
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
