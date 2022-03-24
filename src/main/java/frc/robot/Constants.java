// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DriveConstants {
        public static final int[] kLDrivePort = new int[] {4, 6};        
        public static final int[] kRDrivePort = new int[] {1, 3};

        public static final double kThreshold = 0.2;

        public static final double kWheelDiameter = 6; //replace this with empirical wheel diameter after finding it
    }

    public static final class IntakeConstants {
        public static final int kIntakePort = 4;
        public static final int[][] kIntakePistonPort = new int[][] {
            {2,3},
            {6,7}
        };
        public static final double kIntakeMultiplier = 0.8d;
    }

    public static final class UptakeConstants {
        public static final int[] kUptakePort = new int[] {5, 2};
    }

    public static final class XboxConstants {
        public static final int[] kXboxPort = {0, 1};
        public static final double kTriggerThreshold = 0.2;
    }

    public static final class ShooterConstants {
        public static final int[] kFlywheelPort = new int[] {8,7};
        public static final int kFlywheelEncoderPort = 10;
        public static final int[] kHoodPistonPort = new int[] {0,1};

        public static final double kP = 0.0008;
        public static final double kI = 0.000002;
        public static final double kD = 2;
        public static final double kF = 0;

        public static final double kHoodChangeThreshold = 0;

        public static final double kDefaultSpeed = 0;
        public static final double kFarSpeed = 2900;
        public static final double kCloseSpeed = 2850;
    }

    public static final class AimConstants {
        public static final double kVisionAngle = 57;
        public static final double kVisionHeight = 104 - 23; //reflective tape height = 8'8" or 104"
        public static final double kHubRadius = 34;

        public static final double kP = 0.01;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    public static final class ClimbConstants {
        //ENTER LIMIT SWITCH PORTS!!!
        public static final int[] kLimitPort = new int[] {7, 9};

        public static final int[] kClimbPort = new int[] {50,51};
        public static final double kClimbSpeed =  1; //0.1; //set the right speed (this is in rotations per tick)
        public static final float kClimbMax = 10; //change to rotation value when climb is at full height

        // PID CONSTANTS HAVE NOT BEEN TUNED CORRECTLY YET!!!
        public static final double kP = 0.001;
        public static final double kI = 0.000002;
        public static final double kD = 0;
        public static final double kF = 0;
    }

}
