package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.AutoTarget;
import org.usfirst.frc.team1306.robot.commands.turret.ManualTarget;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;
import org.usfirst.frc.team1306.robot.triggers.DPadUp;
import org.usfirst.frc.team1306.robot.triggers.SecondaryTriggers;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private final XboxController xbox;
	private final XboxController secondary;

	private final Button buttonA;
	private final Button buttonB;
	private final Button secondaryA;
	private final Trigger dPadUp;
	private final Trigger secondaryTriggers;

	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		secondary = new XboxController(RobotMap.secondaryPort);

		buttonA = new JoystickButton(xbox, XboxController.A);
		buttonB = new JoystickButton(xbox, XboxController.B);
		secondaryA = new JoystickButton(secondary, XboxController.A);
		dPadUp = new DPadUp(secondary);
		secondaryTriggers = new SecondaryTriggers(secondary);

		buttonA.whenPressed(new SpinUp());
		buttonB.whenPressed(new Fire());
		secondaryA.whenPressed(new AutoTarget());
		dPadUp.whenActive(new ResetTurret());
		secondaryTriggers.whenActive(new ManualTarget());
	}

	public boolean autoTargetingStart() {
		return secondary.getRawButton(XboxController.START);
	}

	public boolean autoTargetingStop() {
		return secondary.getRawButton(XboxController.BACK);
	}

	public double getRight() {
		return deadband(xbox.getY(Hand.kRight));
	}

	public double getLeft() {
		return deadband(xbox.getY(Hand.kLeft));
	}

	public double getLeftTrigger() {
		return xbox.getLT();
	}

	public double getRightTrigger() {
		return xbox.getRT();
	}

	public double getLeftX() {
		return deadband(xbox.getX(Hand.kLeft));
	}

	public int getPOV() {
		return xbox.getPOV();
	}

	public double getSecondaryLeftTrigger() {
		return secondary.getLT();
	}

	public double getSecondaryRightTrigger() {
		return secondary.getRT();
	}

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
