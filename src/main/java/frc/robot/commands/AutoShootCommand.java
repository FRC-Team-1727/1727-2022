// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.UptakeSubsystem;
import static frc.robot.Constants.ShooterConstants.*;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoShootCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ShooterSubsystem shooter;
  private final UptakeSubsystem uptake;
  private final DoubleSupplier uptakeSpeed;
  private final DoubleSupplier intakeSpeed;

  /**
   * Creates a new AutoShootCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoShootCommand(ShooterSubsystem shooter, UptakeSubsystem uptake, DoubleSupplier uptakeSpeed,
      DoubleSupplier intakeSpeed) {
    this.shooter = shooter;
    this.uptake = uptake;
    this.uptakeSpeed = uptakeSpeed;
    this.intakeSpeed = intakeSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, uptake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double uptakeSpeed = this.uptakeSpeed.getAsDouble();
    if (uptakeSpeed > 0.1) {
      shooter.setSpeed(kCloseSpeed);
    } else {
      shooter.stop();
    }

    if (intakeSpeed.getAsDouble() > 0.1) {
      uptake.grayWheelSetSpeed(-1);
      uptake.greenWheelSetSpeed(0);
    } else if (uptakeSpeed > 0.1 && shooter.atSpeed()) {
      uptake.move(1);
    } else {
      uptake.move(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
