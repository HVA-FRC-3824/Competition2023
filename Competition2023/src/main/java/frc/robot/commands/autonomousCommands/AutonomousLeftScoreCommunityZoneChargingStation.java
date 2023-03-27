package frc.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

public class AutonomousLeftScoreCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousLeftScoreCommunityZoneChargingStation() {
    addCommands(
      // new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders())
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