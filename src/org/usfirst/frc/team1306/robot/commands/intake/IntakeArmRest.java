package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 *
 */
public class IntakeArmRest extends CommandBase {

	public IntakeArmRest() {
		requires(intakeArm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (intakeArm.getPosition() > Constants.INTAKE_DROP_THRESHOLD) {
			intakeArm.setPosition(Constants.INTAKE_PICKUP_POS);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return intakeArm.getPosition() <= Constants.INTAKE_DROP_THRESHOLD;
	}

	// Called once after isFinished returns true
	protected void end() {
		intakeArm.releaseBrakes();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
