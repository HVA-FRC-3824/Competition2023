package frc.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousLeftScoreCommunityZone extends SequentialCommandGroup {
  public AutonomousLeftScoreCommunityZone() {
    addCommands(
      /* 
       * angle arm
       * extend arm
       * grabber motor release
       * grabber motor stop
       * wait
       * go back
       * turn around?
       */
    );
  }
}