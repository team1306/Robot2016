package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScanThenTarget extends CommandGroup {

	public ScanThenTarget() {
		addParallel(new SpinUp());
		addParallel(new RaiseHood());
		addSequential(new ScanForTarget());
		addSequential(new Target());
	}
}
