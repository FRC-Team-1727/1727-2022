// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.ClimbConstants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  private float position;

  private CANSparkMax[] motors = new CANSparkMax[]{
      new CANSparkMax(kClimbPort[0], MotorType.kBrushless),
      new CANSparkMax(kClimbPort[1], MotorType.kBrushless)
  };

  private SparkMaxPIDController[] controllers = new SparkMaxPIDController[]{
      motors[0].getPIDController(),
      motors[1].getPIDController()
  };

  //private RelativeEncoder encoder = motors[0].getEncoder();
  //private SparkMaxPIDController controller = motors[0].getPIDController();
  
  /** Creates a new ClimbSubsystem. */
  public ClimbSubsystem() {
    for (CANSparkMax m : motors) {
      m.setIdleMode(IdleMode.kBrake);
    }
    controllers[0].setFeedbackDevice(motors[0].getEncoder());
    controllers[1].setFeedbackDevice(motors[1].getEncoder());
    position = 0;
    updateConstants();
  }

  private void updateConstants() {
    for (SparkMaxPIDController controller : controllers) {
      controller.setOutputRange(0, 1);
      controller.setP(kP);
      controller.setI(kI);
      controller.setD(kD);
      controller.setFF(kF);
    }
  }

  public void testMotor(double speed) {
    motors[0].set(speed);
  }

  public void move(double speed) {
    for(CANSparkMax m : motors) {
      m.set(speed);
    }
  }

  public void climb(double speed) {
    position += speed * kClimbSpeed;
    if(position < 0) {
      position = 0;
    } else if(position > kClimbMax) {
      position = kClimbMax;
    }
    controllers[0].setReference(position, ControlType.kPosition);
    controllers[1].setReference(-position, ControlType.kPosition);
  }

  public void move(int id, double speed) {
    if(id == 0) {
      speed *= -1;
    }
    motors[id].set(speed * kClimbSpeed);
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
