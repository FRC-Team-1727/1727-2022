// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.AimConstants.*;

/** An example command that uses an example subsystem. */
public class AimCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final DriveSubsystem m_driveSubsystem;
  private final VisionSubsystem m_visionSubsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AimCommand(DriveSubsystem dsubsystem, VisionSubsystem vsubsystem) {
    m_driveSubsystem = dsubsystem;
    m_visionSubsystem = vsubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dsubsystem, vsubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_visionSubsystem.hasTarget()) {
      m_driveSubsystem.aim(m_visionSubsystem.getAngleX());
    } else {
      m_driveSubsystem.aim(10);
    }
    // m_shooterSubsystem.aim(kVisionHeight / Math.tan(kVisionAngle + m_visionSubsystem.getAngleY()) + kHubRadius);
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
