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
  private DoubleSupplier speed;
  private DoubleSupplier speedTwo;
  private int counter;
  /**
   * Creates a new UptakeCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public UptakeCommand(UptakeSubsystem subsystem, DoubleSupplier speed) {
    m_subsystem = subsystem;
    this.speed = speed;
    // this.speedTwo = speedTwo;
    this.counter = 0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //look over this: (if approved, delete this comment)
      if (speed.getAsDouble() < 0.1/* && speedTwo.getAsDouble() < 0.1*/) {
        m_subsystem.grayWheelSetSpeed(-0.30);
        m_subsystem.greenWheelSetSpeed(0);
      } else if (speed.getAsDouble() >= 0.1) {
        counter++;
        m_subsystem.greenWheelSetSpeed(speed.getAsDouble()*.5);
        if (counter < 20) m_subsystem.grayWheelSetSpeed(speed.getAsDouble()*0.75);
        else if (counter < 60) m_subsystem.grayWheelSetSpeed(0);
        else counter = 0;
      }/* else if (speedTwo.getAsDouble() >= 0.1) {
        //m_subsystem.move(-speedTwo.getAsDouble()*0.4);
      }*/ else counter = 0;
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (speed.getAsDouble() < 0.1f) {
      m_subsystem.grayWheelSetSpeed(-0.30);
      m_subsystem.greenWheelSetSpeed(0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
