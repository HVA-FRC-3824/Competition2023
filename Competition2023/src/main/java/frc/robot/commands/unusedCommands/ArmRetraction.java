package frc.robot.commands.unusedCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmRetraction extends CommandBase {
  public boolean extensionLimiter;
  /** Creates a new ArmRetraction. */
  public ArmRetraction() {
    addRequirements(RobotContainer.ARM_EXTENSION_OBJ);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    extensionLimiter = RobotContainer.ARM_EXTENSION_OBJ.getExtensionLimiter();
    if(extensionLimiter){
      if(RobotContainer.ARM_EXTENSION_OBJ.getArmExtensionMotorEncoderPosition() > Constants.MIN_ARM_EXTENSION){
        RobotContainer.ARM_EXTENSION_OBJ.getArmExtensionMotor().setVoltage(-Constants.ARM_EXTENSION_VOLTAGE);
      }else{
        System.out.println("WARNING: Arm Extension Position is less than Minimum extension!!! ");
        RobotContainer.ARM_EXTENSION_OBJ.getArmExtensionMotor().setVoltage(0);
      }
    }else{
      RobotContainer.ARM_EXTENSION_OBJ.getArmExtensionMotor().setVoltage(-Constants.ARM_EXTENSION_VOLTAGE);
    }
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
