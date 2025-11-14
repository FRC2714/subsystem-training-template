// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Dragon;
import frc.robot.subsystems.Intake;

public class RobotContainer {
  private final Intake m_intake = new Intake();
  private final Dragon m_dragon = new Dragon();

  private final Joystick m_buttonBoxRight = new Joystick(0);
  private final JoystickButton m_intakeButton = new JoystickButton(m_buttonBoxRight, 3);
  // private final JoystickButton m_extakeButton = new JoystickButton(m_buttonBoxRight, 5);
  private final JoystickButton m_scoreButton = new JoystickButton(m_buttonBoxRight, 1);
  // private final JoystickButton m_stowButton = new JoystickButton(m_buttonBoxRight, 4);
  private final JoystickButton m_handoffButton = new JoystickButton(m_buttonBoxRight, 2);
  private final JoystickButton m_dragonScoreButton = new JoystickButton(m_buttonBoxRight, 6);
  private final JoystickButton m_dragonStowButton = new JoystickButton(m_buttonBoxRight, 5);
  //private final JoystickButton m_dragonExtakeButton = new JoystickButton(m_buttonBoxRight, 1);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
     m_intakeButton.onTrue(m_intake.intakeCommand());

    // m_extakeButton.onTrue(m_intake.extakeCommand());

    
    m_scoreButton.onTrue(m_intake.scoreCommand());

    // m_stowButton.onTrue(m_intake.stowCommand());

    m_handoffButton.onTrue(m_dragon.handoff());

    m_dragonScoreButton.onTrue(m_dragon.score());

    //m_dragonExtakeButton.onTrue(m_dragon.extake());

    m_dragonStowButton.onTrue(m_dragon.stow());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
