// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class IntakePistonTestCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_subsystem;
  private final int id;
  private final boolean forward;

  /**
   * Creates a new IntakePistonTestCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakePistonTestCommand(IntakeSubsystem subsystem, int id, boolean forward) {
    m_subsystem = subsystem;
    this.id = id;
    this.forward = forward;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Value value;
    if(forward) {
      value = Value.kForward;
    } else {
      value = Value.kReverse;
    }
    m_subsystem.setPiston(0, value);
    m_subsystem.setPiston(1, value);
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
