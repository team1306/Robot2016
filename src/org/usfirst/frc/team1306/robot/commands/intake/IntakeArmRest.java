package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * A Command to put the intake arm in its resting position. If the arm is coming
 * down from a vertical position, it releases the brakes once it gets to a
 * certain point.
 * 
 * @author Finn Voichick
 */
public class IntakeArmRest extends CommandBase {

	/**
	 * Constructs an IntakeArmRest Command that requires the IntakeArm.
	 */
	public IntakeArmRest() {
		requires(intakeArm);
	}

	/**
	 * Called just before this Command runs the first time. If the arm is above
	 * the drop threshold, it is set to the horizontal position.
	 */
	@Override
	protected void initialize() {
		if (intakeArm.getPosition() > Constants.INTAKE_DROP_THRESHOLD) {
			intakeArm.setPosition(IntakePosition.PICKUP);
		}
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing needs to
	 * be run repeatedly because the PID calculations are done internally on the
	 * Talon SRX.
	 */
	@Override
	protected void execute() {
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command is finished when the intake arm is below a certain threshold.
	 * 
	 * @return true if the intake arm is below the threshold, otherwise false.
	 */
	@Override
	protected boolean isFinished() {
		return intakeArm.getPosition() <= Constants.INTAKE_DROP_THRESHOLD;
	}

	/**
	 * Called once after isFinished returns true. Once the arm is below the
	 * threshold, the brakes are released.
	 */
	@Override
	protected void end() {
		intakeArm.releaseBrakes();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Nothing needs to happen when this command
	 * is interrupted.
	 */
	@Override
	protected void interrupted() {
	}
}
