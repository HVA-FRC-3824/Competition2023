package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutonomousScoreHybridBackUp extends SequentialCommandGroup{
  /** Creates a new ScoreHybridBackUp. */
  public AutonomousScoreHybridBackUp() {
    System.out.println("Running Autonomous Default...");
    addCommands(
      // Extend arm bottom position
      new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.armExtendCustom(0)), // TODO extend arm bottom position
     
      // Angle arm to lower hub
      new InstantCommand(() -> RobotContainer.ARM_ANGLE_OBJ.setArmActualPosCustom(0)), 
      // TODO angle arm bottom position

      // wait command .5 sec
      new WaitCommand(.5),

      // grabber negative power
      new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(Constants.GRABBER_VOLTAGE)),

      // wait .75 sec
      new WaitCommand(.75),

      // grabber stop
      new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(0)),

      // angle arm up
      new InstantCommand(() -> RobotContainer.ARM_ANGLE_OBJ.setArmActualPosCustom(0)), 
      // TODO angle arm horizontal position

      // extension in
      new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.armExtendCustom(0)),

      // wait command .75 sec
      new WaitCommand(.75),
      
      // move robot forward out of community zone
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),

      // wait for path to finish
      new WaitCommand(2.5),

      // stop chassis
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0.0, 0.0))
    );
  }
}