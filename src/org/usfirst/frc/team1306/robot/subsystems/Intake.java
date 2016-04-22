package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This Subsystem controls only the rollers on the intake arm, not the intake
 * arm itself. It has methods for bringing balls in and out of the robot.
 */
public class Intake extends Subsystem {

	/** One of the speed controllers that moves the intake. */
	private final SpeedController lateralRoller;
	/** The other roller speed controller. */
	private final SpeedController intakeRoller;

	/**
	 * Constructs an the intake with both rollers.
	 */
	public Intake() {
		lateralRoller = new Talon(RobotMap.INTAKE_ROLLER_1_PORT);
		intakeRoller = new Talon(RobotMap.INTAKE_ROLLER_2_PORT);
		intakeRoller.setInverted(true);

	}

	/**
	 * Sets the default command for the Intake. Nothing is done to the intake
	 * until commands are called, so no default command must be specified.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Set the rollers spinning. This direction will pull balls in.
	 */
	public void startRollers() {
		lateralRoller.set(1.0);
		intakeRoller.set(1.0);
	}

	/**
	 * Stop the rollers spinning.
	 */
	public void stopRollers() {
		lateralRoller.set(0.0);
		intakeRoller.set(0.0);
	}

	/**
	 * Reverse the rollers. This will push balls out.
	 */
	public void reverse() {
		lateralRoller.set(1.0);
		intakeRoller.set(-1.0);
	}

}
