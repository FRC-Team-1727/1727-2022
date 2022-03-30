package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;

public class DriveTime extends SequentialCommandGroup {
    public DriveTime(DriveSubsystem drive, double seconds, double speed) {
        addCommands(
            new SetDriveCommand(drive, speed, speed),
            new WaitCommand(seconds),
            new SetDriveCommand(drive, 0, 0)
        );
    }
}
