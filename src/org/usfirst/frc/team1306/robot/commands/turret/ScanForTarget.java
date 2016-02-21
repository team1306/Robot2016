package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ScanForTarget extends CommandBase {

    public ScanForTarget() {
        requires(turret);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turret.setRight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (turret.isRight()) {
    		turret.setLeft();
    	} else if (turret.isLeft()) {
    		turret.setRight();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Vision.canSeeTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	turret.setVel(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
