package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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

    }
}
