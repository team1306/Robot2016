package org.usfirst.frc.team1306.robot.triggers;

public enum DPadDirection {

	UP(0), UPRIGHT(45), RIGHT(90), DOWNRIGHT(135), DOWN(180), DOWNLEFT(225), LEFT(270), UPLEFT(315);

	private final int angle;

	private DPadDirection(int angle) {
		this.angle = angle;
	}

	public int getAngle() {
		return angle;
	}
}
