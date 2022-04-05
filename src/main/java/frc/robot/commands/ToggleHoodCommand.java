// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.ShooterConstants.*;

/** An example command that uses an example subsystem. */
public class ToggleHoodCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_subsystem;
  private final UptakeSubsystem m_uptake;

  /**
   * Creates a new ToggleHoodCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ToggleHoodCommand(ShooterSubsystem subsystem, UptakeSubsystem uptake) {
    m_subsystem = subsystem;
    m_uptake = uptake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    addRequirements(uptake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_subsystem.getHoodValue() == Value.kReverse) {
      m_subsystem.setHood(false);
      // m_subsystem.setSpeed(kCloseSpeed);
      
    } else {
      m_subsystem.setHood(true);
      // m_subsystem.setSpeed(kFarSpeed);
      
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
