package frc.robot.commands.auton.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakePistonCommand;
import frc.robot.commands.auton.DriveDistanceCommand;
import frc.robot.commands.auton.DriveTime;
import frc.robot.commands.auton.FirstShotAuto;
import frc.robot.commands.auton.IntakeAutoCommand;
import frc.robot.commands.auton.TurnCommand;
import frc.robot.commands.auton.UptakeAutoCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;

public class ThreeBallRightAuto extends SequentialCommandGroup {
    public ThreeBallRightAuto(DriveSubsystem drive, ShooterSubsystem shooter, UptakeSubsystem uptake, IntakeSubsystem intake) {
        addCommands(
            new FirstShotAuto(shooter, uptake, true),
            new TurnCommand(drive, 140, .25),
            new IntakePistonCommand(intake),
            new IntakeAutoCommand(intake, 1),
            new DriveDistanceCommand(drive, 30, .25),
            new WaitCommand(1),
            //back up first?
            new TurnCommand(drive, 110, .25),
            new DriveDistanceCommand(drive, 50, .25),
            new TurnCommand(drive, 135, .25),
            new DriveDistanceCommand(drive, 30, .25),
            new TurnCommand(drive, -45, .25),
            //aim?
            new UptakeAutoCommand(uptake, .75),
            new WaitCommand(1.5),
            new UptakeAutoCommand(uptake, 0),
            new DriveTime(drive, 3, -.25)
        );
    }
}