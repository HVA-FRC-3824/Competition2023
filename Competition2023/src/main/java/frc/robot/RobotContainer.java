package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.InlineCommands;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
 
  /**
   * Instantiate inline commands before OI because OI requires commands before binding to buttons
   * Inline commands requires OI when retrieving joystick values. 
   */
  public static final InlineCommands m_inlineCommands = new InlineCommands();
  public static final OperatorInterface m_operatorInterface = new OperatorInterface();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_operatorInterface.configureButtonBindings();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousDefault();
  }
}
