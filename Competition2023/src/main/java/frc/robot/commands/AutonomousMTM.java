// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.RobotContainer;

// Middle position, Top row, Middle column
public class AutonomousMTM extends SequentialCommandGroup {
  private Pose2d startingPose = new Pose2d(0, 0, new Rotation2d(0));
  private List<Translation2d> waypoints = List.of(new Translation2d(1.15, 1.569));
  private Pose2d endingPose = new Pose2d(4.95, 1.569, new Rotation2d(0));

  public AutonomousMTM() {
    addCommands(
      new InstantCommand(() -> RobotContainer.M_ARM.extendArmTop()),
      new WaitCommand(1),
      // how to get it to follow to a point, maxVel is in m/s
      RobotContainer.M_WEST_COAST_DRIVE.generateRamsete(startingPose, waypoints, endingPose, 2, false),
      // Don't forget to stop subsystems.
      new InstantCommand(() -> RobotContainer.M_ARM.retractArm())
      //new InstantCommand(() -> RobotContainer.m_chamber.setBasePower(0))
    );
  }
}