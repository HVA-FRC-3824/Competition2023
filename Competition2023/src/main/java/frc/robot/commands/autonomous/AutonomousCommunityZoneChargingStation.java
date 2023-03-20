package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousCommunityZoneChargingStation() {
    System.out.println("Running autonomous get out of community zone and autobalance command...");
    addCommands(
      /* move robot forward until either significant deceleration is noticed or until a certian angle is hit
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