package org.usfirst.frc.team1306.robot.commands;

/**
 *
 */
public class DriveTank extends CommandBase {

	public DriveTank() {
		requires(drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double straightVel = oi.getRightTrigger() - oi.getLeftTrigger();
		drivetrain.driveTank(oi.getLeft() + straightVel, oi.getRight() + straightVel);
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
