package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.AutoTarget;
import org.usfirst.frc.team1306.robot.commands.turret.ResetTurret;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private final XboxController xbox;
	private final XboxController secondary;

	private final Button buttonA;
	private final Button buttonB;
	private final Button buttonY;

	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		secondary = new XboxController(RobotMap.secondaryPort);

		buttonA = new JoystickButton(xbox, XboxController.A);
		buttonB = new JoystickButton(xbox, XboxController.B);
		buttonY = new JoystickButton(xbox, XboxController.Y);

		buttonA.whenPressed(new ResetTurret());
		buttonB.whenPressed(new SpinUp());
		buttonB.whenPressed(new AutoTarget());
		buttonY.whenPressed(new Fire());
	}

	public double getRightVel() {
		return deadband(xbox.getY(Hand.kRight));
	}

	public double getLeftVel() {
		return deadband(xbox.getY(Hand.kLeft));
	}

	public double getStraightVel() {
		return xbox.getRT() - xbox.getLT();
	}

	public double getLeftX() {
		return deadband(xbox.getX(Hand.kLeft));
	}

	public int getPOV() {
		return xbox.getPOV();
	}

	public double getTurretVel() {
		return secondary.getRT() - secondary.getLT();
	}

	public boolean getManualOverride() {
		return secondary.getRawButton(XboxController.START);
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
