package frc.robot.commands.simpleCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class ResetExtensionMotorEncoder extends CommandBase {
  public ResetExtensionMotorEncoder() {}

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    RobotContainer.ARM_EXTENSION_OBJ.resetExtensionMotorEncoder();
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