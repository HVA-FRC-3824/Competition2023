package frc.robot.commands.autonomousCommands;

// import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.RobotContainer;

public class AutonomousRightScoreCommunityZonePickUpScoreChargingStation extends SequentialCommandGroup {
  public AutonomousRightScoreCommunityZonePickUpScoreChargingStation() {
    addCommands(
      // new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders())
    );
  }
}