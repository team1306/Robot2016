package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

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
		// setDefaultCommand(new AutoTarget());
		// setDefaultCommand(new ManualTarget());
	}

	public void turnCW() {
		turretTalon.changeControlMode(TalonControlMode.Speed);
		turretTalon.set(Constants.TURRET_MAX_SPEED);
	}

	public void turnCCW() {
		turretTalon.changeControlMode(TalonControlMode.Speed);
		turretTalon.set(-Constants.TURRET_MAX_SPEED);
	}

	public void goToPosition(double position) {
		turretTalon.changeControlMode(TalonControlMode.Position);
		turretTalon.set(position);
	}

	public boolean onTarget() {
		return turretTalon.getControlMode().equals(TalonControlMode.Position)
				&& turretTalon.getError() < Constants.TURRET_TOLERANCE;
	}
}
