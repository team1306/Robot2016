package org.usfirst.frc.team1306.robot.subsystems.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.Range;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends Subsystem {

	private int session;

	public Vision() {

		int sessionNumber;
		try {

			sessionNumber = NIVision.IMAQdxOpenCamera(cameraName,
					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(sessionNumber);
			NIVision.IMAQdxStartAcquisition(sessionNumber);

			SmartDashboard.putNumber("Hue min", HUE_MIN);
			SmartDashboard.putNumber("Hue max", HUE_MAX);
			SmartDashboard.putNumber("Sat min", SAT_MIN);
			SmartDashboard.putNumber("Sat max", SAT_MAX);
			SmartDashboard.putNumber("Val min", VAL_MIN);
			SmartDashboard.putNumber("Val max", VAL_MAX);

		} catch (Exception e) {
			SmartDashboard.putString("Error", "Unable to connect to camera " + cameraName);
			sessionNumber = -1;
		}

		session = sessionNumber;
		distance = 0.0;
		pitch = 0.0;
		yaw = 0.0;

		lastUpdateTime = Timer.getFPGATimestamp();
	}

	private double distance;
	private double pitch;

	public double getPitch() {
		return pitch;
	}

	private double yaw;

	public double getYaw() {
		return yaw;
	}

	private double lastUpdateTime;

	public void update() {

		if (Timer.getFPGATimestamp() - lastUpdateTime < 0.2) {
			return;
		}

		lastUpdateTime = Timer.getFPGATimestamp();

		NIVision.Image frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		NIVision.Image binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);

		if (session < 0) {
			return;
		}

		NIVision.IMAQdxGrab(session, frame, 1);

		int hueMin = (int) SmartDashboard.getNumber("Hue min", HUE_MIN),
				hueMax = (int) SmartDashboard.getNumber("Hue max", HUE_MAX);
		Range satRange = new Range((int) SmartDashboard.getNumber("Sat min", SAT_MIN),
				(int) SmartDashboard.getNumber("Sat max", SAT_MAX));
		Range valRange = new Range((int) SmartDashboard.getNumber("Val min", VAL_MIN),
				(int) SmartDashboard.getNumber("Val max", VAL_MAX));
		
		if (hueMax < hueMin) {
			Range hueRangeLow = new Range(0, hueMax);
			Range hueRangeHigh = new Range(hueMin, 256);
			
			NIVision.imaqColorThreshold(binaryFrame, frame, 255, ColorMode.HSV, hueRangeLow, satRange, valRange);
			NIVision.imaqColorThreshold(binaryFrame, frame, 255, ColorMode.HSV, hueRangeHigh, satRange, valRange);
		} else {
			Range hueRange = new Range(hueMin, hueMax);
			
			NIVision.imaqColorThreshold(binaryFrame, frame, 255, ColorMode.HSV, hueRange, satRange, valRange);
		}

		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Masked particles", numParticles);
		CameraServer.getInstance().setImage(binaryFrame);

		int particleIndex = -1;
		double largestArea = 0.0;
		for (int i = 0; i < numParticles; i++) {
			double area = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_AREA);
			if (area > largestArea) {
				particleIndex = i;
				largestArea = area;
			}
		}

		if (particleIndex < 0) {
			return;
		}

		int x = (int) (NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
				MeasurementType.MT_BOUNDING_RECT_LEFT)
				+ NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, MeasurementType.MT_BOUNDING_RECT_RIGHT))
				/ 2;
		int y = (int) (NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, MeasurementType.MT_BOUNDING_RECT_TOP)
				+ NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, MeasurementType.MT_BOUNDING_RECT_BOTTOM))
				/ 2;

		pitch = calculatePitch(y);
		yaw = calculateYaw(x);
	}

	private static double calculatePitch(int y) {
		double yTarget = (RES_Y / 2.0 - y) / (RES_Y / 2.0);
		return yTarget * FOV_Y / 2.0;
	}

	private static double calculateYaw(int x) {
		double xTarget = (x - RES_X / 2.0) / (RES_X / 2.0);
		return xTarget * FOV_X / 2.0;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	private static final String cameraName = "cam0";

	private static final int RES_X = 320;
	private static final int RES_Y = 240;
	private static final double FOV_X = 60.0;
	private static final double FOV_Y = 45.0;

	private static final int HUE_MIN = 0;
	private static final int HUE_MAX = 256;
	private static final int SAT_MIN = 0;
	private static final int SAT_MAX = 256;
	private static final int VAL_MIN = 192;
	private static final int VAL_MAX = 256;
}
