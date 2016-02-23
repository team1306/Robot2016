package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
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

	/**
	 * Constructs a Hood and enables PID position control.
	 */
	public Hood() {

		target = HoodTarget.AUTO;
		hoodTalon = new CANTalon(RobotMap.hoodTalonPort);
		hoodTalon.configMaxOutputVoltage(Constants.HOOD_MAX_VOLTAGE);
		hoodTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		hoodTalon.changeControlMode(TalonControlMode.Position);
		// hoodTalon.set(hoodTalon.get());
		hoodTalon.enable();

	}

	/**
	 * Sets the default command for the Hood. The hood is affected by the same
	 * methods as the turret, so no default command must be specified.
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
	public void setHeight(double position) {
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.set(
				safetyCheck(Constants.HOOD_0_POS + position * (Constants.HOOD_90_POS - Constants.HOOD_0_POS) / 90.0));
	}

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

	public double getHeight() {
		return 90.0 * (hoodTalon.getPosition() - Constants.HOOD_0_POS) / (Constants.HOOD_90_POS - Constants.HOOD_0_POS);
	}

	public void setVel(double velocity) {
		hoodTalon.changeControlMode(TalonControlMode.PercentVbus);
		hoodTalon.set(-velocity);
	}

	/**
	 * Sets the hood to a flat position. At this position (position 90), the
	 * robot will fit under the low bar.
	 */
	public void flatten() {
		setHeight(90.0);
	}

	public boolean isFlat() {
		return hoodTalon.getControlMode().equals(TalonControlMode.Position) && hoodTalon.getSetpoint() == 90.0;
	}
	
	public boolean isManuallyControlled() {
		return hoodTalon.getControlMode().equals(TalonControlMode.PercentVbus);
	}

	public void setTarget(HoodTarget target) {
		this.target = target;
	}

	public HoodTarget getTarget() {
		return target;
	}

	public boolean onTarget() {
		return hoodTalon.getControlMode().equals(TalonControlMode.Position) && hoodTalon.getSetpoint() < 90.0
				&& hoodTalon.getError() < Constants.HOOD_TOLERANCE;
	}

	public double getCurrent() {
		return hoodTalon.getOutputCurrent();
	}

	private double safetyCheck(double input) {
		return Math.min(Math.max(input, Constants.HOOD_0_POS), Constants.HOOD_90_POS);
	}

}
