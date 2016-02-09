package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.commands.turret.AutoTarget;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurretPID extends PIDSubsystem {

	private final CANTalon turretTalon;

	// Initialize your subsystem here
	public TurretPID() {
		super("Turret PID", Constants.TURRET_P, Constants.TURRET_I, Constants.TURRET_D);
		setAbsoluteTolerance(Constants.TURRET_TOLERANCE);

		turretTalon = new CANTalon(RobotMap.turretTalonPort);
		turretTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		turretTalon.changeControlMode(TalonControlMode.PercentVbus);
		turretTalon.set(turretTalon.get());
		turretTalon.enable();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new AutoTarget());
	}

	public void enable() {
		setSetpoint(0.0);
		super.enable();
	}

	public void disable() {
		super.disable();
	}

	public void setVel(double speed) {
		SmartDashboard.putNumber("PWM Position", turretTalon.getPulseWidthPosition());
		SmartDashboard.putNumber("PWM Velocity", turretTalon.getPulseWidthVelocity());
		SmartDashboard.putString("PWM Status",
				turretTalon.isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute).toString());
		turretTalon.set(speed * Constants.TURRET_MAX_SPEED);
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		double yaw = Vision.getData().getYaw();
		boolean inRange = Vision.canSeeTarget();
		if (inRange) {
			return yaw;
		} else {
			return getSetpoint();
		}
	}

	protected void usePIDOutput(double output) {
		turretTalon.set(-output);
	}
}
