package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;

public class FlywheelStartCommand extends SequentialCommandGroup {
    public FlywheelStartCommand(ShooterSubsystem shooter, UptakeSubsystem uptake) {
        addCommands(
            new IndexerSeparateCommand(uptake, -1),
            new WaitCommand(.5),
            new IndexerSeparateCommand(uptake, 0),
            new ShooterStartupCommand(shooter)
        );
        addRequirements(shooter, uptake);
    }
}
