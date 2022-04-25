package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ShooterSpeedCommand;
import frc.robot.commands.ToggleHoodCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import static frc.robot.Constants.ShooterConstants.*;

public class FirstShotAuto extends SequentialCommandGroup{
    public FirstShotAuto(ShooterSubsystem shooter, UptakeSubsystem uptake, boolean far) {
        double speed;
        if (far) {
            speed = kFarSpeed;
            addCommands(
                new WaitCommand(0.1),
                new ToggleHoodCommand(shooter)
            );
        }
        else speed = 11500;
        addCommands(
            new ShooterSpeedCommand(shooter, speed),
            new WaitCommand(2),
            new UptakeAutoCommand(uptake, .75),
            new WaitCommand(1),
            new UptakeAutoCommand(uptake, 0)
        );
    }
}
