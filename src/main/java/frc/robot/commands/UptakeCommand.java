// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.UptakeSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class UptakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final UptakeSubsystem m_subsystem;
  private final DoubleSupplier intakeSpeed;
  private final DoubleSupplier uptakeSpeed;
  private final DoubleSupplier outtakeSpeed;

  /**
   * Creates a new UptakeCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public UptakeCommand(UptakeSubsystem subsystem, DoubleSupplier intakeSpeed, DoubleSupplier uptakeSpeed, DoubleSupplier outtakeSpeed) {
    m_subsystem = subsystem;
    this.intakeSpeed = intakeSpeed;
    this.uptakeSpeed = uptakeSpeed;
    this.outtakeSpeed = outtakeSpeed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.index(intakeSpeed.getAsDouble(), uptakeSpeed.getAsDouble(), outtakeSpeed.getAsDouble());
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
