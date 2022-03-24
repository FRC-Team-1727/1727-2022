// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import frc.robot.commands.ShooterSpeedCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class AutoCommand extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Creates a new AutoCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoCommand(DriveSubsystem drive, ShooterSubsystem shooter, UptakeSubsystem uptake) {
    addCommands(
        // new ShooterSpeedCommand(m_shooterSubsystem, 2900),
        // new WaitCommand(5),
        // // new UptakeAutoCommand(uptake, 0.5),
        // new WaitCommand(2),
        new DriveDistanceCommand(drive, 100, -25),
        // new WaitCommand(4),
        new ShooterSpeedCommand(shooter, 2900)
    );
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("starting auto");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
