package frc.robot.commands.autonomousCommands.workingAutonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.RobotContainer;

import frc.robot.commands.Autobalance;

public class AutonomousMiddleCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousMiddleCommunityZoneChargingStation() {
    System.out.println("RUNNING AUTONOMOUS MIDDLE COMMUNITY ZONE AND AUTOBALANCE COMMAND... ");
    addCommands(
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.setIdleModeBrake()),

      // move robot forward over charging station
      new WaitUntilCommand(RobotContainer.SWERVE_DRIVE_OBJ::approachAndClimbOverChargeStationForward),
      
      // Wait .5 seconds to ensure the robot is out of the community zone
      new WaitCommand(.5),

      // Wait 1 second with the robot stopped
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, 0, 0)),
      new WaitCommand(1),

      // go back to charging station
      new WaitUntilCommand(RobotContainer.SWERVE_DRIVE_OBJ::approachChargeStationBackward),

      // autobalance
      new Autobalance(RobotContainer.SWERVE_DRIVE_OBJ)
    );
  }
}