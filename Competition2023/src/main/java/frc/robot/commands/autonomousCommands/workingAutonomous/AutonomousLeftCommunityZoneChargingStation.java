package frc.robot.commands.autonomousCommands.workingAutonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class AutonomousLeftCommunityZoneChargingStation extends SequentialCommandGroup {
  public AutonomousLeftCommunityZoneChargingStation() {
    addCommands(
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.setIdleModeBrake()),

      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
      new WaitCommand(2.25),
      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, 0, 0)),

      new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.setIdleModeCoast())
    );
  }
}
