package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.Timer;
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
		// Values useful for PID debugging
		SmartDashboard.putNumber("Game Time", Math.floor(Timer.getMatchTime()));

		SmartDashboard.putNumber("hood angle", hood.getHeight());
		SmartDashboard.putNumber("shooter speed", shooter.getSpeed());
		SmartDashboard.putNumber("shooter current", shooter.getCurrent());

		SmartDashboard.putNumber("intake arm pos", intakeArm.getPosition());
		SmartDashboard.putString("intake command",
				intakeArm.getCurrentCommand() == null ? "null" : intakeArm.getCurrentCommand().toString());

		SmartDashboard.putNumber("target distance", Vision.getData().getDistance());

		SmartDashboard.putBoolean("Vision", Vision.isConnected());

		SmartDashboard.putBoolean("Ball", indexer.hasBall());
		SmartDashboard.putBoolean("Visibility", Vision.canSeeTarget());
		SmartDashboard.putBoolean("Shooter", shooter.isSpunUp());
		SmartDashboard.putBoolean("Turret", turret.getPIDController().isEnabled() && turret.onTarget());
		SmartDashboard.putBoolean("Hood", hood.onTarget());

		SmartDashboard.putString("Distance", displayFeet(Vision.distanceFeet()));
		String inRange;
		if (!Vision.isConnected()) {
			inRange = "Vision disconnected";
		} else if (!Vision.canSeeTarget()) {
			inRange = "Can't see target";
		} else if (Vision.distanceFeet() > Constants.MAX_DISTANCE) {
			inRange = "Get closer";
		} else if (Vision.distanceFeet() < Constants.MIN_DISTANCE) {
			inRange = "Too close";
		} else {
			inRange = "In range";
		}
		SmartDashboard.putString("In Range", inRange);

		SmartDashboard.putString("Aiming", hood.isManuallyControlled() ? "Manual Aim" : hood.getTarget() + "");
		SmartDashboard.putString("Quality", hood.getAdjustment().toString());

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

	private static String displayFeet(double feet) {
		return Math.floor(feet + 1.0/24.0) + " ft " + Math.floor(feet * 12 + 0.5) % 12 + " in";
	}
}
