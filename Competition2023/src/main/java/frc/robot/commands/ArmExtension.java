// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmExtension extends CommandBase {
  public boolean extensionLimiter;
  /** Creates a new ArmExtension. */
  public ArmExtension() {
    addRequirements(RobotContainer.ARM_OBJ);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    extensionLimiter = RobotContainer.ARM_OBJ.getExtensionLimiter();
    if(extensionLimiter){
      if(RobotContainer.ARM_OBJ.getArmExtensionMotorEncoderPosition() < Constants.MAX_ARM_EXTENSION){
        RobotContainer.ARM_OBJ.getArmExtensionMotor().setVoltage(Constants.ARM_EXTENSION_VOLTAGE);
      }else{
        System.out.println("WARNING: Arm Extension Position is greater than maximum extension!!! ");
        RobotContainer.ARM_OBJ.getArmExtensionMotor().setVoltage(0);
      }
    }else{
      RobotContainer.ARM_OBJ.getArmExtensionMotor().setVoltage(Constants.ARM_EXTENSION_VOLTAGE);
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
