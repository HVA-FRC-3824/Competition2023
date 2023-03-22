// Copyright (c) FIRST and other WPILib contributors.
package frc.robot.commands.simpleCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class ResetFieldForwardPositionGyro extends CommandBase {
  public ResetFieldForwardPositionGyro() {}

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    RobotContainer.SWERVE_DRIVE_OBJ.resetFieldCentricity();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}