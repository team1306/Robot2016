package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.drivetrain.ShiftDown;
import org.usfirst.frc.team1306.robot.commands.drivetrain.ShiftUp;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmDown;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmPickup;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmVertical;
import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.Target;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;
import org.usfirst.frc.team1306.robot.triggers.DPadDown;
import org.usfirst.frc.team1306.robot.triggers.DPadRight;
import org.usfirst.frc.team1306.robot.triggers.DPadUp;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author James Tautges, Finn Voichick
 */
public class OI {

	// Driver controls
	private final XboxController xbox;
	private final Joystick targetingStick;

	// Buttons and triggers
	private final Button buttonA;
	private final Button buttonB;
	private final Button buttonX;
	private final Button buttonY;
	private final Button bumperL;
	private final Button bumperR;
	private final Trigger dPadUp;
	private final Trigger dPadRight;
	private final Trigger dPadDown;

	// Initialize everything
	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		targetingStick = new Joystick(RobotMap.secondaryPort);

		buttonA = new JoystickButton(xbox, XboxController.A);
		buttonB = new JoystickButton(xbox, XboxController.B);
		buttonX = new JoystickButton(xbox, XboxController.X);
		buttonY = new JoystickButton(xbox, XboxController.Y);
		bumperL = new JoystickButton(xbox, XboxController.LB);
		bumperR = new JoystickButton(xbox, XboxController.RB);


		dPadUp = new DPadUp(xbox);
		dPadRight = new DPadRight(xbox);
		dPadDown = new DPadDown(xbox);

		// Bind input devices to commands
		buttonA.whenPressed(new Target());
		buttonB.whenPressed(new SpinUp());
		buttonX.whenPressed(new ResetTurret());
		buttonY.whenPressed(new Fire());
		bumperL.whenPressed(new ShiftDown());
		bumperR.whenPressed(new ShiftUp());
		dPadUp.whenActive(new IntakeArmVertical());
		dPadRight.whenActive(new IntakeArmPickup());
		dPadDown.whenActive(new IntakeArmDown());

	}

	/**
	 * Return the value of the Y axis of the main right joystick after applying
	 * a deadband
	 * 
	 * @return Y axis value of main right joystick
	 */
	public double getRightVel() {
		return deadband(xbox.getY(Hand.kRight));
	}

	/**
	 * Return the value of the Y axis of the main right joystick after applying
	 * a deadband
	 * 
	 * @return Y axis value of main right joystick
	 */
	public double getLeftVel() {
		return deadband(xbox.getY(Hand.kLeft));
	}

	public double getStraightVel() {
		return xbox.getRT() - xbox.getLT();
	}

	/**
	 * Return the X axis value of the main left joystick after applying a
	 * deadband
	 * 
	 * @return X axis value of the main left joystick
	 */
	public double getLeftX() {
		return deadband(xbox.getX(Hand.kLeft));
	}

	public int getPOV() {
		return xbox.getPOV();
	}

	public double getTurretVel() {
		return targetingStick.getX();
	}
	
	public double getHoodVel() {
		return targetingStick.getY();
	}

	public boolean getManualOverride() {
		return targetingStick.getTrigger();
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
