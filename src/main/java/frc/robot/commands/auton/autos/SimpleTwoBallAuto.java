package frc.robot.commands.auton.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FlywheelStartCommand;
import frc.robot.commands.IntakePistonCommand;
import frc.robot.commands.ShooterSpeedCommand;
import frc.robot.commands.ShooterStartupCommand;
import frc.robot.commands.auton.AimAutoCommand;
import frc.robot.commands.auton.DriveDistanceCommand;
import frc.robot.commands.auton.IntakeAutoCommand;
import frc.robot.commands.auton.TurnCommand;
import frc.robot.commands.auton.UptakeAutoCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class SimpleTwoBallAuto extends SequentialCommandGroup {
    public SimpleTwoBallAuto(DriveSubsystem drive, ShooterSubsystem shooter, UptakeSubsystem uptake, IntakeSubsystem intake, VisionSubsystem vision) {
        addCommands(
            new WaitCommand(0.1),
            new IntakePistonCommand(intake),
            new WaitCommand(0.5),
            new IntakeAutoCommand(intake, 1),
            new DriveDistanceCommand(drive, 50, .25),
            new DriveDistanceCommand(drive, -0.25, .05),
            new WaitCommand(0.5),
            new IntakeAutoCommand(intake, 0),
            new TurnCommand(drive, -140, .25),
            new AimAutoCommand(drive, vision),
            new IntakePistonCommand(intake),
            new WaitCommand(1),
            new FlywheelStartCommand(shooter, uptake),
            new UptakeAutoCommand(uptake, 0.5),
            new WaitCommand(2),
            new UptakeAutoCommand(uptake, 0),
            new ShooterSpeedCommand(shooter, 0)
        );
    }
}
