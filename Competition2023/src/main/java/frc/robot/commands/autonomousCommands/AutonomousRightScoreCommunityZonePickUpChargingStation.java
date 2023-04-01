package frc.robot.commands.autonomousCommands;

// import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.RobotContainer;

public class AutonomousRightScoreCommunityZonePickUpChargingStation extends SequentialCommandGroup {
  public AutonomousRightScoreCommunityZonePickUpChargingStation() {
    addCommands(
      // new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders())
    );
  }
}