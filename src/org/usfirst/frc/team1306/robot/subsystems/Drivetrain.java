package org.usfirst.frc.team1306.robot.subsystems;

import java.util.List;
import java.util.Queue;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.DriveTank;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

	
	private final CANTalon[] motors;
	private final CANTalon leftMotor1;
	private final CANTalon leftMotor2;
	private final CANTalon rightMotor1;
	private final CANTalon rightMotor2;
	
	private double initPos[] = {0, 0, 0, 0};

	public Drivetrain() {

		leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
		leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
		rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
		rightMotor2 = new CANTalon(RobotMap.rightTalon2Port);
		
		motors = new CANTalon[]{leftMotor1, leftMotor2, rightMotor1, rightMotor2};
		setupMotors(leftMotor1, leftMotor2);
		setupMotors(rightMotor1, rightMotor2);
		
		rightMotor1.reverseOutput(true);
		
		SmartDashboard.putNumber("maxSpeed", Constants.MAX_SPEED);
				
	}

	/**
	 * Takes two values from -1.0 to 1.0 for the right and left motors
	 * 
	 * @param leftVel	Speed of left motor
	 * @param rightVel	Speed of right motor
	 */
	
	public void driveTank(double leftVel, double rightVel) {
		double maxSpeed = SmartDashboard.getNumber("maxSpeed");
		leftVel = leftVel * maxSpeed;
		rightVel = rightVel * maxSpeed;
		
		leftMotor1.set(leftVel);
		rightMotor1.set(rightVel);
	}

	/**
	 * Takes values from -1.0 to 1.0 for velocity and rotation
	 * 
	 * @param velocity	Base speed forward
	 * @param rotation	Additional rotational rate
	 */
	
	public void driveHybrid(double velocity, double rotation) {
		double maxSpeed = SmartDashboard.getNumber("maxSpeed");
		double leftVel = maxSpeed * (velocity + rotation);
		double rightVel = maxSpeed * (velocity - rotation);
		
		leftMotor1.set(leftVel);
		rightMotor1.set(rightVel);
	}

	public void stop() {
		leftMotor1.set(0.0);
		rightMotor1.set(0.0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveTank());
	}

	private void setupMotors(CANTalon master, CANTalon slave) {
		master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		master.changeControlMode(TalonControlMode.Speed);
		master.reverseSensor(true);
		master.set(0.0);
		master.enable();
		
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(master.getDeviceID());
	}
	
	public void setTrajectory(int motor, List<Double> velocities, List<Double> positions, int steps) {
		for (int i = 0; i < steps; i++) {
			SmartDashboard.putNumber("i", i);
			CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
			point.velocity = velocities.get(i);
			point.position = positions.get(i);
			point.timeDurMs = Constants.PROFILE_STEP;
			point.profileSlotSelect = 0;
			point.isLastPoint = (i == steps - 1);
			point.zeroPos = (i == 0);
			point.velocityOnly = false;
			motors[motor].pushMotionProfileTrajectory(point);
		}
	}
	
	public void runProfile() {
		if (leftMotor1.getMotionProfileTopLevelBufferCount() > 0) {
			leftMotor1.processMotionProfileBuffer();
		}
		if (rightMotor1.getMotionProfileTopLevelBufferCount() > 0) {
			rightMotor1.processMotionProfileBuffer();
		}
	}
	
	// These are mainly getters for the smartdashboard command
	
	public double get(int motor) {
		return motors[motor].get();
	}
	
	public double getError(int motor) {
		return motors[motor].getError();
	}
	
	public double getEncVelocity(int motor) {
		return motors[motor].getEncVelocity();
	}
	
	public double getPosition(int motor) {
		return -1*(motors[motor].getPosition() - initPos[motor]);
	}
	
	public void zero() {
		for (int i = 0; i < 4; i++) {
			initPos[i] = motors[i].getPosition();
		}
	}
	
	/** All of these are placeholder values. */
	private static double MAX_SPEED = 850.0;
	/*private static double P = 1.0;
	private static double I = 0.0;
	private static double D = 0.0;
	private static double F = 0.0;
	private static int IZONE = 0;
	private static double RAMP_RATE = 2.0;*/

}
