package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private final Talon roller1;
	private final Talon roller2;

	private final DoubleSolenoid leftIntakeSol;
	private final DoubleSolenoid rightIntakeSol;

	public Intake() {
		roller1 = new Talon(RobotMap.intakeRoller1Port);
		roller2 = new Talon(RobotMap.intakeRoller2Port);

		leftIntakeSol = new DoubleSolenoid(RobotMap.intakeSol1PortA, RobotMap.intakeSol1PortB);
		rightIntakeSol = new DoubleSolenoid(RobotMap.intakeSol2PortA, RobotMap.intakeSol2PortB);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	/**
	 * Toggle the rollers
	 */
	public void toggleRollers() {
		if (roller1.get() > 0) {
			stopRollers();
		} else {
			startRollers();
		}
	}

	/**
	 * Set the rollers spinning
	 */
	public void startRollers() {
		roller1.set(1.0);
		roller2.set(1.0);
	}

	/**
	 * Stop the rollers spinning
	 */
	public void stopRollers() {
		roller1.set(0.0);
		roller2.set(0.0);
	}

	/**
	 * Toggle the tusk state
	 */
	public void toggleTuskState() {
		if (leftIntakeSol.get() == DoubleSolenoid.Value.kForward) {
			retractTusks();
		} else {
			extendTusks();
		}
	}

	/**
	 * Actuate the solenoids to extend the tusks
	 */
	public void extendTusks() {
		if (15 - Constants.INTAKE_LENGTH * Math.cos(getAngle()) < Constants.TUSK_LENGTH) {
			leftIntakeSol.set(DoubleSolenoid.Value.kForward);
			rightIntakeSol.set(DoubleSolenoid.Value.kForward);
		} else {
			retractTusks();
		}
	}

	/**
	 * Actuate the solenoids to retract the tusks
	 */
	public void retractTusks() {
		leftIntakeSol.set(DoubleSolenoid.Value.kReverse);
		rightIntakeSol.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * Return the angle of the intake from the horizontal
	 * 
	 * @return Angle of intake
	 */
	public double getAngle() {
		// TODO write this code
		return 0.0;
	}
}
