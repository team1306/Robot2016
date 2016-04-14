//package org.usfirst.frc.team1306.robot.commands.intake;
//
//import org.usfirst.frc.team1306.robot.Constants;
//import org.usfirst.frc.team1306.robot.commands.CommandBase;
//
///**
// * A Command to lower the intake arm below its resting position. It uses the
// * Talon's internal PID loop.
// * 
// * @author Finn Voichick
// */
//public class IntakeArmDown extends CommandBase {
//
//	/**
//	 * Constructs an IntakeArmDown Command that requires the IntakeArm.
//	 */
//	public IntakeArmDown() {
//		requires(intakeArm);
//	}
//
//	/**
//	 * Called just before this Command runs the first time. This is where the
//	 * arm's new position is set.
//	 */
//	protected void initialize() {
//		intakeArm.setPosition(Constants.INTAKE_DOWN_POS);
//	}
//
//	/**
//	 * Called repeatedly when this Command is scheduled to run. Nothing needs to
//	 * be run repeatedly because the PID calculations are done internally on the
//	 * Talon SRX.
//	 */
//	protected void execute() {
//	}
//
//	/**
//	 * Returns true when this Command no longer needs to run execute(). This
//	 * command doesn't have a finish condition because it runs until
//	 * interrupted.
//	 * 
//	 * @return false
//	 */
//	protected boolean isFinished() {
//		return false;
//	}
//
//	/**
//	 * Called once after isFinished returns true. This command never does end.
//	 */
//	protected void end() {
//	}
//
//	/**
//	 * Called when another command which requires one or more of the same
//	 * subsystems is scheduled to run. Nothing needs to happen when this command
//	 * is interrupted.
//	 */
//	protected void interrupted() {
//	}
//}
