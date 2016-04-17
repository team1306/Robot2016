package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
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
public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand(DefenseType defenseType, boolean fire) {

		if (defenseType.equals(DefenseType.LOWBAR)) {
			addSequential(new IntakeArmRest());
		} else {
			addParallel(new IntakeArmVertical());
		}

		addSequential(defenseType.getDriveCommand());

		if (fire) {
			addParallel(new SpinUp());
			addSequential(new ScanForTarget());
			addParallel(new Target());
			addSequential(new Wait(Constants.TARGET_TIME));
			addSequential(new Fire());
		}

	}
}
