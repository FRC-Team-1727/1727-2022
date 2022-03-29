// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.UptakeSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class UptakeTestCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final UptakeSubsystem m_subsystem;
  private final DoubleSupplier speed;
  private final DoubleSupplier speedTwo;

  /**
   * Creates a new UptakeTestCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public UptakeTestCommand(UptakeSubsystem subsystem, DoubleSupplier speed, DoubleSupplier speedTwo) {
    m_subsystem = subsystem;
    this.speed = speed;
    this.speedTwo = speedTwo;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.grayWheelSetSpeed(speed.getAsDouble());
    m_subsystem.greenWheelSetSpeed(speedTwo.getAsDouble());
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
