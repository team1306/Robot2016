package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hood extends Subsystem {

	private final CANTalon hoodTalon;

	public Hood() {

		hoodTalon = new CANTalon(RobotMap.hoodTalonPort);
		hoodTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		hoodTalon.changeControlMode(TalonControlMode.Position);
		hoodTalon.set(hoodTalon.get());
		hoodTalon.enable();

	}

	public void initDefaultCommand() {
	}

	public void setHeight(double position) {
		hoodTalon.set(position);
	}

}
