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
		double straightVel = oi.getRightTrigger() - oi.getLeftTrigger();
		drivetrain.driveTank(oi.getLeft() + straightVel, oi.getRight() + straightVel);
		SmartDashboard.putNumber("left", oi.getLeft() + straightVel);
		SmartDashboard.putNumber("right", oi.getRight() + straightVel);
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
