// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton.autos;

import frc.robot.commands.ShooterSpeedCommand;
import frc.robot.commands.auton.DriveDistanceCommand;
import frc.robot.commands.auton.UptakeAutoCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static frc.robot.Constants.ShooterConstants.*;

public class FenderAuto extends SequentialCommandGroup {
  public FenderAuto(DriveSubsystem drive, ShooterSubsystem shooter, UptakeSubsystem uptake) {
    addRequirements(drive, shooter, uptake);
    addCommands(
      new ShooterSpeedCommand(shooter, kCloseSpeed),
      new WaitCommand(2),
      new UptakeAutoCommand(uptake, 1),
      new WaitCommand(3),
      new UptakeAutoCommand(uptake, 0),
      new ShooterSpeedCommand(shooter, 0),
      new DriveDistanceCommand(drive, -100, 0.25)
    );
  }
}
