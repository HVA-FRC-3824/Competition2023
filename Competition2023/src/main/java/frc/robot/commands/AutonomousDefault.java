package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.Autobalance;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class AutonomousDefault extends SequentialCommandGroup{
    private Autobalance autoBalanceObj = new Autobalance();
    private Pose2d startingPose = new Pose2d(2, 2.75, new Rotation2d(0));
    private List<Translation2d> waypoints = List.of(new Translation2d(4.5, 2.75));
    private Pose2d middlePose = new Pose2d(7, 2.75, new Rotation2d(0));

    private List<Translation2d> waypoints2 = List.of(new Translation2d(3.5,2.75));
    private Pose2d endingPose = new Pose2d(4,2.75, new Rotation2d(0));
    public AutonomousDefault(){
        System.out.println("Running Autonomous Default...");
        addCommands(
            // get out of community zone
            new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.generateRamsete(startingPose, waypoints, middlePose, 3, false)),
            // stop robot
            new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.driveWithVoltage(0, 0))
        );
    }
}
