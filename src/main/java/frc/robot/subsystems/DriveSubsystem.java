// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.DriveConstants.*;
import static frc.robot.Constants.AimConstants.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

    public PIDController pid;

    private CANSparkMax[] lDrive = new CANSparkMax[]{
            new CANSparkMax(kLDrivePort[0], MotorType.kBrushless),
            new CANSparkMax(kLDrivePort[1], MotorType.kBrushless)
    };

    private CANSparkMax[] rDrive = new CANSparkMax[]{
            new CANSparkMax(kRDrivePort[0], MotorType.kBrushless),
            new CANSparkMax(kRDrivePort[1], MotorType.kBrushless)
    };

    public DriveSubsystem() {
        // lEncoder.setDistancePerPulse(kWheelDiameter * Math.PI / 256);
        // rEncoder.setDistancePerPulse(kWheelDiameter * Math.PI / 256);
        for(CANSparkMax m : lDrive) m.getEncoder().setPositionConversionFactor(kEncoderConversion);
        for(CANSparkMax m : rDrive) {
            m.getEncoder().setPositionConversionFactor(kEncoderConversion);
            m.setInverted(true);
        }

        resetEncoders();
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
            m.set(spd);
        }
    }

    public void setDrive(double left, double right) {
        setLDrive(left);
        setRDrive(right);
    }

    public void setDrive(double speed) {
        setDrive(speed, speed);
    }

    //vision aiming

    public void aim(double angle) {
        if(pid != null) {
            double spd = pid.calculate(angle); //use pid.setSetpoint?
            setDrive(spd, -spd);
        }
        setDrive(angle * kP, -angle * kP);
    }

    public double getEncoderAverage() {
        double total = 0;
        for(CANSparkMax m : lDrive) total += m.getEncoder().getPosition();
        for(CANSparkMax m : rDrive) total += m.getEncoder().getPosition();
        return total / 4;
    }

    public double getAbsoluteEncoderAverage() {
        double total = 0;
        for(CANSparkMax m : lDrive) total += Math.abs(m.getEncoder().getPosition());
        for(CANSparkMax m : rDrive) total += Math.abs(m.getEncoder().getPosition());
        return total / 4;
    }

    public void resetEncoders() {
        for(CANSparkMax m : lDrive) m.getEncoder().setPosition(0);
        for(CANSparkMax m : rDrive) m.getEncoder().setPosition(0);
    }

    public void printEncoders() {
        System.out.println(
            lDrive[0].getEncoder().getPosition() + " " + 
            lDrive[1].getEncoder().getPosition() + " " + 
            rDrive[0].getEncoder().getPosition() + " " +
            rDrive[1].getEncoder().getPosition() + " " +
            getEncoderAverage()
        );
    }
}
