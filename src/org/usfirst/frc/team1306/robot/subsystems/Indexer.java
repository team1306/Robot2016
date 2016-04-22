package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.intake.BallQuality;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Represents the robot's indexer. This is what holds the balls in the turret
 * before firing them, like a preliminary stage between intake and shooter.
 * 
 * @author Finn Voichick
 */
public class Indexer extends Subsystem {

	/** The speed controller that moves the intake wheels. */
	private final SpeedController motor;
	/** One of the limit switches that detects a ball. */
	private final DigitalInput ballSwitch1;
	/** The limit switch on the other side. */
	private final DigitalInput ballSwitch2;
	/** The pressure pad that tests ball compression. */
	private final AnalogInput pressurePad;

	/** The quality of the stored ball. */
	private BallQuality quality;

	/**
	 * Constructs an Indexer, initializing the SpeedController and limit
	 * switches.
	 */
	public Indexer() {
		motor = new Talon(RobotMap.INDEXER_PORT);
		motor.setInverted(true);
		ballSwitch1 = new DigitalInput(RobotMap.INDEXER_LIMIT_1_PORT);
		ballSwitch2 = new DigitalInput(RobotMap.INDEXER_LIMIT_2_PORT);
		pressurePad = new AnalogInput(RobotMap.PRESSURE_PAD_PORT);
	}

	/**
	 * Sets the default command for the Indexer. Nothing is done to the indexer
	 * until commands are called, so no default command must be specified.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Drives the intake motor at the power specified in Constants.
	 */
	public void driveMotor() {
		motor.set(-Constants.INDEXER_POWER);
	}

	/**
	 * Stops the intake motor.
	 */
	public void stop() {
		motor.disable();
	}

	/**
	 * Reverse drives the intake motor at the power specified in Constants.
	 */
	public void reverse() {
		motor.set(1.0);
	}

	/**
	 * Checks whether the indexer has a ball.
	 * 
	 * @return true if the indexer has a ball, otherwise false.
	 */
	public boolean hasBall() {
		return !ballSwitch1.get() || !ballSwitch2.get();
	}

	public double getCompression() {
		return pressurePad.getVoltage();
	}

	public BallQuality getQuality() {
		return quality;
	}

	public void setQuality(BallQuality quality) {
		this.quality = quality;
	}
}
