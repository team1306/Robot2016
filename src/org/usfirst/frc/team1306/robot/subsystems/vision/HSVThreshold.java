package org.usfirst.frc.team1306.robot.subsystems.vision;

import com.ni.vision.NIVision.Range;

final class HSVThreshold {

	private final int hueMin, hueMax, satMin, satMax, valMin, valMax;
	
	HSVThreshold(int hueMin, int hueMax, int satMin, int satMax, int valMin, int valMax) {
		this.hueMin = hueMin;
		this.hueMax = hueMax;
		this.satMin = satMin;
		this.satMax = satMax;
		this.valMin = valMin;
		this.valMax = valMax;
	}
	
	Range hueRange() {
		return new Range(hueMin, hueMax);
	}
	
	Range satRange() {
		return new Range(satMin, satMax);
	}
	
	Range valRange() {
		return new Range(valMin, valMax);
	}
}
