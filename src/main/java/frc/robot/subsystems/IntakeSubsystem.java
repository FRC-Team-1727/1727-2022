// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  
  private CANSparkMax intake = new CANSparkMax(kIntakePort, MotorType.kBrushless);
  private DoubleSolenoid[] pistons = new DoubleSolenoid[]{
    new DoubleSolenoid(PneumaticsModuleType.REVPH, kIntakePistonPort[0][0], kIntakePistonPort[0][1]),
    new DoubleSolenoid(PneumaticsModuleType.REVPH, kIntakePistonPort[1][0], kIntakePistonPort[1][1])
  };
  
  public IntakeSubsystem() {
    for(DoubleSolenoid p : pistons) {
      p.set(kForward);
    }
  }

  public void intake(double spd) {
    intake.set(spd);
  }

  public void togglePiston()
  {
    for(DoubleSolenoid p : pistons) {
      p.toggle();
    }
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
