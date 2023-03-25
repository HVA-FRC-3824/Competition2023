package frc.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousLeftScoreCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousLeftScoreCommunityZoneChargingStation() {
    addCommands(
      /*
       * angle arm
       * extend arm
       * grabber motor release
       * grabber motor stop
       * back up
       * move right
       * move backward toward charging station
       * autobalance
       */
    );
  }
}
