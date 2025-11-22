package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public final class Constants {
  public static final class Intake {
    public static final int kRollerMotorCanId = 11;
    public static final int kPivotMotorCanId = 12;
    public static final int kIndexerMotorCanId = 13;

    public static final double kPivotThreshold = 5; // TODO

    public static final int kBeamBreakDioChannel = 0;
    public static final double kP = 0.06;
    public static final double kG = 0.2;

    public static final double kPivotReduction = 1;

    public static final class PivotSetpoints {
      // Zero offset in Hardware Client is 10
      public static final double kStow = 15.2;
      public static final double kIntake = 69;
      public static final double kExtake = 90;
      public static final double kScore = 75;

      public static final double kZeroOffsetDegrees = 270;
      public static final double kClimb = 100;
    }

    public static final class RollerSetpoints {
      public static final double kIntake = 0.6;
      public static final double kIntakeSlow = 0.2;
      public static final double kExtake = -0.4;
      public static final double kScore = 0.5;
      public static final double kStop = 0;
    }
  }

  public class Drive {
    public static final class DriveConstants {
      public static final double kIntake = 0.5;
      public static final double kExtake = -0.5;
      public static final double kStop = 0;

      public static final boolean kGyroReversed = false;

      public static final int kFrontLeftDrivingCanId = 1;
      public static final int kFrontLeftTurningCanId = 2;
      public static final double kFrontLeftChassisAngularOffset = 0.0;

      public static final int kFrontRightDrivingCanId = 5;
      public static final int kFrontRightTurningCanId = 6;
      public static final double kFrontRightChassisAngularOffset = 0.0;

      public static final int kRearLeftDrivingCanId = 3;
      public static final int kRearLeftTurningCanId = 7;
      public static final double kBackLeftChassisAngularOffset = 0.0;

      public static final int kRearRightDrivingCanId = 4;
      public static final int kRearRightTurningCanId = 8;
      public static final double kBackRightChassisAngularOffset = 0.0;

      public static final double kWheelBase = 0.5; // meters
      public static final double kTrackWidth = 0.5; // meters

      public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));;

      public static final double kMaxSpeedMetersPerSecond = 4.0;
      public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI; // 180
      public static final double kMaxAngularSpeed = Math.PI; // 180
    }

    public static final class ModuleConstants {
      public static final double kIntake = 0.5;
      public static final double kExtake = -0.5;
      public static final double kStop = 0;

      public static final double kDrivingP = 2 * Math.PI; // radians per second
      public static final double kDrivingI = 2 * Math.PI; // radians per second
      public static final double kDrivingD = 2 * Math.PI; // radians per second
      public static final double kDrivingF = 2 * Math.PI; // radians per second

      public static final double kTurningP = 2 * Math.PI; // radians per second
      public static final double kTurningI = 2 * Math.PI; // radians per second
      public static final double kTurningD = 2 * Math.PI; // radians per second

      public static final int kTurningEncoderResolution = 4096;
    }
  }
}
