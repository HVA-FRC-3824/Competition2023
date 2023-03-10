package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class AutonomousDefault extends SequentialCommandGroup{
    private Pose2d startingPose = new Pose2d(0, 0, new Rotation2d(0));
    private List<Translation2d> waypoints = List.of(new Translation2d(2.5, 0));
    private Pose2d endingPose = new Pose2d(5, 0, new Rotation2d(0));
    public AutonomousDefault(){
        System.out.println("Running Autonomous Default...");
        addCommands(
            new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.generateRamsete(startingPose, waypoints, endingPose, 0, false))
        );
    }
}
