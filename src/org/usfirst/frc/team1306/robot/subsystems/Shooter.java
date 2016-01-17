package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    CANTalon shooter;
    
    public Shooter() {
    	shooter = new CANTalon(RobotMap.shooterTalonPort);
    }

    public void initDefaultCommand() {
    }
    
    public void spinUp() {
    	shooter.set(1.0);
    }
    
    public void spinDown() {
    	shooter.set(0.0);
    }
    
    public boolean isSpunUp() {
    	return shooter.getEncVelocity() > 900;
    }
    
}

