package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class ResetAngleMotorEncoder extends CommandBase {
  /** Creates a new ResetAngleMotorEncoder. */
  public ResetAngleMotorEncoder() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.ARM_ANGLE_OBJ.resetAngleMotorEncoder();
    System.out.println("Reset Arm Angle Encoder");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

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
