package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This Subsystem controls only the rollers on the intake arm, not the intake
 * arm itself. It has methods for bringing balls in and out of the robot. This
 * class has a lot of code that is commented out because it is still unsure
 * whether our robot will end up having pneumatic tusks.
 */
public class Intake extends Subsystem {

	/** One of the speed controllers that moves the intake. */
	private final SpeedController roller1;
	/** The other roller speed controller. */
	private final SpeedController roller2;

	// private final DoubleSolenoid leftIntakeSol;
	// private final DoubleSolenoid rightIntakeSol;

	/**
	 * Constructs an the intake with both rollers.
	 */
	public Intake() {
		roller1 = new Talon(RobotMap.intakeRoller1Port);
		roller1.setInverted(true);
		roller2 = new Talon(RobotMap.intakeRoller2Port);
		roller2.setInverted(true);

		// leftIntakeSol = new DoubleSolenoid(RobotMap.intakeSol1PortA,
		// RobotMap.intakeSol1PortB);
		// rightIntakeSol = new DoubleSolenoid(RobotMap.intakeSol2PortA,
		// RobotMap.intakeSol2PortB);

	}

	/**
	 * Sets the default command for the Intake. Nothing is done to the intake
	 * until commands are called, so no default command must be specified.
	 */
	public void initDefaultCommand() {
	}

	/**
	 * Toggle the rollers. This is never used.
	 */
	public void toggleRollers() {
		if (roller1.get() > 0) {
			stopRollers();
		} else {
			startRollers();
		}
	}

	/**
	 * Set the rollers spinning. This direction will pull balls in.
	 */
	public void startRollers() {
		roller1.set(1.0);
		roller2.set(1.0);
	}

	/**
	 * Stop the rollers spinning.
	 */
	public void stopRollers() {
		roller1.set(0.0);
		roller2.set(0.0);
	}

	/**
	 * Reverse the rollers. This will push balls out.
	 */
	public void reverse() {
		roller1.set(-1.0);
		roller2.set(-1.0);
	}

	// This code for the tusks isn't used, but may be re-added if pneumatic
	// tusks are later added.
	//
	//
	// /**
	// * Toggle the tusk state
	// */
	// public void toggleTuskState() {
	// if (leftIntakeSol.get() == DoubleSolenoid.Value.kForward) {
	// retractTusks();
	// } else {
	// extendTusks();
	// }
	// }
	//
	// /**
	// * Actuate the solenoids to extend the tusks
	// */
	// public void extendTusks() {
	// if (15 - Constants.INTAKE_LENGTH * Math.cos(getAngle()) <
	// Constants.TUSK_LENGTH) {
	// leftIntakeSol.set(DoubleSolenoid.Value.kForward);
	// rightIntakeSol.set(DoubleSolenoid.Value.kForward);
	// } else {
	// retractTusks();
	// }
	// }
	//
	// /**
	// * Actuate the solenoids to retract the tusks
	// */
	// public void retractTusks() {
	// leftIntakeSol.set(DoubleSolenoid.Value.kReverse);
	// rightIntakeSol.set(DoubleSolenoid.Value.kReverse);
	// }

}
