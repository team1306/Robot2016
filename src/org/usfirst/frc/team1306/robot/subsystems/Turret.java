package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.ManualTarget;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {

	private final CANTalon turretTalon;

	// Initialize your subsystem here
	public Turret() {

		turretTalon = new CANTalon(RobotMap.turretTalonPort);
		turretTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get());
		turretTalon.enable();

	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualTarget());
	}

	/**
	 * Set the velocity of the turret motor, on a scale from -1.0 to 1.0
	 * 
	 * @param velocity
	 *            the new velocity
	 */
	public void setVel(double velocity) {
		turretTalon.changeControlMode(TalonControlMode.Speed);
		turretTalon.set(velocity * Constants.TURRET_MAX_SPEED);
	}

	public void setTarget(double position) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(position);
	}

	public void setTargetRelative(double angle) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get() + angle);
	}

	public void stop() {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(turretTalon.get());
	}

	public boolean onTarget() {
		return turretTalon.getControlMode().equals(TalonControlMode.Position)
				&& turretTalon.getError() < Constants.TURRET_TOLERANCE;
	}

}
