package org.usfirst.frc.team1306.robot.commands;

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
		SmartDashboard.putNumber("leftMotor1.get()", drivetrain.get(0));
		SmartDashboard.putNumber("leftMotor1.getError()", drivetrain.get(0) + drivetrain.getEncVelocity(0));
		SmartDashboard.putNumber("Encoder velocity", drivetrain.getEncVelocity(0));

		SmartDashboard.putNumber("rightMotor1.get()", drivetrain.get(2));
		SmartDashboard.putNumber("rightMotor1.getError()", drivetrain.getError(2));
		
		SmartDashboard.putNumber("MAX leftMotor1.getCurrent()", Math.max(pdp.getCurrent(0), SmartDashboard.getNumber("MAX leftMotor1.getCurrent()")));
		SmartDashboard.putNumber("MAX leftMotor2.getCurrent()", Math.max(pdp.getCurrent(1), SmartDashboard.getNumber("MAX leftMotor2.getCurrent()")));
		SmartDashboard.putNumber("MAX leftMotor3.getCurrent()", Math.max(pdp.getCurrent(2), SmartDashboard.getNumber("MAX leftMotor3.getCurrent()")));
		SmartDashboard.putNumber("MAX rightMotor1.getCurrent()", Math.max(pdp.getCurrent(15), SmartDashboard.getNumber("MAX rightMotor1.getCurrent()")));
		SmartDashboard.putNumber("MAX rightMotor2.getCurrent()", Math.max(pdp.getCurrent(14), SmartDashboard.getNumber("MAX rightMotor2.getCurrent()")));
		SmartDashboard.putNumber("MAX rightMotor3.getCurrent()", Math.max(pdp.getCurrent(13), SmartDashboard.getNumber("MAX rightMotor3.getCurrent()")));

		SmartDashboard.putNumber("NOW leftMotor1.getCurrent()", pdp.getCurrent(1));
		SmartDashboard.putNumber("NOW leftMotor2.getCurrent()", pdp.getCurrent(1));
		SmartDashboard.putNumber("NOW leftMotor3.getCurrent()", pdp.getCurrent(2));
		SmartDashboard.putNumber("NOW rightMotor1.getCurrent()", pdp.getCurrent(15));
		SmartDashboard.putNumber("NOW rightMotor2.getCurrent()", pdp.getCurrent(14));
		SmartDashboard.putNumber("NOW rightMotor3.getCurrent()", pdp.getCurrent(13));
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
