package org.usfirst.frc.team1306.robot.commands.turret;

public enum BallQuality {

	NEW(-2, "Lower"), MEDIUM(0, "Normal"), OLD(2, "Higher");
	
	private final double difference;
	private final String name;
	
	public double difference() {
		return difference;
	}
	
	public String toString() {
		return name;
	}
	
	private BallQuality(double difference, String name) {
		this.difference = difference;
		this.name = name;
	}
}
