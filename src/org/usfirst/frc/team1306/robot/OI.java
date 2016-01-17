package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.TurnTurretCCW;
import org.usfirst.frc.team1306.robot.commands.turret.TurnTurretCW;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private final XboxController xbox;
	
	private final Button buttonA;
	private final Button buttonB;
	private final Button buttonX;
	private final Button buttonY;

	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		
		buttonA = new JoystickButton(xbox, XboxController.A);
		buttonB = new JoystickButton(xbox, XboxController.B);
		
		buttonX = new JoystickButton(xbox, XboxController.X);
		buttonY = new JoystickButton(xbox, XboxController.Y);
		
		buttonA.whenPressed(new SpinUp());
		buttonB.whenPressed(new Fire());
		
		buttonX.whileHeld(new TurnTurretCW());
		buttonY.whileHeld(new TurnTurretCCW());
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
