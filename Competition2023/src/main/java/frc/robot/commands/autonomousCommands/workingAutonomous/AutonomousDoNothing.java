package frc.robot.commands.autonomousCommands.workingAutonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class AutonomousDoNothing extends SequentialCommandGroup {
  public AutonomousDoNothing() {
    System.out.println("Running autonomous do nothing command...");
    addCommands(
      // new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders()),
      new WaitCommand(15)
    );
  }
}