// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import frc.robot.commands.IntakePistonCommand;
import frc.robot.commands.IntakePistonTestCommand;
import frc.robot.commands.ShooterSpeedCommand;
import frc.robot.commands.auton.UptakeAutoCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import static frc.robot.Constants.ShooterConstants.*;

/** An example command that uses an example subsystem. */
public class ComplexAutoCommand extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Creates a new ComplexAutoCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ComplexAutoCommand(DriveSubsystem drive, ShooterSubsystem shooter, UptakeSubsystem uptake, IntakeSubsystem intake) {
    addCommands(
      new ShooterSpeedCommand(shooter, kFarSpeed), //power up flywheel
      //shoot first ball
      new WaitCommand(2),
      new UptakeAutoCommand(uptake, .75),
      new WaitCommand(1),
      new UptakeAutoCommand(uptake, 0),
      //turn and intake second ball
      new TurnCommand(drive, 145, .25),
      new IntakePistonCommand(intake),
      new IntakeAutoCommand(intake, 1),
      new WaitCommand(0.5),
      new DriveDistanceCommand(drive, 20, .25),
      new WaitCommand(1),
      // //back up and turn towards hub
      new DriveDistanceCommand(drive, -40, .25),
      new IntakeAutoCommand(intake, 0),
      new TurnCommand(drive, -160, .25),
      new IntakePistonCommand(intake),
      new WaitCommand(0.5),
      // //shoot second ball
      new UptakeAutoCommand(uptake, .75),
      new WaitCommand(2),
      new UptakeAutoCommand(uptake, 0),
      //taxi
      new SetDriveCommand(drive, -0.25, -0.25),
      new WaitCommand(3),
      new SetDriveCommand(drive, 0, 0)
    );
    addRequirements(uptake, intake);
  }

  // Called when the command is initially scheduled.
  // @Override
  // public void initialize() {
  //   System.out.println("starting auto");
  // }

  // Called every time the scheduler runs while the command is scheduled.
  // @Override
  // public void execute() {
    
  // }

  // Called once the command ends or is interrupted.
  // @Override
  // public void end(boolean interrupted) {
  // }

  // // Returns true when the command should end.
  // @Override
  // public boolean isFinished() {
  //   return false;
  // }
}
