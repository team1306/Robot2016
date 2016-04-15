package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmVertical;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TerrainAuto extends CommandGroup {
    
    public  TerrainAuto() {
		addParallel(new IntakeArmVertical());
		addSequential(new DriveOverTerrain());
    }
}
