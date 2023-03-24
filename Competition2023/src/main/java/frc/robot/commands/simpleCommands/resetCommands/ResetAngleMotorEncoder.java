package frc.robot.commands.simpleCommands.resetCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class ResetAngleMotorEncoder extends CommandBase {
  public ResetAngleMotorEncoder() {}

  @Override
  public void initialize() {
    RobotContainer.ARM_ANGLE_OBJ.resetAngleMotorEncoder();
    System.out.println("Reset Arm Angle Encoder");
  }

  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}