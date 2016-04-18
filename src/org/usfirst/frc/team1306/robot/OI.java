package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.drivetrain.ShiftDown;
import org.usfirst.frc.team1306.robot.commands.drivetrain.ShiftUp;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmDown;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmPickup;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmRest;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmVertical;
import org.usfirst.frc.team1306.robot.commands.intake.Pass;
import org.usfirst.frc.team1306.robot.commands.intake.RollUntilPickup;
import org.usfirst.frc.team1306.robot.commands.intake.StopRoll;
import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.LowSpinOff;
import org.usfirst.frc.team1306.robot.commands.shooter.LowSpinOn;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.BatterTargetClose;
import org.usfirst.frc.team1306.robot.commands.turret.BatterTargetFar;
import org.usfirst.frc.team1306.robot.commands.turret.HoodSetAdjustmentHigher;
import org.usfirst.frc.team1306.robot.commands.turret.HoodSetAdjustmentLower;
import org.usfirst.frc.team1306.robot.commands.turret.HoodSetAdjustmentNone;
import org.usfirst.frc.team1306.robot.commands.turret.HoodSetTargetLow;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;
import org.usfirst.frc.team1306.robot.commands.turret.Target;
import org.usfirst.frc.team1306.robot.triggers.DPadDirection;
import org.usfirst.frc.team1306.robot.triggers.DPadPress;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class, the Operator Interface, is the glue that binds the controls on
 * the physical operator interface to the commands and command groups that allow
 * control of the robot.
 * 
 * @author Finn Voichick, James Tautges
 */
public class OI {

	// Driver controls
	private final XboxController xbox;
	private final XboxController secondary;

	// Buttons and triggers
	private final Button buttonA;
	private final Button buttonB;
	private final Button buttonX;
	private final Button buttonY;
	private final Button buttonBack;
	private final Button buttonStart;
	private final Button bumperL;
	private final Button bumperR;
	private final Trigger dPadUp;
	private final Trigger dPadRight;
	private final Trigger dPadLeft;
	private final Trigger dPadDown;

	private final Button buttonA2;
	private final Button buttonB2;
	private final Button buttonX2;
	private final Button buttonY2;
	private final Button buttonStart2;
	private final Button buttonBack2;

	private final Trigger dPad2Up;
	private final Trigger dPad2Right;
	private final Trigger dPad2Down;

	private final Button bumperR2;

	/**
	 * Initializes the controllers and maps individual buttons to commands.
	 */
	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		secondary = new XboxController(RobotMap.secondaryPort);

		buttonA = new JoystickButton(xbox, XboxController.A);
		buttonB = new JoystickButton(xbox, XboxController.B);
		buttonX = new JoystickButton(xbox, XboxController.X);
		buttonY = new JoystickButton(xbox, XboxController.Y);
		buttonBack = new JoystickButton(xbox, XboxController.BACK);
		buttonStart = new JoystickButton(xbox, XboxController.START);
		bumperL = new JoystickButton(xbox, XboxController.LB);
		bumperR = new JoystickButton(xbox, XboxController.RB);

		buttonA2 = new JoystickButton(secondary, XboxController.A);
		buttonB2 = new JoystickButton(secondary, XboxController.B);
		buttonX2 = new JoystickButton(secondary, XboxController.X);
		buttonY2 = new JoystickButton(secondary, XboxController.Y);
		buttonStart2 = new JoystickButton(secondary, XboxController.START);
		buttonBack2 = new JoystickButton(secondary, XboxController.BACK);

		bumperR2 = new JoystickButton(secondary, XboxController.RB);

		dPadUp = new DPadPress(xbox, DPadDirection.UP);
		dPadRight = new DPadPress(xbox, DPadDirection.RIGHT);
		dPadLeft = new DPadPress(xbox, DPadDirection.LEFT);
		dPadDown = new DPadPress(xbox, DPadDirection.DOWN);

		dPad2Up = new DPadPress(secondary, DPadDirection.UP);
		dPad2Right = new DPadPress(secondary, DPadDirection.RIGHT);
		dPad2Down = new DPadPress(secondary, DPadDirection.DOWN);

