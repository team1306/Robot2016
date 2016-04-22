package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.Adjustment;
import org.usfirst.frc.team1306.robot.commands.turret.HoodTarget;
import org.usfirst.frc.team1306.robot.vision.Vision;

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

	private static final boolean ENABLED = true;

	/** The motor that moves the hood */
	private final CANTalon hoodTalon;
	/** The current target for the Hood. This is null if the hood is flat. */
	private HoodTarget target;
	/** The adjustment for the set hood angle. */
	private Adjustment adjustment;

	/**
	 * Constructs a Hood and enables PID position control.
	 */
	public Hood() {
		if (!ENABLED) {
			return;
		}

		adjustment = Adjustment.NONE;
		target = null;
		hoodTalon = new CANTalon(RobotMap.HOOD_TALON_PORT);
		hoodTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.enable();

	}

	/**
	 * Sets the default command for the Hood. Nothing is done to the hood until
	 * commands are called, so no default command must be specified.
	 */
	@Override
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
		if (!ENABLED) {
			return;
		}

		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.set(
				safetyCheck(Constants.HOOD_0_POS + position * (Constants.HOOD_90_POS - Constants.HOOD_0_POS) / 90.0));

	}

	/**
	 * Sets the adjustment on the hood. Used to shift all angles (except flat)
	 * by the required amount.
	 * 
	 * @param adjustment
	 *            the new adjustment
	 */
	public void setAdjustment(Adjustment adjustment) {
		if (!ENABLED) {
			return;
		}

		this.adjustment = adjustment;

	}

	/**
	 * Gets the current adjustment setting on the hood. Used for letting the
	 * drivers know on the SmartDashboard.
	 * 
	 * @return the currently set adjustment.
	 */
	public Adjustment getAdjustment() {
		if (!ENABLED) {
			return null;
		}

		return adjustment;

	}

	/**
	 * Set the height of the hood based on the distance from the target. The
	 * distance is feet. This does the math to find the ideal hood angle using
	 * an equation determined using regression on some tested distances, and
	 * then sets the hood where it needs to go.
	 * 
	 * @param distance
	 *            The distance from the target calculated by the Jetson.
	 */
	private double getAngleForDistance(double distance) {
		if (!ENABLED) {
			return 0.0;
		}

		return Constants.HOOD_AUTOTARGET_A * distance * distance + Constants.HOOD_AUTOTARGET_B * distance
				+ Constants.HOOD_AUTOTARGET_C;

	}

	/**
	 * Gets the height of the hood. This is measured in degrees, with 0 being
	 * all the way up and 90 being all the way down. In other words, it's the
	 * angle of the trajectory of the ball.
	 * 
	 * @return the angle of the ball's trajectory.
	 */
	public double getHeight() {
		if (!ENABLED) {
			return 0.0;
		}

		return 90.0 * (hoodTalon.getPosition() - Constants.HOOD_0_POS) / (Constants.HOOD_90_POS - Constants.HOOD_0_POS);

	}

	/**
	 * Directly set the throttle of the hood, using PercentVbus mode.
	 * 
	 * @param velocity
	 *            the hood motor's throttle.
	 */
	public void setVel(double velocity) {
		if (!ENABLED) {
			return;
		}

		hoodTalon.changeControlMode(TalonControlMode.PercentVbus);
		hoodTalon.set(-velocity);

	}

	/**
	 * Sets the hood to a flat position. At this position (position 90), the
	 * robot will fit under the low bar.
	 */
	public void flatten() {
		if (!ENABLED) {
			return;
		}

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
		if (!ENABLED) {
			return false;
		}

		return target == null;

	}

	/**
	 * Checks to see whether the hood is being manually controlled.
	 * 
	 * @return true if the hood is being manually controlled, otherwise false.
	 */
	public boolean isManuallyControlled() {
		if (!ENABLED) {
			return false;
		}

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
		if (!ENABLED) {
			return;
		}

		this.target = target;
		if (target == null) {
			flatten();
		} else {
			double height;
			if (target.equals(HoodTarget.AUTO)) {
				if (Vision.canSeeTarget()) {
					height = getAngleForDistance(Vision.distanceFeet());
				} else {
					SmartDashboard.putNumber("hood set height",
							SmartDashboard.getNumber("hood set height", HoodTarget.AUTO.getHeight()));
					height = SmartDashboard.getNumber("hood set height");
				}
			} else {
				height = target.getHeight();
			}
			setAngle(height + adjustment.difference());
		}

	}

	/**
	 * Gets where the hood is currently targeted.
	 * 
	 * @return the hood's target, or null if it's flat.
	 */
	public HoodTarget getTarget() {
		if (!ENABLED) {
			return null;
		}

		return target;

	}

	/**
	 * Determines whether the hood's position is on target. This makes sure the
	 * hood Talon is in the correct mode and its error is within the tolerance.
	 * Used by the drivers so they know when they can shoot.
	 * 
	 * @return true if the hood is on target, otherwise false.
	 */
	public boolean onTarget() {
		if (!ENABLED) {
			return false;
		}

		return hoodTalon.getControlMode().equals(TalonControlMode.Position) && getHeight() < 85.0 && hoodTalon
				.getError() < Constants.HOOD_TOLERANCE * Math.abs(Constants.HOOD_90_POS - Constants.HOOD_0_POS) / 90.0;

	}

	/**
	 * Gets the amperage going through the hood motor.
	 * 
	 * @return The output current of the hood Talon.
	 */
	public double getCurrent() {
		if (!ENABLED) {
			return 0.0;
		}

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
