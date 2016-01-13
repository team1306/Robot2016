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
    	requires(drivetrain);
    	startTime = Timer.getFPGATimestamp();
    }

    protected void initialize() {
    	if (target < Math.pow(Constants.MAX_SPEED, 2)/(2*Constants.MAX_ACCELERATION)) { // if the target distance is too short for us to get up to full speed
    		maxSpeed = Math.sqrt(target*Constants.MAX_ACCELERATION);
    	} else { // if we can reach our max speed
    		maxSpeed = Constants.MAX_SPEED;
    	}
    	
    	// zero the encoders and set the speeds of 0
    	drivetrain.zero();
    	drivetrain.driveTank(speed, speed);
    }

    protected void execute() {
    	// if we're under the projected max speed, continue ramping up or maintain max speed
    	if (drivetrain.getPosition(0) < target - 0.5 * Math.pow(maxSpeed, 2) / Constants.MAX_ACCELERATION) { 
    		speed = Math.min((Timer.getFPGATimestamp() - startTime) * Constants.MAX_ACCELERATION, Constants.MAX_SPEED);
    	} else { // once it's time to ramp down, do so
    		if (declineTime == 0.0) {
    			declineTime = Timer.getFPGATimestamp();
    		}
    		speed = maxSpeed - (Timer.getFPGATimestamp() - declineTime) * Constants.MAX_ACCELERATION;
    	}
    	drivetrain.driveTank(speed, speed);
    }

    protected boolean isFinished() {
    	// terminate when we've ramped all the way down
        return speed < 0;
    }

    protected void end() {
    	drivetrain.stop();
    }

    protected void interrupted() {
    }
}
