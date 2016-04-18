package org.usfirst.frc.team1306.robot.commands.turret;

public enum Adjustment {

	LOWER(-2, "Lower"), NONE(0, "Normal"), HIGHER(2, "Higher");
	
	private final double difference;
	private final String name;
	
	public double difference() {
		return difference;
	}
	
	public String toString() {
		return name;
	}
	
	private Adjustment(double difference, String name) {
		this.difference = difference;
		this.name = name;
	}
}
