package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that spins the shooter up to full speed. This command is meant to
 * be used in conjunction with the Target command so that the shooter is spun up
 * by the time the Fire command is run.
 * 
 * @author Finn Voichick
 */
public class SpinUp extends CommandBase {
	
	private final Timer timer;
	private boolean spunUp;

	/**
	 * Constructs the SpinUp command. For obvious reasons, it requires the
	 * shooter.
	 */
	public SpinUp() {
		timer = new Timer();
		requires(shooter);
	}

	/**
	 * Called just before this Command runs the first time. Nothing needs to be
	 * done before the command starts.
	 */
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		spunUp = false;
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. For whatever
	 * reason, the spinUp() method must be called repeatedly.
	 */
	@Override
	protected void execute() {
		shooter.spinUp();
		if (!spunUp && shooter.isSpunUp()) {
			spunUp = true;
			SmartDashboard.putNumber("spinup time", timer.get());
		}
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command only ends when it's interrupted.
	 * 
	 * @return false
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true. This command never does end.
	 */
	@Override
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Nothing happens because it simply
	 * transfers control, so no new velocity needs to be set.
	 */
	@Override
	protected void interrupted() {
	}
}
