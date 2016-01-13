package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class TalonTest extends CommandBase {
	
	private final double target = 1000;
	private final double startTime;
	private double declineTime = 0.0;
	private double maxSpeed = 0.0;
	private double speed = 0.0;

    public TalonTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	startTime = Timer.getFPGATimestamp();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (target < Math.pow(Constants.MAX_SPEED, 2)/(2*Constants.MAX_ACCELERATION)) {
    		maxSpeed = Math.sqrt(target*Constants.MAX_ACCELERATION);
    	} else {
    		maxSpeed = Constants.MAX_SPEED;
    	}
    	
    	drivetrain.zero();
    	drivetrain.driveTank(speed, speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (speed < maxSpeed) {
    		speed = (Timer.getFPGATimestamp() - startTime) * Constants.MAX_ACCELERATION;
    	} else {
    		if (declineTime == 0.0) {
    			declineTime = Timer.getFPGATimestamp();
    		}
    		speed = maxSpeed - (Timer.getFPGATimestamp() - declineTime) * Constants.MAX_ACCELERATION;
    	}
    	drivetrain.driveTank(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return speed < 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
