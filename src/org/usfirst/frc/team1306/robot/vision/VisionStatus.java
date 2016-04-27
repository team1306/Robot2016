package org.usfirst.frc.team1306.robot.vision;

public enum VisionStatus {

	DISCONNECTED("Vision disconnected"), INVISIBLE("Can't see target"), FAR("Get closer"), INRANGE("In range"), CLOSE(
			"Too close");

	private final String name;

	private VisionStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
