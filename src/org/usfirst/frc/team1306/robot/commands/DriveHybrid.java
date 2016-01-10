package org.usfirst.frc.team1306.robot.commands;

public class DriveHybrid extends CommandBase {

	public DriveHybrid() {
		requires(drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		drivetrain.driveHybrid(oi.getRightTrigger() - oi.getLeftTrigger(), oi.getLeftX());
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
