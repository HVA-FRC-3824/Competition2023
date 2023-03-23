package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.RobotContainer;

import frc.robot.commands.Autobalance;

public class AutonomousCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousCommunityZoneChargingStation() {
    System.out.println("Running autonomous get out of community zone and autobalance command...");
    addCommands(
      // move robot forward over charging station
      new WaitUntilCommand(RobotContainer.SWERVE_DRIVE_OBJ::approachAndClimbOverChargeStationForward),
      
      // Wait .5 seconds to ensure the robot is out of the community zone
      new WaitCommand(.5),

      // Wait 1 second with the robot stopped
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, 0, 0)),
      new WaitCommand(1),

      // go back to charging station
      new WaitUntilCommand(RobotContainer.SWERVE_DRIVE_OBJ::approachChargeStationBackward)
    );
    // autobalance
    CommandScheduler.getInstance().schedule(new Autobalance(RobotContainer.SWERVE_DRIVE_OBJ));
  }
}