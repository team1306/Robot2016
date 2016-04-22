package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command for tank drive
 * 
 * @author James Tautges
 */

public class DriveTank extends CommandBase {

	public DriveTank() {
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		drivetrain.shiftDown();
	}

	/**
	 * Drives the drivetrain using a combination of the triggers and the
	 * joysticks. If joysticks aren't used, it can drive straight using the
	 * triggers.
	 */
	@Override
	protected void execute() {
		double straightVel = oi.getStraightVel();
		drivetrain.driveTank(oi.getLeftVel() + straightVel, oi.getRightVel() + straightVel);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
