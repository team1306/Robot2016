package org.usfirst.frc.team1306.robot.commands.turret;

public enum ScanDirection {

	LEFT("Left"), RIGHT("Right");

	private final String name;

	private ScanDirection(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
