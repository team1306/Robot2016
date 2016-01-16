package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class SetStraight extends CommandBase {

    public SetStraight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double current_angle=oi.getAngle();
    	double ideal_angle=oi.getPOV();
    	double angle_change=ideal_angle - current_angle;
    	
    	
    	
    	
 
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
