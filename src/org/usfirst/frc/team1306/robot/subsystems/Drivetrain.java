package org.usfirst.frc.team1306.robot.subsystems;

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

	private final RobotDrive drivetrain;

	public Drivetrain() {

		leftMotor1 = new CANTalon(RobotMap.leftTalon1Port);
		leftMotor2 = new CANTalon(RobotMap.leftTalon2Port);
		rightMotor1 = new CANTalon(RobotMap.rightTalon1Port);
		rightMotor2 = new CANTalon(RobotMap.rightTalon2Port);
		
		motors = new CANTalon[]{leftMotor1, leftMotor2, rightMotor1, rightMotor2};
		setupMotors(leftMotor1, leftMotor2);
		setupMotors(rightMotor1, rightMotor2);
		
		leftMotor1.reverseOutput(true);
		
		drivetrain = new RobotDrive(leftMotor1, rightMotor1);
		
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
		drivetrain.stopMotor();
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

}
