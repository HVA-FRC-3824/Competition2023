package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.RobotContainer;

public class AutonomousDefault extends SequentialCommandGroup{
    public AutonomousDefault(){
        System.out.println("Running Autonomous Default...");
        addCommands(
            // move robot forward
            new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),

            // wait for path to finish
            new WaitCommand(2.25),

            // stop chassis
            new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0.0, 0.0))
        );
    }
}