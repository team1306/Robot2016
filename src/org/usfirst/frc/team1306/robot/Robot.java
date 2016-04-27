
package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.SmartDashboardUpdate;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand;
import org.usfirst.frc.team1306.robot.commands.autonomous.DefenseType;
import org.usfirst.frc.team1306.robot.vision.Vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation.
 * 
 * @author Finn Voichick, James Tautges
 */
public class Robot extends IterativeRobot {

	/** The command run during the autonomous period. */
	Command autonomousCommand;
	/** The command that always updates the SmartDashboard with information. */
	Command smartdashboard;
	/** How the drivers can choose the autonomous program before each match. */
	SendableChooser autoChooser;
	/** The CameraServer that sends camera images to the driver station. */
	CameraServer server;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// CommandBase.init() initializes all the subsystems and oi. This needs
		// to happen before anything else so that the other commands have things
		// to access
		CommandBase.init();

		server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");

		Vision.init();

		// Since we write some auto programs, we need to add them here
		autoChooser = new SendableChooser();
		autoChooser.addObject("Low Bar - Don't Shoot", new AutonomousCommand(DefenseType.LOWBAR, false, false));
		autoChooser.addObject("Rough Terrain - Don't Shoot", new AutonomousCommand(DefenseType.TERRAIN, false, false));
		autoChooser.addObject("Moat, Wall, or Ramparts - Don't Shoot",
				new AutonomousCommand(DefenseType.OBSTACLE, false, false));
		autoChooser.addDefault("Low Bar - Fire", new AutonomousCommand(DefenseType.LOWBAR, true, false));
		autoChooser.addObject("Rough Terrain - Fire", new AutonomousCommand(DefenseType.TERRAIN, true, false));
		autoChooser.addObject("Moat, Wall, or Ramparts - Fire",
				new AutonomousCommand(DefenseType.OBSTACLE, true, false));
		autoChooser.addDefault("Low Bar - Fire - Modified", new AutonomousCommand(DefenseType.LOWBAR, true, true));
		autoChooser.addObject("Rough Terrain - Fire - Modified",
				new AutonomousCommand(DefenseType.TERRAIN, true, true));
		autoChooser.addObject("Moat, Wall, or Ramparts - Fire - Modified",
				new AutonomousCommand(DefenseType.OBSTACLE, true, true));
		SmartDashboard.putData("Auto mode", autoChooser);

		// Start the debugging log command
		smartdashboard = new SmartDashboardUpdate();
		smartdashboard.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 */
	@Override
	public void disabledInit() {

	}

	/**
	 * This function is called periodically while the robot is disabled.
	 */
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		autonomousCommand = (Command) autoChooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called once each time the robot enters Teleop mode. It
	 * makes sure that the autonomous stops running when teleop starts running.
	 */
	@Override
	public void teleopInit() {

		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
