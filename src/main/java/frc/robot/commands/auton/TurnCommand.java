// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.DriveConstants.*;

/** An example command that uses an example subsystem. */
public class TurnCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_subsystem;
  private double distance;
  private double speed;

  /**
   * Creates a new TurnCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnCommand(DriveSubsystem subsystem, double degrees, double speed) {
    m_subsystem = subsystem;
    distance = Math.toRadians(degrees) * kTurningRadius;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.resetEncoders();
    System.out.println("starting drive");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turnSpeed = (Math.abs(distance) - m_subsystem.getAbsoluteEncoderAverage()) * 0.02;
    if(turnSpeed > speed) {
      turnSpeed = speed;
    }
    turnSpeed *= distance/Math.abs(distance);
    m_subsystem.setDrive(-turnSpeed, turnSpeed);
    // System.out.println(m_subsystem.getEncoderAverage() + " " + distance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setDrive(0);
    System.out.println("done!");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_subsystem.getAbsoluteEncoderAverage()) > Math.abs(distance) - 1;
  }
}
