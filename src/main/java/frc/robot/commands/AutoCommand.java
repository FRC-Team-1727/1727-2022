// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private final ShooterSubsystem m_shooterSubsystem;
  private final UptakeSubsystem m_uptakeSubsystem;

  /**
   * Creates a new AutoCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoCommand(DriveSubsystem drive, ShooterSubsystem shooter, UptakeSubsystem uptake) {
    m_driveSubsystem = drive;
    m_shooterSubsystem = shooter;
    m_uptakeSubsystem = uptake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive, shooter, uptake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooterSubsystem.setSpeed(2900);
    Timer.delay(5);
    m_uptakeSubsystem.move(1);
    Timer.delay(2);
    m_driveSubsystem.setDrive(-.25, -.25);
    Timer.delay(6);
    m_driveSubsystem.setDrive(0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
