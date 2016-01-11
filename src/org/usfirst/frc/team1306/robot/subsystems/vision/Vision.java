package org.usfirst.frc.team1306.robot.subsystems.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends Subsystem {

	private final int session;
	private final Image frame;
	private final Image binaryFrame;
	private HSVThreshold threshold;

	public Vision() {

		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);

		session = NIVision.IMAQdxOpenCamera(cameraName, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);

		SmartDashboard.putNumber("Hue min", HUE_MIN);
		SmartDashboard.putNumber("Hue max", HUE_MAX);
		SmartDashboard.putNumber("Sat min", SAT_MIN);
		SmartDashboard.putNumber("Sat max", SAT_MAX);
		SmartDashboard.putNumber("Val min", VAL_MIN);
		SmartDashboard.putNumber("Val max", VAL_MAX);
		threshold = new HSVThreshold(HUE_MIN, HUE_MAX, SAT_MIN, SAT_MAX, VAL_MIN, VAL_MAX);

	}

	private double distance;
	private double angle;

	public void update() {
		NIVision.IMAQdxGrab(session, frame, 1);
		threshold = new HSVThreshold((int) SmartDashboard.getNumber("Hue min", HUE_MIN),
				(int) SmartDashboard.getNumber("Hue max", HUE_MAX), (int) SmartDashboard.getNumber("Sat min", SAT_MIN),
				(int) SmartDashboard.getNumber("Sat max", SAT_MAX), (int) SmartDashboard.getNumber("Val min", VAL_MIN),
				(int) SmartDashboard.getNumber("Val max", VAL_MAX));

		NIVision.imaqColorThreshold(binaryFrame, frame, 255, ColorMode.HSV, threshold.hueRange(), threshold.satRange(),
				threshold.valRange());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	private static final String cameraName = "cam0";
	private static final int HUE_MIN = 0;
	private static final int HUE_MAX = 255;
	private static final int SAT_MIN = 127;
	private static final int SAT_MAX = 255;
	private static final int VAL_MIN = 127;
	private static final int VAL_MAX = 255;
}
