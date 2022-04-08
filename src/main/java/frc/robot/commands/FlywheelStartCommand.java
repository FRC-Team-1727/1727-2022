package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.auton.UptakeAutoCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;

public class FlywheelStartCommand extends SequentialCommandGroup {
    public FlywheelStartCommand(ShooterSubsystem shooter) {
        addCommands(
            new WaitCommand(.5),
            new ShooterStartupCommand(shooter)
        );
        addRequirements(shooter);
    }
}
