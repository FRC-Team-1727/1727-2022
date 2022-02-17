// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import static frc.robot.Constants.ClimbConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  private VictorSPX[] motors = new VictorSPX[]{
      new VictorSPX(kClimbPort[0]),
      new VictorSPX(kClimbPort[1])
  };
  
  /** Creates a new ClimbSubsystem. */
  public ClimbSubsystem() {}

  public void move(int speed) {
    motors[0].set(ControlMode.PercentOutput, speed*kClimbSpeed);
    motors[1].set(ControlMode.PercentOutput, -speed*kClimbSpeed);
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
