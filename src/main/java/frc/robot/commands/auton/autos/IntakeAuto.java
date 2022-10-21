// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton.autos;

import frc.robot.commands.IntakePistonCommand;
import frc.robot.commands.auton.DriveDistanceCommand;
import frc.robot.commands.auton.IntakeAutoCommand;
import frc.robot.commands.auton.TurnCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class IntakeAuto extends SequentialCommandGroup {
  public IntakeAuto(DriveSubsystem drive, ShooterSubsystem shooter, IntakeSubsystem intake) {
    addRequirements(drive, shooter, intake);
    addCommands(
      new IntakePistonCommand(intake),
      new IntakeAutoCommand(intake, 1),
      new DriveDistanceCommand(drive, 40, 0.25),
      new WaitCommand(2),
      new IntakePistonCommand(intake),
      new TurnCommand(drive, 180, 0.25)
    );
  }
}
