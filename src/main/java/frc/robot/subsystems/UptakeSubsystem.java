// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.UptakeConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UptakeSubsystem extends SubsystemBase {
  private VictorSPX[] motor = new VictorSPX[] {
    new VictorSPX(kUptakePort[0]),
    new VictorSPX(kUptakePort[1])
  };

  private DigitalInput beamBreak = new DigitalInput(kBeamBreakPort);

  private boolean preparing;
  private boolean shooting;
  private boolean triggerHeld;

  /** Creates a new UptakeSubsystem. */
  public UptakeSubsystem() {
    shooting = false;
    triggerHeld = false;
    preparing = false;
  }

  public void move(double speed) {
    for (VictorSPX m : motor) {
      // m.set(ControlMode.PercentOutput, speed * 0.22);
      m.set(ControlMode.PercentOutput, speed);
    }
  }

  public void index(double intake, double uptake, double outtake) {
    // if(outtake > 0.1) {
    //   move(-outtake);
    // } else if (uptake > 0.1) {
    //   move(uptake * 0.5);
    //   triggerHeld = true;
    //   shooting = true;
    //   preparing = false;
    // } else {
    //   triggerHeld = false;
    //   greenWheelSetSpeed(intake);
    //   if(beamBreak.get()) grayWheelSetSpeed(intake);
    //   else if (!preparing) grayWheelSetSpeed(0);
    // }

    if (outtake > 0.1) {
      move(-outtake);
    } else if (uptake > 0.1) {
      move(uptake);
    } else if (intake > 0.1) {
      grayWheelSetSpeed(-.2);
      greenWheelSetSpeed(intake);
    } else {
      move(0);
    }
  }

  public void grayWheelSetSpeed(double speed) {
    motor[0].set(ControlMode.PercentOutput, speed);
  }

  public void greenWheelSetSpeed(double speed) {
    motor[1].set(ControlMode.PercentOutput, speed);
  }

  public void setShooting(boolean value) {
    shooting = value;
  }

  public void setPreparing(boolean value) {
    preparing = value;
  }

  public boolean hasBeenReleased() {
    return shooting && !triggerHeld;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // System.out.println(beamBreak.get());
    SmartDashboard.putBoolean("beam break", beamBreak.get());
    // System.out.println(shooting + " " + triggerHeld + " " + preparing + " ");
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
