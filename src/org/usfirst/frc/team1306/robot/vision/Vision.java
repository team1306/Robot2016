package org.usfirst.frc.team1306.robot.vision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import edu.wpi.first.wpilibj.Timer;

/**
 * A class for getting data from the Jetson.
 * 
 * @author Finn Voichick
 */
public class Vision {

	/**
	 * This boolean represents whether making the socket was successful. It's
	 * only going to try once since repeated tries are what broke the robot last
	 * year.
	 */
	private boolean isConnected = true;

	/** Socket members */
	private Socket jetson;
	private PrintWriter out;
	private BufferedReader in;

	/** The most recent data retrieved from the Jetson. */
	private VisionData recentData;

	/**
	 * Creates a new Vision object with no data.
	 */
	public Vision() {
		recentData = null;

		try {
			jetson = new Socket(hostName, portNumber);
			out = new PrintWriter(jetson.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(jetson.getInputStream()));
			isConnected = true;

		} catch (Exception e) {
			jetson = null;
			out = null;
			in = null;
			isConnected = false;
		}
	}

	/**
	 * Returns the most recent data if it is recent enough, otherwise gets new
	 * data from the Jetson and returns that.
	 * 
	 * @return recent data from the Jetson.
	 */
	public VisionData getData() {
		if (recentData != null && Timer.getFPGATimestamp() - recentData.getTimestamp() < PERIOD) {
			return recentData;
		}
		
		double pitch = 0.0;
		double yaw = 0.0;
		double distance = 0.0;
		
		if (isConnected) {
			String data = null;
			while (true) {
				try {
					data = in.readLine();
				} catch (IOException e) {
					break;
				}
			}
			
			if (data != null) {
				//yaw = (int) data;
			}
		}

		VisionData newData = new VisionData(pitch, yaw, distance);
		recentData = newData;
		return newData;
	}

	/**
	 * Finds whether a target is detected.
	 * 
	 * @return true if a target is detected, otherwise false
	 */
	public boolean canSeeTarget() {
		return getData().getDistance() > 0.0;
	}

	/** The period between updates, in seconds */
	private final static double PERIOD = 0.2;

	/** The ip address and port number of the jetson */
	private final static String hostName = "10.13.6.95";
	private final static int portNumber = 5802;

}