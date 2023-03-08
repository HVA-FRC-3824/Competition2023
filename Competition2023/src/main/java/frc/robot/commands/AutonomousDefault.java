package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class AutonomousDefault extends SequentialCommandGroup{


    private AutoScore m_autoScore = new AutoScore();

    public AutonomousDefault(){

        // score
        m_autoScore.score(0, 0);

        // back up
        RobotContainer.M_WEST_COAST_DRIVE.drive(-3.2, 0.0);

        // to the cone
        RobotContainer.M_WEST_COAST_DRIVE.drive(3.2, 0.0);

        // look for april tag

        // pick up an object


    private Pose2d startingPose = new Pose2d(0, 0, new Rotation2d(0));
    private List<Translation2d> waypoints = List.of(new Translation2d(1.15, 0));
    private Pose2d endingPose = new Pose2d(4.95, 0, new Rotation2d(0));
    public AutonomousDefault(){
        addCommands(
            new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.generateRamsete(startingPose, waypoints, endingPose, 0, false))
        );

    }
}
