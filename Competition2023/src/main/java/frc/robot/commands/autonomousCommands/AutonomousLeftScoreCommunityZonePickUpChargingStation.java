package frc.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousLeftScoreCommunityZonePickUpChargingStation extends SequentialCommandGroup {
  public AutonomousLeftScoreCommunityZonePickUpChargingStation() {
    addCommands(
      /*
       * angle arm
       * extend arm
       * 
       * grabber motor release
       * wait 
       * grabber motor stop
       * 
       * back up
       * turn around
       * 
       * move to pick up piece
       * angle arm to pick up position
       * extend arm to pick up position
       * grabber motor pick up
       * move forward
       * grabber motor stop
       * 
       * move arm back to hold pos
       * 
       * move toward charging station
       * autobalance
       */
    );
  }
}