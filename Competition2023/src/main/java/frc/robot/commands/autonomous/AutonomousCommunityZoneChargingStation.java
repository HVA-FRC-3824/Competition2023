package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.RobotContainer;

public class AutonomousCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousCommunityZoneChargingStation() {
    System.out.println("Running autonomous get out of community zone and autobalance command...");
    addCommands(
      // move robot forward until 2 degree angle is hit
      new WaitUntilCommand(RobotContainer.SWERVE_DRIVE_OBJ::approachChargeStationForward),
      
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, -0.4, 0))

      /* 
       * continue to move forward at a slower speed until you hit 0 degrees +- .5 for a extended period of time
       * move faster until you get out of community zone
       * stop robot
       * move backward towards charging station
       * continue to move robot forward until over 1 degree of gyro
       * autobalance command
       */
    );
  }
}