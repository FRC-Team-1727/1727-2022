package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.auton.UptakeAutoCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;

public class FlywheelStartCommand extends SequentialCommandGroup {
    public FlywheelStartCommand(ShooterSubsystem shooter, UptakeSubsystem uptake) {
        addCommands(
            new UptakeAutoCommand(uptake, -0.5),
            new WaitCommand(.5),
            new UptakeAutoCommand(uptake, 0),
            new ShooterStartupCommand(shooter),
            new WaitCommand(0.5)
        );
        addRequirements(shooter, uptake);
    }
}
