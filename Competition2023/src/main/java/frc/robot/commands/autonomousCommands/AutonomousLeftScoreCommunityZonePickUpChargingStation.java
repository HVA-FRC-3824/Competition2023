package frc.robot.commands.autonomousCommands;

// import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.RobotContainer;

public class AutonomousLeftScoreCommunityZonePickUpChargingStation extends SequentialCommandGroup {
  public AutonomousLeftScoreCommunityZonePickUpChargingStation() {
    addCommands(
      // new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders())
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