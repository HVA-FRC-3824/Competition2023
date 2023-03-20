package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousDoNothing extends SequentialCommandGroup {
  public AutonomousDoNothing() {
    System.out.println("Running autonomous do nothing command...");
    addCommands();
  }
}