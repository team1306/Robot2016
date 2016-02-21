package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmRest;
import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.ScanForTarget;
import org.usfirst.frc.team1306.robot.commands.turret.Target;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAimShoot extends CommandGroup {

	public DriveAimShoot() {
		addParallel(new SpinUp());
		addParallel(new IntakeArmRest());
		addSequential(new DriveThreeSeconds());
		addSequential(new ScanForTarget());
		addParallel(new Target());
		addSequential(new WaitFiveSeconds());
		addSequential(new Fire());
	}
}
