package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The hood on the shooter. This controls the angle at which the ball shoots.
 * 
 * @author Finn Voichick
 */
public class Hood extends Subsystem {

	/** The motor that moves the hood */
	private final CANTalon hoodTalon;

	/**
	 * Constructs a Hood and enables PID position control.
	 */
	public Hood() {

		hoodTalon = new CANTalon(RobotMap.hoodTalonPort);
		hoodTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.set(hoodTalon.get());
		hoodTalon.enable();

	}

	/**
	 * Sets the default command for the Hood. The hood is affected by the same
	 * methods as the turret, so no default command must be specified.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Sets the position of the hood, in degrees.
	 * 
	 * @param the
	 *            new position of the hood
	 */
	public void setHeight(double position) {
		hoodTalon.set(position);
	}

}
