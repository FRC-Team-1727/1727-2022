// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.AimConstants.kD;
import static frc.robot.Constants.AimConstants.kI;
import static frc.robot.Constants.AimConstants.kP;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/** An example command that uses an example subsystem. */
public class AimToggleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_subsystem;
  private boolean aiming;

  /*
   * Creates a new AimToggleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AimToggleCommand(DriveSubsystem subsystem, boolean aiming) {
    m_subsystem = subsystem;
    this.aiming = aiming;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(aiming) {
      m_subsystem.pid = new PIDController(kP, kI, kD);
      m_subsystem.pid.setSetpoint(0);
    } else {
      m_subsystem.pid.close();
    }
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
