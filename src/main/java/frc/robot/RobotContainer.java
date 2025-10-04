// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private final Joystick m_buttonBoxRight = new Joystick(0);
  private final JoystickButton m_intakeButton = new JoystickButton(m_buttonBoxRight, 6);
  private final JoystickButton m_extakeButton = new JoystickButton(m_buttonBoxRight, 5);
  private final JoystickButton m_scoreButton = new JoystickButton(m_buttonBoxRight, 1);
  private final JoystickButton m_stowButton = new JoystickButton(m_buttonBoxRight, 4);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // TODO: Intake button

    // TODO: Extake button

    // TODO: Score button

    // TODO: Stow button
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
