package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Indexer extends Subsystem {
    
    private final SpeedController motor;
    private final DigitalInput ballSwitch1;
    private final DigitalInput ballSwitch2;
    
    public Indexer() {
    	motor = new Talon(RobotMap.indexerPort);
    	motor.setInverted(true);
    	ballSwitch1 = new DigitalInput(RobotMap.indexerLimitPort1);
    	ballSwitch2 = new DigitalInput(RobotMap.indexerLimitPort2);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveMotor() {
    	motor.set(Constants.INDEXER_POWER);
    }
    
    public void stop() {
    	motor.disable();
    }
    
    public void reverse() {
    	motor.set(-Constants.INDEXER_POWER);
    }
    
    public boolean hasBall() {
    	return !ballSwitch1.get() || ballSwitch2.get();
    }
}

