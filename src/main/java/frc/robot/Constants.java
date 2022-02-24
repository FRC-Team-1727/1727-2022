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
        public static final int kIntakePort = 2;
        public static final int[][] kIntakePistonPort = new int[][] {
            {6,7},
            {8,9}
        };
    }

    public static final class UptakeConstants {
        public static final int kUptakePort = 5;
    }

    public static final class XboxConstants {
        public static final int kXboxPort = 0;
        public static final double kTriggerThreshold = 0.2;
    }

    public static final class ShooterConstants {
        public static final int[] kFlywheelPort = new int[] {8,7};
        public static final int kFlywheelEncoderPort = 10;
        public static final int[] kHoodPistonPort = new int[] {11,12};

        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kF = 0;

        public static final double kHoodChangeThreshold = 0;

        public static final double kDefaultSpeed = 3000;
    }

    public static final class AimConstants {
        public static final double kVisionAngle = 45;
        public static final double kVisionHeight = 104 - 18; //reflective tape height = 8'8" or 104"
        public static final double kHubRadius = 24;

        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    public static final class ClimbConstants {
        public static final int[] kClimbPort = new int[] {13,14};
        public static final double kClimbSpeed =  1;
    }

}
