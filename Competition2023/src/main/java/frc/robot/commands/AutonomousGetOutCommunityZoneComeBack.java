// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousGetOutCommunityZoneComeBack extends SequentialCommandGroup {
  /** Creates a new AutonomousGetOutCommunityZoneComeBack. */
  public AutonomousGetOutCommunityZoneComeBack() {
    System.out.println("Running Autonomous Get out of community zone and come back to grid...");
      addCommands(
        // move robot forward
        new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),

        // wait for path to finish
        new WaitCommand(2.25),

        // stop chassis
        new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0.0, 0.0)),
        
        // wait for drive train to stop completely
        new WaitCommand(1),

        // go back to in front of the grid
        new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0.4, 0.0)),

        // wait for path to finish
        new WaitCommand(2.25),

        // Stop Robot
        new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0.0, 0.0))
        );
  }
}
