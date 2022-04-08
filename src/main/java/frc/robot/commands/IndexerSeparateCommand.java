package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.auton.UptakeAutoCommand;
import frc.robot.subsystems.UptakeSubsystem;

public class IndexerSeparateCommand extends SequentialCommandGroup {
  public IndexerSeparateCommand(UptakeSubsystem uptake) {
    addCommands(
      new UptakeAutoCommand(uptake, -1),
      new WaitCommand(0.5),
      new PrepareIndexerCommand(uptake)
    );
    addRequirements(uptake);
  }
}