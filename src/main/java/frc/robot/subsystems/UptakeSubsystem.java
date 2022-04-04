// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.UptakeConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UptakeSubsystem extends SubsystemBase {
  private VictorSPX[] motor = new VictorSPX[] {
    new VictorSPX(kUptakePort[0]),
    new VictorSPX(kUptakePort[1])
  };

  private DigitalInput beamBreak = new DigitalInput(kBeamBreakPort);
  
  /** Creates a new UptakeSubsystem. */
  public UptakeSubsystem() {}

  public void move(double speed) {
    for (VictorSPX m : motor) {
      // m.set(ControlMode.PercentOutput, speed * 0.22);
      m.set(ControlMode.PercentOutput, speed);
    }
  }

  public void index(double intake, double uptake) {
    if (uptake > 0.1) {
      move(uptake);
    } else {
      greenWheelSetSpeed(intake);
      if(!beamBreak.get()) grayWheelSetSpeed(intake);
      else grayWheelSetSpeed(0);
    }
  }

  public void grayWheelSetSpeed(double speed) {
    motor[0].set(ControlMode.PercentOutput, speed);
  }

  public void greenWheelSetSpeed(double speed) {
    motor[1].set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(!beamBreak.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
