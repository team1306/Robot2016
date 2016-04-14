//package org.usfirst.frc.team1306.robot.commands.autonomous;
//
//import org.usfirst.frc.team1306.robot.Constants;
//import org.usfirst.frc.team1306.robot.commands.CommandBase;
//
//import edu.wpi.first.wpilibj.Timer;
//
///**
// *
// */
//public class DriveOverObstacle extends CommandBase {
//
//	private final Timer timer;
//
//	public DriveOverObstacle() {
//		timer = new Timer();
//		requires(drivetrain);
//	}
//
//	// Called just before this Command runs the first time
//	protected void initialize() {
//		timer.reset();
//		timer.start();
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	protected void execute() {
//		drivetrain.driveTank(Constants.OBSTACLE_POWER, Constants.OBSTACLE_POWER);
//	}
//
//	// Make this return true when this Command no longer needs to run execute()
//	protected boolean isFinished() {
//		return timer.hasPeriodPassed(Constants.OBSTACLE_TIME);
//	}
//
//	// Called once after isFinished returns true
//	protected void end() {
//		drivetrain.stop();
//	}
//
//	// Called when another command which requires one or more of the same
//	// subsystems is scheduled to run
//	protected void interrupted() {
//		end();
//	}
//}