		// Bind input devices to commands
		buttonA.whenPressed(new Fire());
		buttonB.whenPressed(new StopRoll());
		buttonB.whenPressed(new ResetTurret());
		buttonX.whenPressed(new SpinUp());
		buttonY.whenPressed(new ResetTurret());
		buttonY.whenPressed(new IntakeArmPickup());
		buttonY.whenPressed(new RollUntilPickup());
		buttonBack.whenPressed(new ResetTurret());
		buttonBack.whenPressed(new Pass());
		buttonStart.whenPressed(new Target());
		bumperL.whenPressed(new ShiftDown());
		bumperR.whenPressed(new ShiftUp());
		dPadUp.whenActive(new IntakeArmVertical());
		dPadRight.whenActive(new IntakeArmPickup());
		dPadLeft.whenActive(new IntakeArmDown());
		dPadDown.whenActive(new IntakeArmRest());

		buttonA2.whenPressed(new HoodSetTargetLow());
		buttonB2.whenPressed(new HoodSetAdjustmentHigher());
		buttonX2.whenPressed(new HoodSetAdjustmentLower());
		buttonY2.whenPressed(new HoodSetAdjustmentNone());
		buttonStart2.whenPressed(new LowSpinOn());
		buttonBack2.whenPressed(new LowSpinOff());
		bumperR2.whenPressed(new Fire());

		dPad2Up.whenActive(new BatterTargetClose());
		dPad2Right.whenActive(new BatterTargetFar());
		dPad2Down.whenActive(new Target());
	}

	/**
	 * Return the value of the Y axis of the main right joystick after applying
	 * a deadband. It is put to a power in case a variable responsiveness is
	 * desired.
	 * 
	 * @return Y axis value of main right joystick
	 */
	public double getRightVel() {
		return Math.pow(deadband(xbox.getY(Hand.kRight)), Constants.JOYSTICK_POWER);
	}

	/**
	 * Return the value of the Y axis of the main left joystick after applying a
	 * deadband. It is put to a power in case a variable responsiveness is
	 * desired.
	 * 
	 * @return Y axis value of main right joystick
	 */
	public double getLeftVel() {
		return Math.pow(deadband(xbox.getY(Hand.kLeft)), Constants.JOYSTICK_POWER);
	}

	/**
	 * Gets the intended straightline velocity of the robot, on a scale from
	 * -1.0 to 1.0. This is controlled by the triggers. It is put to a power in
	 * case a variable responsiveness is desired.
	 * 
	 * @return
	 */
	public double getStraightVel() {
		return Math.pow(xbox.getRT() - xbox.getLT(), Constants.JOYSTICK_POWER);
	}

	/**
	 * Gets the D-Pad of the main Xbox controller, measured in degrees.
	 * 
	 * @return
	 */
	public int getPOV() {
		return xbox.getPOV();
	}

	/**
	 * Gets the intended velocity of the turret. This is controlled by the right
	 * joystick on the secondary controller only when targeting is manually
	 * overridden.
	 * 
	 * @return the overridden velocity of the turret.
	 */
	public double getTurretVel() {
		return deadband(secondary.getX(Hand.kRight));
	}

	/**
	 * Gets the intended velocity of the hood. This is controlled by the left
	 * joystick on the secondary controller only when targeting is manually
	 * overridden.
	 * 
	 * @return the overridden velocity of the hood.
	 */
	public double getHoodVel() {
		return deadband(secondary.getY(Hand.kLeft));
	}

	/**
	 * Gets whether the turret is overridden. The turret is overridden when the
	 * right trigger is pressed.
	 * 
	 * @return true if the turret should be manually overridden, otherwise
	 *         false.
	 */
	public boolean getTurretOverrride() {
		return secondary.getRT() > 0.5;
	}

	/**
	 * Gets whether the hood is overridden. The hood is overridden when the
	 * secondary left trigger is pressed.
	 * 
	 * @return true if the hood should be manually overridden, otherwise false.
	 */
	public boolean getHoodOverride() {
		return secondary.getLT() > 0.5;
	}

	/**
	 * Apply a deadband to the given value. This means that the value graph is
	 * split around zero a certain amount. This fixes the imprecise zeroing of
	 * Xbox joysticks.
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
