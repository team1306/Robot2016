package org.usfirst.frc.team1306.robot.vision;

import org.usfirst.frc.team1306.robot.Constants;

import edu.wpi.first.wpilibj.Timer;

/**
 * A class for getting data from the Jetson.
 * 
 * @author James Tautges
 */
public class Vision {

	/** The most recent data retrieved from the Jetson. */
	private VisionData recentData;

	private Timer timer;

	/**
	 * Creates a new Vision object with no data.
	 */
	public Vision() {
		recentData = null;
		timer = new Timer();
		timer.start();
	}

	/**
	 * Returns the most recent data if it is recent enough, otherwise gets new
	 * data from the Jetson and returns that.
	 * 
	 * @return recent data from the Jetson.
	 */
	public VisionData getData() {
		if (timer.hasPeriodPassed(Constants.VISION_PERIOD) || recentData == null) {
			// TODO James, put your code here.
			double pitch = 0.0;
			double yaw = 0.0;
			double distance = 0.0;

			VisionData newData = new VisionData(pitch, yaw, distance);
			recentData = newData;
			return newData;
		} else {
			return recentData;
		}
	}

	/**
	 * Finds whether a target is detected.
	 * 
	 * @return true if a target is detected, otherwise false
	 */
	public boolean canSeeTarget() {
		return getData().getDistance() > 0.0;
	}

}