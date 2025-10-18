package frc.robot;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

public class Configs {
  public static final class Intake {
    public static final SparkFlexConfig rollerConfig = new SparkFlexConfig();
    public static final SparkFlexConfig indexerConfig = new SparkFlexConfig();
    public static final SparkFlexConfig pivotConfig = new SparkFlexConfig();

    static {
      // Configure basic setting of the arm motor
      pivotConfig
          .smartCurrentLimit(40)
          .idleMode(IdleMode.kBrake)
          .inverted(true)
          .voltageCompensation(12);
      pivotConfig
          .absoluteEncoder
          .positionConversionFactor(360 / Constants.Intake.kPivotReduction)
          .inverted(false)
          .zeroCentered(false); // tune later
      pivotConfig
          .closedLoop
          .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
          // Set PID values for position control. We don't need to pass a closed
          // loop slot, as it will default to slot 0.
          .p(Constants.Intake.kP)
          .d(0)
          .outputRange(-1, 1)
          .positionWrappingEnabled(true)
          .positionWrappingInputRange(0, 360)
          .maxMotion
          .maxVelocity(4200 * 360)
          .maxAcceleration(6000 * 360)
          .allowedClosedLoopError(0.5);

      // Configure basic settings of the intake motor
      rollerConfig
          .inverted(false)
          .idleMode(IdleMode.kCoast)
          .smartCurrentLimit(40)
          .voltageCompensation(12);

      // Configure indexer
      indexerConfig.inverted(true).smartCurrentLimit(40).idleMode(IdleMode.kBrake);
      indexerConfig.limitSwitch.forwardLimitSwitchEnabled(false).reverseLimitSwitchEnabled(false);
      indexerConfig.signals.limitsPeriodMs(5);
    }
  }

  public static final class Dragon {
    public static final SparkFlexConfig pivotConfig = new SparkFlexConfig();
    public static final SparkFlexConfig rollerConfig = new SparkFlexConfig();

    static {
      // Configure basic setting of the pivot
      pivotConfig
          .smartCurrentLimit(40)
          .idleMode(IdleMode.kBrake)
          .inverted(false)
          .voltageCompensation(12);
      pivotConfig
          .absoluteEncoder
          .positionConversionFactor(360 / Constants.DragonConstants.kPivotReduction)
          .inverted(false)
          .zeroCentered(false); // tune later
      pivotConfig
          .closedLoop
          .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
          // Set PID values for position control. We don't need to pass a closed
          // loop slot, as it will default to slot 0.
          .p(Constants.DragonConstants.kP)
          .d(0)
          .outputRange(-1, 1)
          .positionWrappingEnabled(true)
          .positionWrappingInputRange(0, 360)
          .maxMotion
          .maxVelocity(4200 * 360)
          .maxAcceleration(6000 * 360)
          .allowedClosedLoopError(0.5);

      // Configure basic settings of the dragon motor
      rollerConfig
          .inverted(true)
          .idleMode(IdleMode.kCoast)
          .smartCurrentLimit(40)
          .voltageCompensation(12);
    }
  }
}
