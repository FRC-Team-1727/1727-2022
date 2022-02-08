// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public boolean hasTarget() {
    return table.getEntry("tv").getDouble(0) == 1;
  }

  public double getAngleX() {
    return table.getEntry("tx").getDouble(0);
  }

  public double getArea() {
    return table.getEntry("ta").getDouble(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
