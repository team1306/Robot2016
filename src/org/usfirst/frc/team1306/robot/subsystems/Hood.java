package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
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
	private boolean aimingLow;

	/**
	 * Constructs a Hood and enables PID position control.
	 */
	public Hood() {

		aimingLow = false;
		hoodTalon = new CANTalon(RobotMap.hoodTalonPort);
		hoodTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
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
	 * Sets the position of the hood, in degrees. 0 shoots directly forward,
	 * while 90 shoots straight up. The angle is the angle of the trajectory,
	 * not the angle of the edge of the hood.
	 * 
	 * @param position
	 *            the new position of the hood
	 */
	public void setHeight(double position) {
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.set(Constants.HOOD_0_POS + position * (Constants.HOOD_90_POS - Constants.HOOD_0_POS) / 90.0);
	}

	public double getHeight() {
		return 90.0 * (hoodTalon.getPosition() - Constants.HOOD_0_POS) / (Constants.HOOD_90_POS - Constants.HOOD_0_POS);
	}

	public void setVel(double velocity) {
		hoodTalon.changeControlMode(TalonControlMode.PercentVbus);
		hoodTalon.set(velocity);
	}

	/**
	 * Sets the hood to a flat position. At this position (position 90), the
	 * robot will fit under the low bar.
	 */
	public void flatten() {
		setHeight(90.0);
	}

	public void toggleTarget() {
		aimingLow = !aimingLow;
	}

	public boolean isAimingLow() {
		return aimingLow;
	}

	public boolean onTarget() {
		return hoodTalon.getControlMode().equals(TalonControlMode.Position)
				&& hoodTalon.getError() < Constants.HOOD_TOLERANCE;
	}

}
