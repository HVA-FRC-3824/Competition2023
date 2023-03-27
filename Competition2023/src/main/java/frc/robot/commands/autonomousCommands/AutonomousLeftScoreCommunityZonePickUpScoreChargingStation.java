package frc.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class AutonomousLeftScoreCommunityZonePickUpScoreChargingStation extends SequentialCommandGroup {
  public AutonomousLeftScoreCommunityZonePickUpScoreChargingStation() {
     addCommands(
      // new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders())
     );
  }
}