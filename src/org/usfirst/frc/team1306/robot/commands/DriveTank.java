package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTank extends CommandBase {

	public DriveTank() {
		requires(drivetrain);
	}

	protected void initialize() {
	}

	/**
	 * Drives the drivetrain using a combination of the triggers and the
	 * joysticks. If joysticks aren't used, it can drive straight using the
	 * triggers.
	 */
	protected void execute() {
		double straightVel = oi.getStraightVel();
		drivetrain.driveTank(oi.getLeftVel() + straightVel, oi.getRightVel() + straightVel);
		SmartDashboard.putNumber("left", oi.getLeftVel() + straightVel);
		SmartDashboard.putNumber("right", oi.getRightVel() + straightVel);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.stop();
	}

	protected void interrupted() {
		end();
	}
}
