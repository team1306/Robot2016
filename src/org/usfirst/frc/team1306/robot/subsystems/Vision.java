package org.usfirst.frc.team1306.robot.subsystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.usfirst.frc.team1306.robot.commands.VisionCommand;
import org.usfirst.frc.team1306.robot.vision.VisionData;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {
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
	
	public Vision() {
		recentData = null;

		try {
			jetson = new Socket(hostName, portNumber);
			out = new PrintWriter(jetson.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(jetson.getInputStream()));
			isConnected = true;

		} catch (Exception e) {
			SmartDashboard.putString("error", e.getMessage());
		}
	}

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new VisionCommand());
    }
    
	/**
	 * Returns the most recent data if it is recent enough, otherwise gets new
	 * data from the Jetson and returns that.
	 * 
	 * @return recent data from the Jetson.
	 */
	public String getData() {
		/*if (recentData != null && Timer.getFPGATimestamp() - recentData.getTimestamp() < PERIOD) {
			return recentData;
		}*/

		double pitch = 0.0;
		double yaw = 0.0;
		double distance = 0.0;

		String data = "";
		if (isConnected) {
			out.print('a');
			data = null;
			try {
				data = in.readLine();
			} catch (IOException e) {
				
			}
			SmartDashboard.putString("data", data);
			String[] numbers = data.split(",");
			pitch = Integer.parseInt(numbers[0]);
			yaw = Integer.parseInt(numbers[1]);
			distance = Integer.parseInt(numbers[2]);
		}

		VisionData newData = new VisionData(pitch, yaw, distance);
		recentData = newData;
		return data;
	}
    
	/** The period between updates, in seconds */
	private final static double PERIOD = 0.2;

	/** The ip address and port number of the jetson */
	private final static String hostName = "http://10.13.6.22";
	private final static int portNumber = 5802;
}

