package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
    private final CANTalon leftIntakeMotor;
    private final CANTalon rightIntakeMotor;
    
    private final DoubleSolenoid leftIntakeSol;
    private final DoubleSolenoid rightIntakeSol;
    
    public Intake() {
    	leftIntakeMotor = new CANTalon(RobotMap.intakeMotor1Port);
    	rightIntakeMotor = new CANTalon(RobotMap.intakeMotor2Port);
    	
    	leftIntakeSol = new DoubleSolenoid(RobotMap.intakeSol1PortA, RobotMap.intakeSol1PortB);
    	rightIntakeSol = new DoubleSolenoid(RobotMap.intakeSol2PortA, RobotMap.intakeSol2PortB);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

