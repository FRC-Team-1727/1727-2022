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
import frc.robot.commands.auton.AutoCommand;
import frc.robot.commands.auton.ComplexAutoCommand;

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
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final UptakeSubsystem m_uptakeSubsystem = new UptakeSubsystem();
  // private final CompressorSubsystem m_compressorSubsystem = new CompressorSubsystem();

  private final Command m_autoCommand = new ComplexAutoCommand(m_driveSubsystem, m_shooterSubsystem, m_uptakeSubsystem, m_intakeSubsystem);

  XboxController xbox = new XboxController(kXboxPort[0]);
  // XboxController xboxTwo = new XboxController(kXboxPort[1]);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // // Configure the button bindings
    // configureButtonBindings();
    // // default commands
    // m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem,()->xbox.getLeftX(), ()->-xbox.getRightY()));
    // m_intakeSubsystem.setDefaultCommand(new IntakeCommand(m_intakeSubsystem,()->xbox.getRightTriggerAxis()));
    // m_uptakeSubsystem.setDefaultCommand(new UptakeCommand(m_uptakeSubsystem, ()->xbox.getLeftTriggerAxis()));
    // // m_climbSubsystem.setDefaultCommand(new ClimbCommand(m_climbSubsystem, xbox.getPOV()));
    // m_shooterSubsystem.setDefaultCommand(new ShooterCommand(m_shooterSubsystem));
    // //m_climbSubsystem.setDefaultCommand(new ClimbMoveCommand(m_climbSubsystem, () -> xboxTwo.getLeftY(), () -> xboxTwo.getRightY()));
    // // m_climbSubsystem.setDefaultCommand(new ClimbMoveCommand(m_climbSubsystem, ()->xbox.getLeftTriggerAxis()));
    // // m_compressorSubsystem.setDefaultCommand(new CompressorCommand(m_compressorSubsystem));

    m_uptakeSubsystem.setDefaultCommand(new UptakeTestCommand(m_uptakeSubsystem, ()->xbox.getLeftTriggerAxis(), ()->xbox.getRightTriggerAxis()));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(xbox, Button.kA.value).whileHeld(new UptakeMoveCommand(m_uptakeSubsystem, 1));

    // new JoystickButton(xbox, Button.kRightBumper.value).whenPressed(new IntakePistonCommand(m_intakeSubsystem));
    // //new JoystickButton(xbox, Button.kB.value).whenHeld(new OuttakeCommand(m_intakeSubsystem));

    // //aiming
    // // new JoystickButton(xbox, Button.kLeftBumper.value).whenPressed(new AimToggleCommand(m_driveSubsystem, true));
    // new JoystickButton(xbox, Button.kLeftBumper.value).whileHeld(new AimCommand(m_driveSubsystem, m_visionSubsystem, m_shooterSubsystem));
    // // new JoystickButton(xbox, Button.kLeftBumper.value).whenReleased(new AimToggleCommand(m_driveSubsystem, false));

    // //flywheel manual control 
    // new JoystickButton(xbox, Button.kStart.value).whenPressed(new ToggleHoodCommand(m_shooterSubsystem, m_uptakeSubsystem));
    // // new JoystickButton(xboxTwo, Button.kStart.value).whenPressed(new ToggleHoodCommand(m_shooterSubsystem, m_uptakeSubsystem));
    // new JoystickButton(xbox, Button.kBack.value).whenPressed(new ShooterSpeedCommand(m_shooterSubsystem, 0));
    // // new JoystickButton(xbox, Button.kB.value).whenPressed(new ShooterIncrementCommand(m_shooterSubsystem, 25));
    // // new JoystickButton(xbox, Button.kA.value).whenPressed(new ShooterIncrementCommand(m_shooterSubsystem, -25));

    // //climb
    // // new JoystickButton(xbox, Button.kX.value).whenPressed(new ClimbIndividualCommand(m_climbSubsystem, 0, 1));
    // // new JoystickButton(xbox, Button.kA.value).whenPressed(new ClimbIndividualCommand(m_climbSubsystem, 0, 0));
    // // new JoystickButton(xbox, Button.kY.value).whenPressed(new ClimbIndividualCommand(m_climbSubsystem, 1, 1));
    // // new JoystickButton(xbox, Button.kB.value).whenPressed(new ClimbIndividualCommand(m_climbSubsystem, 1, 0));
    // new JoystickButton(xbox, Button.kY.value).whenHeld(new ClimbMoveCommand(m_climbSubsystem, 1));
    // new JoystickButton(xbox, Button.kB.value).whenHeld(new ClimbMoveCommand(m_climbSubsystem, -1));

    // //new JoystickButton(xbox, Button.kA.value).whenHeld(new ClimbIndividualCommand(m_climbSubsystem, 0, 1));
    // //new JoystickButton(xbox, Button.kX.value).whenHeld(new ClimbIndividualCommand(m_climbSubsystem, 0, -1));
    // //new JoystickButton(xbox, Button.kY.value).whenHeld(new ClimbIndividualCommand(m_climbSubsystem, 1, -1));
    // //new JoystickButton(xbox, Button.kB.value).whenHeld(new ClimbIndividualCommand(m_climbSubsystem, 1, 1));
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
