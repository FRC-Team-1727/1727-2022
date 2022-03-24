// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import frc.robot.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final DriveSubsystem m_subsystem;

  private final double left;
  private final double right;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SetDriveCommand(DriveSubsystem subsystem, double left, double right) {
    m_subsystem = subsystem;

    this.left = left;
    this.right = right;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

  }

  public SetDriveCommand(DriveSubsystem subsystem, double speed) {
    m_subsystem = subsystem;

    left = speed;
    right = speed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setDrive(left, right);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
