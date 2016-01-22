package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    private CANTalon flywheel;
    
    private double target = 0;
    
    public Shooter() {
    	flywheel = new CANTalon(RobotMap.flyWheelTalonPort);
    	
    	flywheel.reverseOutput(true);
    	flywheel.reverseSensor(true);
    	
    	flywheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	flywheel.changeControlMode(TalonControlMode.Speed);
    	flywheel.enable();
    }

    public void initDefaultCommand() {
    }
    
    public void spinTo(double speed) {
    	flywheel.set(speed);
    	target = speed;
    }
    
    public void spinUp() {
    	spinTo(1.0);
    }
    
    public void spinDown() {
    	spinTo(0.0);
    }
    
    public boolean isSpunUp() {
    	return Math.abs(flywheel.getEncVelocity() - target) < Constants.HOOD_TOLERANCE;
    }
    
}

