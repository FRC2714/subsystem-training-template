package frc.robot;

public final class Constants {
  public static final class Intake {
    public static final int kRollerMotorCanId = 11;
    public static final int kPivotMotorCanId = 12;
    public static final int kIndexerMotorCanId = 13;

    public static final double kPivotThreshold = 5;

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

  public static final class DragonConstants {

    public static final int kPivotMotorCanId = 16;
    public static final int kPivotRollerMotorCanId = 17;
    public static final double kPivotReduction = 1;
    public static final double kPivotThreshold = 5;
    public static final double kRollerCurrentThreshold = 5;

    public static final double kPivotMinAngle = 10.0;
    public static final double kPivotMaxAngle = 250.0;
    public static final double kClearFromElevatorAngle = 165.5;
    public static final double kClearFromReefAngle = 50;
    public static final double kClearFromReefNoCoralAngle = 64;
    public static final double kClearToScoreL4Angle = 70.7;

    public static final double kP = 0.015;
    public static final double kG = -0.25;

    public static final class PivotSetpoints {
      // Zero offset in Hardware Client is 10
      public static final double kStartingConfig = 245;
      public static final double kStow = 27.25;
      public static final double kHandoff = 221.1;
      public static final double kHandoffStandby = 200;
      public static final double kScore = 85.5;
    }

    public static final class RollerSetpoints {
      public static final double kIntake = -0.1;
      public static final double kExtake = .7;
      public static final double kStop = 0;
      public static final double kHold = -0.2;
    }
  }
}
