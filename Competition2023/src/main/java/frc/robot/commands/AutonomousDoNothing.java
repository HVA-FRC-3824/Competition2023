// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousDoNothing extends SequentialCommandGroup {
  /** Creates a new AutonomousDoNothing. */
  public AutonomousDoNothing() {
    System.out.println("Running Autonomous do nothing command... ");
    addCommands(
      new WaitCommand(2.5)
    );
  }
}
