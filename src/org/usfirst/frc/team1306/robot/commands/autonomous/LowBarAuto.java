package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmRest;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAuto extends CommandGroup {

	public LowBarAuto() {
		addSequential(new IntakeArmRest());
		addSequential(new DriveUnderLowBar());
	}
}
