package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.Hood;
import org.usfirst.frc.team1306.robot.subsystems.Shooter;
import org.usfirst.frc.team1306.robot.subsystems.Turret;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {

	protected static OI oi;
	protected static Drivetrain drivetrain;
	protected static Shooter shooter;
	protected static Turret turret;
	protected static Hood hood;
	protected static Vision vision;

	public static void init() {
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		turret = new Turret();
		hood = new Hood();
		vision = new Vision();
		oi = new OI();
	}

}
