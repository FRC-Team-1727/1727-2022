// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.UptakeConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UptakeSubsystem extends SubsystemBase {
  private VictorSPX motor = new VictorSPX(kUptakePort);
  
  /** Creates a new UptakeSubsystem. */
  public UptakeSubsystem() {}

  public void move(double speed) {
    motor.set(ControlMode.PercentOutput, speed);
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
