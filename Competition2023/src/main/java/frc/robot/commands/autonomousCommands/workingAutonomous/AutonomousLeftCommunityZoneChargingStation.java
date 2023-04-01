package frc.robot.commands.autonomousCommands.workingAutonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.RobotContainer;

public class AutonomousLeftCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousLeftCommunityZoneChargingStation() {
    System.out.println("RUNNING AUTONOMOUS LEFT COMMUNITY ZONE AND AUTOBALANCE COMMAND... ");
    addCommands(
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.setIdleModeBrake()),

      // move foward out of community zone for 2.5 sec then stop for .25
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
      new WaitCommand(.5),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
      new WaitCommand(.5),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
      new WaitCommand(.5),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
      new WaitCommand(.5),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
      new WaitCommand(.5),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, 0, 0)),
      new WaitCommand(.25),

      // move right to in front of the charging station for 1 seconds then stop for .25
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.4, 0.0, 0.0)),
      new WaitCommand(.5),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.4, 0.0, 0.0)),
      new WaitCommand(.5),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0.0, 0.0)),
      new WaitCommand(.25),

      // go back to charging station
      new WaitUntilCommand(RobotContainer.SWERVE_DRIVE_OBJ::approachChargeStationBackward),

      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.setIdleModeCoast())
    );
  }
}
