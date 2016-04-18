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
	protected void initialize() {
		if (intakeArm.getCurrentCommand() instanceof IntakeArmRest) {
			new IntakeArmPickup().start();
		}
		indexer.reverse();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (intakeArm.getCurrentCommand() instanceof IntakeArmVertical) {
			intake.stopRollers();
		} else {
			intake.startRollers();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
