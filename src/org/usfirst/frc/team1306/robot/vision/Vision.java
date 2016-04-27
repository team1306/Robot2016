package org.usfirst.frc.team1306.robot.vision;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.usfirst.frc.team1306.robot.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A class for getting data from the Jetson.
 * 
 * @author James Tautges
 */
public class Vision {

	private static Socket jetson;
	private static BufferedReader in;
	private static PrintWriter out;

	private static boolean isConnected = true;

	/** The most recent data retrieved from the Jetson. */
	private static VisionData recentData;

	private static Timer timer;
	private static Timer connectionTimer;

	private Vision() {

	}

	/**
	 * Creates a new Vision object with no data.
	 */
	public static void init() {
		connectToJetson();
		recentData = null;
		timer = new Timer();
		connectionTimer = new Timer();
		timer.start();
		connectionTimer.start();
	}

	/**
	 * Returns the most recent data if it is recent enough, otherwise gets new
	 * data from the Jetson and returns that.
	 * 
	 * @return recent data from the Jetson.
	 */
	public static VisionData getData() {
		if (timer.hasPeriodPassed(Constants.VISION_PERIOD) || recentData == null) {
			double pitch = 0.0;
			double yaw = 0.0;
			double distance = 0.0;

			if (isConnected && !jetson.isClosed()) {
				String data = null;
				try {
					out.println("hello");
					out.flush();
					data = in.readLine();
				} catch (Exception e) {
					e.printStackTrace();
					isConnected = false;
					recentData = new VisionData(0.0, 0.0, 0.0);
					return recentData;
				}
				if (data == null) {
					isConnected = false;
					recentData = new VisionData(0.0, 0.0, 0.0);
					return recentData;
				}

				SmartDashboard.putString("data", data);
				String[] numbers = data.split(",");
				pitch = Double.parseDouble(numbers[0]);
				yaw = Double.parseDouble(numbers[1]);
				distance = Double.parseDouble(numbers[2]);

				// convert units
				yaw = -yaw / 10;
			} else {
				isConnected = false;
				if (connectionTimer.hasPeriodPassed(1.0)) {
					connectToJetson();
				}
			}

			VisionData newData = new VisionData(pitch, yaw, distance);
			recentData = newData;
			return newData;

		} else {
			return recentData;
		}

	}

	private static void connectToJetson() {
		try {
			jetson = new Socket(Constants.VISION_HOSTNAME, Constants.VISION_PORT_NUMBER);
			in = new BufferedReader(new InputStreamReader(jetson.getInputStream()));
			out = new PrintWriter(jetson.getOutputStream());
			isConnected = true;
			SmartDashboard.putString("error", "no error");
			out.println('a');
			SmartDashboard.putBoolean("Connected", true);
		} catch (Exception e) {
			e.printStackTrace();
			SmartDashboard.putString("error", e.getMessage());
			isConnected = false;
			SmartDashboard.putBoolean("Connected", false);
		}
	}

	/**
	 * Finds whether a target is detected.
	 * 
	 * @return true if a target is detected, otherwise false
	 */
	public static boolean canSeeTarget() {
		return recentData != null && recentData.getDistance() > 0.0;
	}

	public static boolean isConnected() {
		return isConnected;
	}

	public static double distanceFeet() {
		double distance = getData().getDistance();
		return -0.253 * distance * distance + 4.1235 * distance - 2.0429;
	}

	public static VisionStatus getStatus() {

		if (!isConnected()) {
			return VisionStatus.DISCONNECTED;
		} else if (!canSeeTarget()) {
			return VisionStatus.INVISIBLE;
		} else if (distanceFeet() > Constants.MAX_DISTANCE) {
			return VisionStatus.FAR;
		} else if (distanceFeet() < Constants.MIN_DISTANCE) {
			return VisionStatus.CLOSE;
		}
		return VisionStatus.INRANGE;

	}

}