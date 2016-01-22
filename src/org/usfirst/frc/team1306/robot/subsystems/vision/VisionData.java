package org.usfirst.frc.team1306.robot.subsystems.vision;

import edu.wpi.first.wpilibj.Timer;

public final class VisionData {

	private final double pitch, yaw, distance, timestamp;
	
	public VisionData(double pitch, double yaw, double distance) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.distance = distance;
		this.timestamp = Timer.getFPGATimestamp();
	}
	
	public double getPitch() {
		return pitch;
	}
	
	public double getYaw() {
		return yaw;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double timestamp() {
		return timestamp;
	}
}
