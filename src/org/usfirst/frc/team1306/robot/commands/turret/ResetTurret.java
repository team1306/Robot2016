package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmRest;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmVertical;

/**
 * Sets the Turret to point directly forward (encoder position zero), and lowers
 * the hood so that the robot can fit under the low bar. This also spins down
 * the shooter because it is used after firing. This command will continue until
 * interrupted when the robot enters targeting mode.
 * 
 * @author Finn Voichick
 */
public class ResetTurret extends CommandBase {

	/**
	 * Creates a new ResetTurret command. The turret and hood are required
	 * because this command can't run at the same time as Target or Fire.
	 */
	public ResetTurret() {
		requires(turret);
		requires(hood);
		requires(shooter);
	}

	/**
	 * Called just before this Command runs the first time. This is where the
	 * turret's target is set to zero and the hood is lowered all the way.
	 * Because the PID calculations are done on the CANTalon, the target only
	 * needs to be set once. If the intake arm is up, another command is started
	 * that lowers it to resting position. This is to ensure that the turret
	 * doesn't hit the intake arm as it turns.
	 */
	protected void initialize() {
		turret.setTurretForward();
		hood.flatten();
		shooter.spinDown();
//		if (intakeArm.getCurrentCommand() instanceof IntakeArmVertical) {
//			new IntakeArmRest().start();
//		}
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Nothing is here
	 * because nothing needs to happen repeatedly.
	 */
	protected void execute() {
	}

	/**
	 * Make this return true when this Command no longer needs to run execute().
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
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Nothing happens because it simply
	 * transfers control, so no new position or velocity needs to be set.
	 */
	protected void interrupted() {
	}
}
