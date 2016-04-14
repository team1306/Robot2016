package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.Adjustment;
import org.usfirst.frc.team1306.robot.commands.turret.HoodTarget;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The hood on the shooter. This controls the angle at which the ball shoots.
 * 
 * @author Finn Voichick
 */
public class Hood extends Subsystem {

	/** The motor that moves the hood */
	private final CANTalon hoodTalon;
	private HoodTarget target;
	private Adjustment adjustment;

	/**
	 * Constructs a Hood and enables PID position control.
	 */
	public Hood() {

		adjustment = Adjustment.NONE;
		target = null;
		hoodTalon = new CANTalon(RobotMap.hoodTalonPort);
		hoodTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.enable();

	}

	/**
	 * Sets the default command for the Hood. Nothing is done to the hood until
	 * commands are called, so no default command must be specified.
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
	private void setAngle(double position) {
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.set(
				safetyCheck(Constants.HOOD_0_POS + position * (Constants.HOOD_90_POS - Constants.HOOD_0_POS) / 90.0));
	}
	
	public void setAdjustment(Adjustment quality) {
		this.adjustment = quality;
	}
	
	public Adjustment getAdjustment() {
		return adjustment;
	}

	/**
	 * Set the height of the hood based on the distance from the target. The
	 * distance is in arbitrary units returned from the Jetson. This does the
	 * math to find the ideal hood angle using an equation determined using
	 * regression on some tested distances, and then sets the hood where it
	 * needs to go.
	 * 
	 * @param distance
	 *            The distance from the target calculated by the Jetson.
	 */
	public void setDistance(double distance) {
		hoodTalon.changeControlMode(TalonControlMode.Position);
		double angle;
		if (distance <= 8.0) {
			angle = 2.75708 * Math.pow(distance, 2) - 44.14092 * distance + 515;
		} else {
			angle = 370;
		}
		SmartDashboard.putNumber("setting hood angle", angle);
		hoodTalon.set(safetyCheck(angle));
	}

	/**
	 * Gets the height of the hood. This is measured in degrees, with 0 being
	 * all the way up and 90 being all the way down. In other words, it's the
	 * angle of the trajectory of the ball.
	 * 
	 * @return the angle of the ball's trajectory.
	 */
	public double getHeight() {
		return 90.0 * (hoodTalon.getPosition() - Constants.HOOD_0_POS) / (Constants.HOOD_90_POS - Constants.HOOD_0_POS);
	}

	/**
	 * Directly set the velocity of the hood, using PercentVbus mode.
	 * 
	 * @param velocity
	 *            the hood motor's throttle.
	 */
	public void setVel(double velocity) {
		hoodTalon.changeControlMode(TalonControlMode.PercentVbus);
		hoodTalon.set(-velocity);
	}

	/**
	 * Sets the hood to a flat position. At this position (position 90), the
	 * robot will fit under the low bar.
	 */
	public void flatten() {
		target = null;
		setAngle(90.0);
	}

	/**
	 * Checks to see whether or not the hood is set to a flat position. This
	 * will return true if the hood is on its way to a flat position.
	 * 
	 * @return true if the hood is down, otherwise false.
	 */
	public boolean isFlat() {
		return target == null;
	}

	/**
	 * Checks to see whether the hood is being manually controlled.
	 * 
	 * @return true if the hood is being manually controlled, otherwise false
	 */
	public boolean isManuallyControlled() {
		return hoodTalon.getControlMode().equals(TalonControlMode.PercentVbus);
	}

	/**
	 * Sets the hood's target. This value is used by the Target command when
	 * determining where to aim.
	 * 
	 * @param target
	 *            the hood's target.
	 */
	public void setTarget(HoodTarget target) {
		this.target = target;
		SmartDashboard.putNumber("hood set height", SmartDashboard.getNumber("hood set height", HoodTarget.NORMAL.getHeight()));
		double height = SmartDashboard.getNumber("hood set height");
		setAngle(height + adjustment.difference());
	}

	/**
	 * Gets the hood's target: AUTO, LOW, or HIGH.
	 * 
	 * @return the hoods target.
	 */
	public HoodTarget getTarget() {
		return target;
	}

	/**
	 * Determines whether the hood's position is on target. This makes sure the
	 * hood Talon is in the correct mode and its error is within the tolerance.
	 * 
	 * @return true if the hood is on target, otherwise false.
	 */
	public boolean onTarget() {
		return hoodTalon.getControlMode().equals(TalonControlMode.Position) && getHeight() < 85.0
				&& hoodTalon.getError() < Constants.HOOD_TOLERANCE * Math.abs(Constants.HOOD_90_POS - Constants.HOOD_0_POS) / 90.0;
	}

	/**
	 * Gets the amperage going through the hood motor.
	 * 
	 * @return The output current of the hood Talon.
	 */
	public double getCurrent() {
		return hoodTalon.getOutputCurrent();
	}

	/**
	 * Makes sure the input value is within the potentiometer range specified in
	 * the Constants file.
	 * 
	 * @param input
	 *            the value to check.
	 * @return the same value if it is within the bounds, otherwise whichever
	 *         bound was hit.
	 */
	private static double safetyCheck(double input) {
		return Math.min(Math.max(input, Constants.HOOD_0_POS), Constants.HOOD_90_POS);
	}

}
