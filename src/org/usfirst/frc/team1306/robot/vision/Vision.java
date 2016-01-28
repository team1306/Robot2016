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

	/**
	 * Creates a new Vision object with no data.
	 */
	public Vision() {
		connectToJetson();
		recentData = null;
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
			double pitch = 0.0;
			double yaw = 0.0;
			double distance = 0.0;
			
			if (isConnected) {
				out.println("hello");
				out.flush();
				String data = null;
				try {
					data = in.readLine();
				} catch (IOException e) {
					
				}
				SmartDashboard.putString("data", data);
				String[] numbers = data.split(",");
				pitch = Integer.parseInt(numbers[0]);
				yaw = Integer.parseInt(numbers[1]);
				distance = Integer.parseInt(numbers[2]);
			} else {
				connectToJetson();
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		return getData().getDistance() > 0.0;
	}

	// TODO put these constants in Constants

	/** The ip address and port number of the jetson */
	private final static String hostName = "10.13.6.22";
	private final static int portNumber = 5802;

}