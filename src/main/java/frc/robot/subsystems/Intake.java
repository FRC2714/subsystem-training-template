package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants;
import frc.robot.Constants.Intake.PivotSetpoints;

public class Intake extends SubsystemBase {
  private SparkFlex m_pivot =
      new SparkFlex(Constants.Intake.kPivotMotorCanId, MotorType.kBrushless);
  private AbsoluteEncoder m_pivotEncoder = m_pivot.getAbsoluteEncoder();
  private SparkClosedLoopController m_pivotController = m_pivot.getClosedLoopController();

  private SparkFlex m_roller =
      new SparkFlex(Constants.Intake.kRollerMotorCanId, MotorType.kBrushless);
  private SparkFlex m_indexer =
      new SparkFlex(Constants.Intake.kIndexerMotorCanId, MotorType.kBrushless);

  private SparkLimitSwitch m_outerBeamBreak = m_indexer.getReverseLimitSwitch();
  private SparkLimitSwitch m_innerBeamBreak = m_indexer.getForwardLimitSwitch();

  private enum PivotSetpoint {
    STOW,
    INTAKE,
    EXTAKE,
    SCORE
  }

  private enum RollerSetpoint {
    INTAKE,
    INTAKE_SLOW,
    EXTATE,
    SCORE,
    STOP
  }

  public Intake() {
    m_pivot.configure(
        Configs.Intake.pivotConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_roller.configure(
        Configs.Intake.rollerConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    m_indexer.configure(
        Configs.Intake.indexerConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  /** Set the position of the pivot motor. */
  private void setPivot(PivotSetpoint setpoint) {
    double targetPosition = 0;
    switch (setpoint) {
      case STOW:
        targetPosition = PivotSetpoints.kStow;
        break;
      case INTAKE:
        targetPosition = PivotSetpoints.kIntake;
        break;
      case EXTAKE:
        targetPosition = PivotSetpoints.kExtake;
        break;
      case SCORE:
        targetPosition = PivotSetpoints.kScore;
        break;
      default:
        return;
    }
    m_pivotController.setReference(targetPosition, ControlType.kPosition);
  }

  /** Set the speed of the roller motor. */
  private void setRollerSpeed(RollerSetpoint setpoint) {
    double speed;
    switch (setpoint) {
      case INTAKE:
        speed = Constants.Intake.RollerSetpoints.kIntake;
        break;
      case INTAKE_SLOW:
        speed = Constants.Intake.RollerSetpoints.kIntakeSlow;
        break;
      case EXTATE:
        speed = Constants.Intake.RollerSetpoints.kExtake;
        break;
      case SCORE:
        speed = Constants.Intake.RollerSetpoints.kScore;
        break;
      case STOP:
        speed = Constants.Intake.RollerSetpoints.kStop;
        break;
      default:
        return;
    }
    m_roller.set(speed);
  }

  /** Set the speed of the indexer motor. */
  private void setIndexerSpeed(RollerSetpoint setpiont) {
    double speed;
    switch (setpiont) {
      case INTAKE:
        speed = Constants.Intake.RollerSetpoints.kIntake;
        break;
      case INTAKE_SLOW:
        speed = Constants.Intake.RollerSetpoints.kIntakeSlow;
        break;
      case EXTATE:
        speed = Constants.Intake.RollerSetpoints.kExtake;
        break;
      case SCORE:
        speed = Constants.Intake.RollerSetpoints.kScore;
        break;
      case STOP:
        speed = Constants.Intake.RollerSetpoints.kStop;
        break;
      default:
        return;
    }
    m_indexer.set(speed);
  }

  /**
   * Intake the game piece and hold it when it is done to prepare for the next possible command
   * which is either scoring or extaking.
   */
  public Command intakeCommand() {
    return this.run(
            () -> {
              setPivot(PivotSetpoint.INTAKE);
              setRollerSpeed(RollerSetpoint.INTAKE);
              setIndexerSpeed(RollerSetpoint.INTAKE);
            })
        .until(() -> (m_innerBeamBreak.isPressed()))
        .andThen(
            () -> {
              setIndexerSpeed(RollerSetpoint.EXTATE);
            })
        .until(() -> (m_outerBeamBreak.isPressed()))
        .andThen(
            () -> {
              setRollerSpeed(RollerSetpoint.STOP);
              setIndexerSpeed(RollerSetpoint.STOP);
            });
  }

  /** Spit the game piece back out. */
  public Command extakeCommand() {
    // TODO
    return this.run(
        () -> {
          setPivot(PivotSetpoint.EXTAKE);
          setIndexerSpeed(RollerSetpoint.EXTATE);
          setRollerSpeed(RollerSetpoint.EXTATE);
        });
  }

  /** Intake the game piece further through the robot. */
  public Command scoreCommand() {
    // TODO
    return this.run(
        () -> {
          setPivot(PivotSetpoint.SCORE);
          setIndexerSpeed(RollerSetpoint.SCORE);
          setRollerSpeed(RollerSetpoint.SCORE);
        });
  }

  /** Stop the rollers and put in the stow position. */
  public Command stowCommand() {
    // TODO
    return this.run(
        () -> {
          setPivot(PivotSetpoint.STOW);
          setIndexerSpeed(RollerSetpoint.STOP);
          setRollerSpeed(RollerSetpoint.STOP);
        });
  }
}
