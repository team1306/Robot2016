package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.vision.Vision;
import org.usfirst.frc.team1306.robot.vision.VisionData;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartDashboardUpdate extends CommandBase {

	public SmartDashboardUpdate() {
		// This command is started when the robot is initialized, so to prevent
		// it from being interupted when the robot goes into disabled mode, this
		// flag needs to be set
		setRunWhenDisabled(true);
	}

	protected void initialize() {
	}

	// Print various debugging values to the SmartDashboard. These commands can
	// invoke any methods of any subsystem provided they're read only
	// operations. Since we don't require any subsystems, we can't guarantee
	// that other commands aren't accessing it
	protected void execute() {
		SmartDashboard.putNumber("left intake motor", intakeArm.getLeftPos());
		SmartDashboard.putNumber("right intake motor", intakeArm.getRightPos());

		VisionData data = Vision.getData();
		SmartDashboard.putNumber("latera", data.getYaw());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
