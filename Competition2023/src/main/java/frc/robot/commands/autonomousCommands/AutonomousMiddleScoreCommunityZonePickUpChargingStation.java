package frc.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import frc.robot.RobotContainer;

public class AutonomousMiddleScoreCommunityZonePickUpChargingStation extends SequentialCommandGroup {
  public AutonomousMiddleScoreCommunityZonePickUpChargingStation() {
    addCommands(
      // new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders())
    );
  }
}