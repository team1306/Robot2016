package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 *
 */
public class AutoTarget extends CommandBase {

    public AutoTarget() {
        requires(turret);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	vision.update();
    	turret.setTargetRelative(vision.getYaw());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turret.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	turret.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
