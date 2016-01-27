package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.AutoTarget;
import org.usfirst.frc.team1306.robot.commands.turret.ManualTarget;
import org.usfirst.frc.team1306.robot.commands.turret.SnapForward;
import org.usfirst.frc.team1306.robot.triggers.DPadUp;
import org.usfirst.frc.team1306.robot.triggers.SecondaryTriggers;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author James Tautges
 */
public class OI {

	// Driver controls
	private final XboxController xbox;
	private final XboxController secondary;

	// Buttons and triggers
	private final Button buttonA;
	private final Button buttonB;
	private final Button secondaryA;
	private final Trigger dPadUp;
	private final Trigger secondaryTriggers;

	// Initialize everything
	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		secondary = new XboxController(RobotMap.secondaryPort);

		buttonA = new JoystickButton(xbox, XboxController.A);
		buttonB = new JoystickButton(xbox, XboxController.B);
		secondaryA = new JoystickButton(secondary, XboxController.A);
		dPadUp = new DPadUp(secondary);
		secondaryTriggers = new SecondaryTriggers(secondary);

		// Bind input devices to commands
		buttonA.whenPressed(new SpinUp());
		buttonB.whenPressed(new Fire());
		secondaryA.whenPressed(new AutoTarget());
		dPadUp.whenActive(new SnapForward());
		secondaryTriggers.whenActive(new ManualTarget());
	}

	/**
	 * Get the value of the start button on the secondary controller. This
	 * corresponds to starting auto targeting
	 * 
	 * @return Value of secondary start button
	 */

	public boolean autoTargetingStart() {
		return secondary.getRawButton(XboxController.START);
	}

	/**
	 * Get the value of the back button on the secondary controller. This
	 * corresponds to stopping auto targeting
	 * 
	 * @return Value of secondary back button
	 */

	public boolean autoTargetingStop() {
		return secondary.getRawButton(XboxController.BACK);
	}

	/**
	 * Return the value of the Y axis of the main right joystick after applying
	 * a deadband
	 * 
	 * @return Y axis value of main right joystick
	 */

	public double getRight() {
		return deadband(xbox.getY(Hand.kRight));
	}

	/**
	 * Return the value of the Y axis of the main right joystick after applying
	 * a deadband
	 * 
	 * @return Y axis value of main right joystick
	 */

	public double getLeft() {
		return deadband(xbox.getY(Hand.kLeft));
	}

	/**
	 * Return the value of the main left trigger (no deadband)
	 * 
	 * @return Value of main left trigger
	 */

	public double getLeftTrigger() {
		return xbox.getLT();
	}

	/**
	 * Return the value of the main right trigger (no deadband)
	 * 
	 * @return Value of main right trigger
	 */
	
	public double getRightTrigger() {
		return xbox.getRT();
	}

	/**
	 * Return the X axis value of the main left joystick after applying a deadband
	 * 
	 * @return X axis value of the main left joystick
	 */
	
	public double getLeftX() {
		return deadband(xbox.getX(Hand.kLeft));
	}

	public int getPOV() {
		return xbox.getPOV();
	}
	
	/**
	 * Return the value of the secondary left trigger (no deadband)
	 * 
	 * @return Value of secondary left trigger
	 */
	
	public double getSecondaryLeftTrigger() {
		return secondary.getLT();
	}

	/**
	 * Return the value of the secondary right trigger (no deadband)
	 * 
	 * @return Value of main secondary trigger
	 */
	
	public double getSecondaryRightTrigger() {
		return secondary.getRT();
	}

	/**
	 * Apply a deadband to the given value. This means that the value graph is
	 * split around zero a certain amount. This fixes the imprecise zeroing of
	 * xbox joysticks
	 * 
	 * @param value
	 *            Value to deadband
	 * @return Deadbanded value
	 */

	private static double deadband(double value) {
		if (value < -Constants.DEADBAND) {
			return (value + Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		}
		if (value > Constants.DEADBAND) {
			return (value - Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		}
		return 0;
	}
}
