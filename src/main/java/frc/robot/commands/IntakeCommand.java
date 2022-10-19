// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_subsystem;
  private DoubleSupplier spd;
  private BooleanSupplier outtake;
  private DoubleSupplier shoot;
  /**
   * Creates a new IntakeCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeCommand(IntakeSubsystem subsystem, DoubleSupplier spd, BooleanSupplier outtake, DoubleSupplier shoot) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

    this.spd = spd;
    this.outtake = outtake;
    this.shoot = shoot;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(outtake.getAsBoolean() || shoot.getAsDouble() > 0.1) m_subsystem.intake(-1);
    // else m_subsystem.intake(spd.getAsDouble());
    if(outtake.getAsBoolean()) m_subsystem.intake(-1);
    else if (shoot.getAsDouble() > 0.1) m_subsystem.intake(1);
    else m_subsystem.intake(spd.getAsDouble());
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
