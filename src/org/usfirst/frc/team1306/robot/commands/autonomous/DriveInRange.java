package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.vision.Vision;
import org.usfirst.frc.team1306.robot.vision.VisionStatus;

//TODO Documentation
public class DriveInRange extends CommandBase {

    public DriveInRange() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
	@Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
	@Override
    protected void execute() {
		drivetrain.driveTank(Constants.SLOW_DRIVE, Constants.SLOW_DRIVE);
    }

    // Make this return true when this Command no longer needs to run execute()
	@Override
    protected boolean isFinished() {
        return Vision.getStatus().equals(VisionStatus.INRANGE);
    }

    // Called once after isFinished returns true
	@Override
    protected void end() {
		drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
	@Override
    protected void interrupted() {
		end();
    }
}
