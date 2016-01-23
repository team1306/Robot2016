package org.usfirst.frc.team1306.robot.vision;

import edu.wpi.first.wpilibj.Timer;

public class Vision {

	private VisionData recentData;

	public Vision() {
		recentData = null;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public VisionData getData() {
		if (recentData != null && Timer.getFPGATimestamp() - recentData.getTimestamp() < PERIOD) {
			return recentData;
		}

		// TODO James, put your code here.
		double pitch = 0.0;
		double yaw = 0.0;
		double distance = 0.0;

		VisionData newData = new VisionData(pitch, yaw, distance);
		recentData = newData;
		return newData;
	}

	public boolean canSeeTarget() {
		return getData().getDistance() > 0.0;
	}

	private final static double PERIOD = 0.2;

}