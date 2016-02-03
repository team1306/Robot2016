package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class DriveHybrid extends CommandBase {

	public DriveHybrid() {
		requires(drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		drivetrain.driveHybrid(oi.getStraightVel(), oi.getLeftX());
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
