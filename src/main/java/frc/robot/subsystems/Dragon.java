// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Dragon extends SubsystemBase {
  /** Creates a new Dragon. */
  public enum PivotSetpoint {
    STARTING_CONFIG,
    STOW,
    HANDOFF,
    HANDOFF_STANDBY,
    SCORE
  }

  public enum RollerSetpoint {
    INTAKE,
    EXTAKE,
    STOP,
    HOLD,
  }

  private SparkFlex m_pivot =
      new SparkFlex(
          Constants.DragonConstants.kPivotMotorCanId,
          com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
  private AbsoluteEncoder m_pivotEncoder = m_pivot.getAbsoluteEncoder();
  private SparkClosedLoopController m_pivotController = m_pivot.getClosedLoopController();

  private SparkFlex m_roller =
      new SparkFlex(
          Constants.DragonConstants.kPivotRollerMotorCanId,
          com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

  public Dragon() {
    m_pivot.configure(
        frc.robot.Configs.Dragon.pivotConfig,
        com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters,
        com.revrobotics.spark.SparkBase.PersistMode.kPersistParameters);

    m_roller.configure(
        frc.robot.Configs.Dragon.rollerConfig,
        com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters,
        com.revrobotics.spark.SparkBase.PersistMode.kPersistParameters);
  }

  public void setPivot(PivotSetpoint setpoint) {
    double targetPosition = 0;
    switch (setpoint) {
      case STARTING_CONFIG:
        targetPosition = Constants.DragonConstants.PivotSetpoints.kStartingConfig;
        break;
      case STOW:
        targetPosition = Constants.DragonConstants.PivotSetpoints.kStow;
        break;
      case HANDOFF:
        targetPosition = Constants.DragonConstants.PivotSetpoints.kHandoff;
        break;
      case HANDOFF_STANDBY:
        targetPosition = Constants.DragonConstants.PivotSetpoints.kHandoffStandby;
        break;
      case SCORE:
        targetPosition = Constants.DragonConstants.PivotSetpoints.kScore;
        break;
    }
    m_pivotController.setReference(targetPosition, ControlType.kPosition);
  }

  public void setRollerSpeed(RollerSetpoint setpoint) {
    double speed = 0;
    switch (setpoint) {
      case INTAKE:
        speed = Constants.DragonConstants.RollerSetpoints.kIntake;
        break;
      case EXTAKE:
        speed = Constants.DragonConstants.RollerSetpoints.kExtake;
        break;
      case STOP:
        speed = Constants.DragonConstants.RollerSetpoints.kStop;
        break;
      case HOLD:
        speed = Constants.DragonConstants.RollerSetpoints.kHold;
        break;
    }
    m_roller.set(speed);
  }

  public Command stowCommand() {
    return this.run(
        () -> {
          setPivot(PivotSetpoint.STOW);
          setRollerSpeed(RollerSetpoint.STOP);
        });
  }

  public Command stowAndHoldCommand() {
    return this.run(
        () -> {
          setPivot(PivotSetpoint.STOW);
          setRollerSpeed(RollerSetpoint.HOLD);
        });
  }

  public Command handOffStandByCommand() {
    return this.run(
        () -> {
          setPivot(PivotSetpoint.HANDOFF_STANDBY);
          setRollerSpeed(RollerSetpoint.STOP);
        });
  }

  public Command handoffCommand() {
    return this.run(
        () -> {
          setPivot(PivotSetpoint.HANDOFF);
          setRollerSpeed(RollerSetpoint.INTAKE);
        });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
