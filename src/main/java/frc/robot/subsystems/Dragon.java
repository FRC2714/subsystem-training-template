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
import frc.robot.Constants.DragonConstants;

public class Dragon extends SubsystemBase {
  private SparkFlex m_pivot =
      new SparkFlex(Constants.DragonConstants.kPivotMotorCanId, MotorType.kBrushless);
  private SparkClosedLoopController m_pivotController = m_pivot.getClosedLoopController();
  private AbsoluteEncoder m_pivotEncoder = m_pivot.getAbsoluteEncoder();

  private SparkFlex m_roller =
      new SparkFlex(Constants.DragonConstants.kPivotRollerMotorCanId, MotorType.kBrushless);

  private SparkLimitSwitch m_outerBeamBreak =
      m_roller.getForwardLimitSwitch(); // may needa switch direction

  private enum PivotSetpoint {
    STARTING_CONFIG,
    STOW,
    HANDOFF,
    HANDOFF_STANDBY,
    SCORE
  }

  private enum RollerSetpoint {
    INTAKE,
    EXTAKE,
    STOP,
    HOLD
  }

  public Dragon() {
    m_pivot.configure(
        Configs.Dragon.pivotConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_roller.configure(
        Configs.Dragon.pivotRollerConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  private void setPivot(PivotSetpoint setpoint) {
    double targetPosition = 0;
    switch (setpoint) {
      case STARTING_CONFIG:
        targetPosition = DragonConstants.PivotSetpoints.kStartingConfig;
        break;
      case STOW:
        targetPosition = DragonConstants.PivotSetpoints.kStow;
        break;
      case HANDOFF:
        targetPosition = DragonConstants.PivotSetpoints.kHandoff;
        break;
      case HANDOFF_STANDBY:
        targetPosition = DragonConstants.PivotSetpoints.kHandoffStandby;
        break;
      case SCORE:
        targetPosition = DragonConstants.PivotSetpoints.kScore;
        break;
      default:
        return;
    }
    m_pivotController.setReference(targetPosition, ControlType.kPosition);
  }

  private void setRollers(RollerSetpoint setpoint) {
    double speed = 0;
    switch (setpoint) {
      case INTAKE:
        speed = DragonConstants.RollerSetpoints.kIntake;
        break;
      case EXTAKE:
        speed = DragonConstants.RollerSetpoints.kExtake;
        break;
      case STOP:
        speed = DragonConstants.RollerSetpoints.kStop;
        break;
      case HOLD:
        speed = DragonConstants.RollerSetpoints.kHold;
        break;
      default:
        return;
    }
    m_roller.set(speed);
  }

  public Command handoff() {
    return this.run(
        () -> {
          setPivot(PivotSetpoint.HANDOFF);
          setRollers(RollerSetpoint.INTAKE);
        }).until(() -> m_outerBeamBreak.isPressed())
        .andThen(() -> {
            setRollers(RollerSetpoint.HOLD);
            setPivot(PivotSetpoint.SCORE);
        });
  }

  public Command score() {
    return this.run(
        () -> {
            setPivot(PivotSetpoint.SCORE);
          setRollers(RollerSetpoint.EXTAKE);
        });
  }

  public Command extake() {
    return this.run(
        () -> {
          setPivot(PivotSetpoint.SCORE);
          // setRollers(RollerSetpoint.EXTAKE);
        });
  }

  public Command stow() {
    return this.run(
        () -> {
          setPivot(PivotSetpoint.STOW);
          setRollers(RollerSetpoint.STOP);
        });
  }
}
