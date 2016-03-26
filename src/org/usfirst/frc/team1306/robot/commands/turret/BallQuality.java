package org.usfirst.frc.team1306.robot.commands.turret;

public enum BallQuality {

	NEW(-1), MEDIUM(0), OLD(1);
	
	private final double difference;
	
	public double difference() {
		return difference;
	}
	
	private BallQuality(double difference) {
		this.difference = difference;
	}
}
