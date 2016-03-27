package org.usfirst.frc.team1306.robot.commands;

//import org.usfirst.frc.team1306.robot.vision.Vision;

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
		SmartDashboard.putNumber("Game Time", Timer.getMatchTime());

//		SmartDashboard.putNumber("hood angle", hood.getHeight());
		SmartDashboard.putNumber("shooter speed", shooter.getSpeed());
		SmartDashboard.putNumber("shooter current", shooter.getCurrent());

		SmartDashboard.putNumber("intake arm pos", intakeArm.getPosition());
		SmartDashboard.putString("intake command", intakeArm.getCurrentCommand() == null ? "null" : intakeArm.getCurrentCommand().toString());

//		SmartDashboard.putNumber("target distance", Vision.getData().getDistance());
		
//		SmartDashboard.putBoolean("Vision", Vision.isConnected());

		SmartDashboard.putBoolean("Ball", indexer.hasBall());
//		SmartDashboard.putBoolean("Visibility", Vision.canSeeTarget());
		SmartDashboard.putBoolean("Shooter", shooter.isSpunUp());
		SmartDashboard.putBoolean("Turret", turret.getPIDController().isEnabled() && turret.onTarget());
//		SmartDashboard.putBoolean("Hood", hood.onTarget());

//		SmartDashboard.putString("Aiming", hood.isManuallyControlled() ? "Manual Aim" : hood.getTarget().toString());
//		SmartDashboard.putString("Quality", hood.getQuality().toString());

		SmartDashboard.putBoolean("Left Drivetrain Current", drivetrain.getLeftCurrent() > 90.0);
		SmartDashboard.putBoolean("Right Drivetrain Current", drivetrain.getRightCurrent() > 90.0);
		// SmartDashboard.putBoolean("Hood Current", hood.getCurrent() > );

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
