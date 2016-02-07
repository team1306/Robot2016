package org.usfirst.frc.team1306.robot.vision;

import java.io.BufferedReader;
import java.io.IOException;
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

	Socket jetson;
	BufferedReader in;
	PrintWriter out;

	private boolean isConnected = true;

	/** The most recent data retrieved from the Jetson. */
	private VisionData recentData;

	private Timer timer;
	private Timer connectionTimer;

	/**
	 * Creates a new Vision object with no data.
	 */
	public Vision() {
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
	public VisionData getData() {
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

	private void connectToJetson() {
		try {
			jetson = new Socket(hostName, portNumber);
			in = new BufferedReader(new InputStreamReader(jetson.getInputStream()));
			out = new PrintWriter(jetson.getOutputStream());
			isConnected = true;
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
	public boolean canSeeTarget() {
		return getData().getDistance() > 0.01;
	}

	// TODO put these constants in Constants

	/** The ip address and port number of the jetson */
	private final static String hostName = "tegra-ubuntu";
	private final static int portNumber = 5802;

}