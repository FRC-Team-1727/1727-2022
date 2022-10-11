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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    // hoodPiston.set(kForward);
  }

  public void move() {
    if(curSpeed <= 0) {
      stop();
    } else {
      controller.setReference(curSpeed, ControlType.kVelocity);
    }
    // System.out.println(curSpeed);
  }

  public void setSpeed(double spd) {
    controller.setReference(spd, ControlType.kVelocity);
    curSpeed = spd;
    System.out.println(flywheel[0].getEncoder().getVelocity());
  }

  public void startup() {
    if (hoodPiston.get() == kReverse) {
      setSpeed(kFarSpeed);
    } else {
      setSpeed(kCloseSpeed);
    }
  }

  public void stop() {
    curSpeed = 0;
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

  public boolean atSpeed() {
    return Math.abs(curSpeed / kConversionFactor - flywheel[0].getEncoder().getVelocity()) <= kSpeedTolerance;
  }

  public void increment(double speed) {
    curSpeed += speed;
    controller.setReference(curSpeed, ControlType.kVelocity);
  }

  public void setHood(boolean value) {
    if(value) {
      hoodPiston.set(kReverse);
      if (curSpeed > 0) setSpeed(kFarSpeed);
      System.out.println("Hood is FARRRRRRRRRRRRRRRR");
      // System.out.println(curSpeed);
    } else {
      hoodPiston.set(kForward);
      if (curSpeed > 0) setSpeed(kCloseSpeed);
      System.out.println("Hood is CLOSE");
      // System.out.println(curSpeed);
    }
  }

  public Value getHoodValue() {
    return hoodPiston.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // if(flywheel[0].getEncoder().getVelocity() > 0) {
    //   System.out.println(flywheel[0].getEncoder().getVelocity() + " " + flywheel[1].getEncoder().getVelocity());
    // }

    SmartDashboard.putNumber("flywheel speed", flywheel[0].getEncoder().getVelocity());
    SmartDashboard.putNumber("flywheel target speed", curSpeed);
    SmartDashboard.putBoolean("hood", getHoodValue() == kReverse);

    if (getHoodValue() == kForward) SmartDashboard.putString("hoodValue", "close");
    else SmartDashboard.putString("hoodValue", "far");
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
