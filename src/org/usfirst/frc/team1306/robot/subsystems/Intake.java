package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
    private final CANTalon leftIntakeMotor;
    private final CANTalon rightIntakeMotor;
    
    private final Talon roller1;
    private final Talon roller2;
    
    private final DoubleSolenoid leftIntakeSol;
    private final DoubleSolenoid rightIntakeSol;
    
    public Intake() {
    	leftIntakeMotor = new CANTalon(RobotMap.intakeMotor1Port);
    	rightIntakeMotor = new CANTalon(RobotMap.intakeMotor2Port);
    	
    	roller1 = new Talon(RobotMap.intakeRoller1Port);
    	roller2 = new Talon(RobotMap.intakeRoller2Port);
    	
    	leftIntakeSol = new DoubleSolenoid(RobotMap.intakeSol1PortA, RobotMap.intakeSol1PortB);
    	rightIntakeSol = new DoubleSolenoid(RobotMap.intakeSol2PortA, RobotMap.intakeSol2PortB);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Set the rollers spinning
     */
    
    public void startRollers() {
    	roller1.set(1.0);
    	roller2.set(1.0);
    }
    
    /**
     * Stop the rollers spinning
     */
    
    public void stopRollers() {
    	roller1.set(0.0);
    	roller2.set(0.0);
    }
    
    /**
     * Actuate the solenoids to extend the tusks
     */
    
    public void extendTusks() {
    	leftIntakeSol.set(DoubleSolenoid.Value.kForward);
    	rightIntakeSol.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Actuate the solenoids to retract the tusks
     */
    
    public void retractTusks() {
    	leftIntakeSol.set(DoubleSolenoid.Value.kReverse);
    	rightIntakeSol.set(DoubleSolenoid.Value.kReverse);
    }
}

