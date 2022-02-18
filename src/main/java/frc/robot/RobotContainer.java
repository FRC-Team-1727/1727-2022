// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.XboxConstants.*;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

/** CONTROLS 
 * Arcade Controls
 * Right Joystick (RS) - Forward/Back
 * Left Joystick (LS) - Left/Fight
 * Right Bumper (RB) - Intake Piston
 * Right Trigger (RT) - Intake
 * Left Bumper (LB) - Align To Limelight
 * Left Trigger (LT) - Shoot
 * D-Pad - Climb up & down (2 telescoping arms, powered by 2 motors)
 * - One button is up, one button is down (both move at the same time)
 * Buttons - Reverse intake, stop flywheel, etc etc.
 */

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final UptakeSubsystem m_uptakeSubsystem = new UptakeSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  XboxController xbox = new XboxController(kXboxPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    // default commands
    m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem,()->-xbox.getRightY(), ()->xbox.getLeftX())); // this is the old code, not sure why it doesn't work.
    m_intakeSubsystem.setDefaultCommand(new IntakeCommand(m_intakeSubsystem,()->xbox.getRightTriggerAxis()));
    m_uptakeSubsystem.setDefaultCommand(new UptakeCommand(m_uptakeSubsystem, ()->xbox.getLeftTriggerAxis()));
    m_climbSubsystem.setDefaultCommand(new ClimbCommand(m_climbSubsystem, xbox.getPOV()));
    m_shooterSubsystem.setDefaultCommand(new ShooterCommand(m_shooterSubsystem));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(xbox, Button.kRightBumper.value).whenPressed(new IntakePistonCommand(m_intakeSubsystem));

    //aiming
    new JoystickButton(xbox, Button.kLeftBumper.value).whenPressed(new AimToggleCommand(m_driveSubsystem, true));
    new JoystickButton(xbox, Button.kLeftBumper.value).whileHeld(new AimCommand(m_driveSubsystem, m_visionSubsystem, m_shooterSubsystem));
    new JoystickButton(xbox, Button.kLeftBumper.value).whenReleased(new AimToggleCommand(m_driveSubsystem, false));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}