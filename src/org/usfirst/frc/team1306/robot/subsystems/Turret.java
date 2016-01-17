package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Turret extends PIDSubsystem {
	
	private final CANTalon turretTalon;
	
	private final Counter turretEncoder;

    // Initialize your subsystem here
    public Turret() {
    	super("turret", 0.1, 0.0, 0.0);
    	
    	turretTalon = new CANTalon(RobotMap.turretTalonPort);
    	
    	turretEncoder = new Counter(RobotMap.turretEncoderPort);
    	setSetpoint(turretEncoder.get());
    	enable();
    }
    
    public void initDefaultCommand() {
        //setDefaultCommand(new AutoTarget());
    	//setDefaultCommand(new ManualTarget());
    }
    
    public void turnCW() {
    	setSetpoint(turretEncoder.get() - 5);
    	turretEncoder.setReverseDirection(true);
    }
    
    public void turnCCW() {
    	setSetpoint(turretEncoder.get() + 5);
    	turretEncoder.setReverseDirection(false);
    }
    
    protected double returnPIDInput() {
    	return turretEncoder.pidGet();
    }
    
    protected void usePIDOutput(double output) {
        turretTalon.set(output);
    }
}
