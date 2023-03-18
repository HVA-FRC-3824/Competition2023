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
public class HybridScoreCommunityZone extends SequentialCommandGroup {
  /** Creates a new HybridScoreCommunityZone. */
  public HybridScoreCommunityZone() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // backward
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, .7, 0.0)),
      new WaitCommand(.2),

      // forard fast to knock off cube
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -1, 0.0)),
      new WaitCommand(.65),

      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0, 0.0)), 
      new WaitCommand(.3),

      // back to score the cube
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, .4, 0)),
      new WaitCommand(1.5),
      
      // out of community zone
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
      new WaitCommand(2.7),

      // stop robot
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0, 0.0))
    );
  }
}
