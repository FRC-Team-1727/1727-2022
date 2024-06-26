// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.ShooterConstants.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  
  private CANSparkMax[] flywheel = new CANSparkMax[] {
    new CANSparkMax(kFlywheelPort[0], MotorType.kBrushless),
    new CANSparkMax(kFlywheelPort[1], MotorType.kBrushless)
  };

  private RelativeEncoder encoder = flywheel[0].getEncoder();
  private SparkMaxPIDController controller = flywheel[0].getPIDController();

  private DoubleSolenoid hoodPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, kHoodPistonPort[0], kHoodPistonPort[1]);

  private double curSpeed;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    flywheel[1].follow(flywheel[0], true);
    flywheel[0].set(0);
    for(CANSparkMax m : flywheel) {
      m.setIdleMode(IdleMode.kCoast);
    }

    controller.setFeedbackDevice(encoder);
    stop();
    updateConstants();
    curSpeed = kDefaultSpeed;
    hoodPiston.set(kReverse);
  }

  public void move() {
    controller.setReference(curSpeed, ControlType.kVelocity);
  }

  public void setSpeed(double spd) {
    controller.setReference(spd, ControlType.kVelocity);
    curSpeed = spd;
  }

  public void stop() {
    controller.setReference(0, ControlType.kDutyCycle);
  }

  private void updateConstants() {
    controller.setOutputRange(0, 1);
    controller.setP(kP);
    controller.setI(kI);
    controller.setD(kD);
    controller.setFF(kF);
  }

  public void aim(double distance) {
    //set hood
    if (distance > kHoodChangeThreshold) {
      hoodPiston.set(kForward);
    } else {
      hoodPiston.set(kReverse);
    }

    //set flywheel speed
    //do math based on distance
    // controller.setReference(0, ControlType.kVelocity);
  }

  public void increment(double speed) {
    curSpeed += speed;
    controller.setReference(curSpeed, ControlType.kVelocity);
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
