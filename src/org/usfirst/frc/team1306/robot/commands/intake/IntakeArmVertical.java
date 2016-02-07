package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 *
 */
public class IntakeArmVertical extends CommandBase {

	public IntakeArmVertical() {
		requires(intakeArm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		intakeArm.setPosition(Constants.INTAKE_VERTICAL_POS);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
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
