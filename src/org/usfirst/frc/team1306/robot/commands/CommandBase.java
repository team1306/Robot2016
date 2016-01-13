package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {

	protected static OI oi;
	protected static Drivetrain drivetrain;
	protected static Vision vision;

	public static void init() {
		oi = new OI();
		drivetrain = new Drivetrain();
		vision = new Vision();
	}

}
