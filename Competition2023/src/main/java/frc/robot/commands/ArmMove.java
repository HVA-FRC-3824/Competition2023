// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmMove extends ParallelCommandGroup {
  /** Creates a new ArmMove. */
  
  public ArmMove() {
    addCommands(
      new RunCommand(() -> RobotContainer.M_ARM.setDesiredArmPosition(RobotContainer.m_OI.getOperatorController().getY())),
      new RunCommand(() -> RobotContainer.M_ARM.setArmActualPosToDesiredPos())
    );
  }
}
