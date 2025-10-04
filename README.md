# Subsystem Training Template

This is a template for training students on command based programming with a basic subsystem from our 2025 robot.

## Getting Started

This template takes care of the motor configuration and infrastructure for you. All you need to do is fill in the sections of the program with a `TODO`:

### Intake.java

Implement `intakeCommand()`, `extakeCommand()`, `scoreCommand()`, and `stowCommand()`. The javadocs of each command includes a description of what it should do.

The methods `setPivot()`, `setRollerSpeed()`, and `setIndexerSpeed()` are available to control the motors. An `m_outerBeamBreak` and `m_innerBeamBreak` are available for sensing the game piece. Use `.isPressed()` to get their state.

### RobotContainer.java

Add bindings for the buttons and their respective actions. Be sure to consider the button's behavior i.e. whether it should do something just from the initial press or while the button is pressed.
