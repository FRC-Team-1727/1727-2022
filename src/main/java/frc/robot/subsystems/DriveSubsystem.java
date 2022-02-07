// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.DriveConstants.*;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;

public class DriveSubsystem extends SubsystemBase {

    private CANSparkMax[] lDrive = new CANSparkMax[]{
            new CANSparkMax(kLDrivePort[0], MotorType.kBrushless),
            new CANSparkMax(kLDrivePort[1], MotorType.kBrushless)
    };

    private CANSparkMax[] rDrive = new CANSparkMax[]{
            new CANSparkMax(kRDrivePort[0], MotorType.kBrushless),
            new CANSparkMax(kRDrivePort[1], MotorType.kBrushless)
    };

    private Encoder lEncoder = new Encoder(kLEncoderPort[0], kLEncoderPort[1], kLEncoderReverse, CounterBase.EncodingType.k4X);
    private Encoder rEncoder = new Encoder(kREncoderPort[0], kREncoderPort[1], kREncoderReverse, CounterBase.EncodingType.k4X);


    public DriveSubsystem() {
        lEncoder.setDistancePerPulse(kWheelDiameter * Math.PI / 256);
        rEncoder.setDistancePerPulse(kWheelDiameter * Math.PI / 256);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    public void arcade(double x, double y) {
        if (Math.abs(x) < kThreshold) {
            x = 0;
        }
        if (Math.abs(y) < kThreshold) {
            y = 0;
        }

        setDrive(y + x, y - x);
    }


    //MOVEMENT FUNCTIONS

    private void setLDrive(double spd) {
        //move lDrive
        for (CANSparkMax m : lDrive) {
            m.set(spd);
        }
    }

    private void setRDrive(double spd) {
        //move rDrive
        for (CANSparkMax m : rDrive) {
            m.set(-spd);
        }
    }

    private void setDrive(double left, double right) {
        setLDrive(left);
        setRDrive(right);
    }
}
