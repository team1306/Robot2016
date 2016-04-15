package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmRest;
import org.usfirst.frc.team1306.robot.commands.intake.IntakeArmVertical;
import org.usfirst.frc.team1306.robot.commands.shooter.Fire;
import org.usfirst.frc.team1306.robot.commands.shooter.SpinUp;
import org.usfirst.frc.team1306.robot.commands.turret.ScanForTarget;
import org.usfirst.frc.team1306.robot.commands.turret.Target;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TerrainAutoFire extends CommandGroup {
    
    public  TerrainAutoFire() {
		addParallel(new IntakeArmVertical());
		addSequential(new DriveOverTerrain());
		addParallel(new SpinUp());
		addSequential(new IntakeArmRest());
		addSequential(new ScanForTarget());
		addParallel(new Target());
		addSequential(new WaitFourSeconds());
		addSequential(new Fire());
    }
}
