package org.usfirst.frc.team1306.robot.subsystems;

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
    }
    
    public void spinUp() {
    	flywheel.set(1.0);
    }
    
    public void spinDown() {
    	flywheel.set(0.0);
    }
    
    public boolean isSpunUp() {
    	return flywheel.getEncVelocity() > 900;
    }
    
}

