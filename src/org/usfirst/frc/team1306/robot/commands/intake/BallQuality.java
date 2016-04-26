package org.usfirst.frc.team1306.robot.commands.intake;

public enum BallQuality {

	OLD("DEAD BALL"), NEW("Good Ball");

	private final String name;

	private BallQuality(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
