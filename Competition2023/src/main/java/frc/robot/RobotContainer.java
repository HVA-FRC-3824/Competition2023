package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.InlineCommands;
import frc.robot.subsystems.WestCoastDrive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final WestCoastDrive M_WEST_COAST_DRIVE = new WestCoastDrive();
 
  /**
   * Instantiate inline commands before OI because OI requires commands before binding to buttons
   * Inline commands requires OI when retrieving joystick values. 
   */
  public static final InlineCommands m_inlineCommands = new InlineCommands();
  public static final OI m_OI = new OI();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_OI.configureButtonBindings();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousDefault();
    // will add a dropdown selection to the smart dashboard later when multiple autonomous commands have been created.
  }
  
  /**
   * Configures TalonFX (Falcon 500) objects with passed in parameters. Falcon
   * 500s will be used for the chassis only, thus Motion Magic
   * is not required. (PIDController with Gyro/Vision or ControlMode.Velocity will
   * be used instead).
   */
  public static void configureTalonFX(WPI_TalonFX talonFX, boolean setInverted, boolean setSensorPhase, double kF,double kP, double kI, double kD) {
    // Factory default to reset TalonFX and prevent unexpected behavior.
    talonFX.configFactoryDefault();

    // Configure Sensor Source for Primary PID.
    talonFX.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.K_PID_LOOP_IDX,
        Constants.K_TIMEOUT_MS);

    // Configure TalonFX to drive forward when LED is green.
    talonFX.setInverted(setInverted);
    // Configure TalonFX's sensor to increment its value as it moves forward.
    talonFX.setSensorPhase(setSensorPhase);

    /**
     * Configure the nominal and peak output forward/reverse.
     * 
     * Nominal Output: minimal/weakest motor output allowed during closed-loop. Peak
     * Output: maximal/strongest motor output allowed during closed-loop.
     */
    talonFX.configNominalOutputForward(0, Constants.K_TIMEOUT_MS);
    talonFX.configNominalOutputReverse(0, Constants.K_TIMEOUT_MS);
    talonFX.configPeakOutputForward(1, Constants.K_TIMEOUT_MS);
    talonFX.configPeakOutputReverse(-1, Constants.K_TIMEOUT_MS);

    /* Set the Velocity gains (FPID) in slot0. */
    talonFX.selectProfileSlot(Constants.K_SLOT_IDX, Constants.K_PID_LOOP_IDX);
    talonFX.config_kF(Constants.K_SLOT_IDX, kF, Constants.K_TIMEOUT_MS);
    talonFX.config_kP(Constants.K_SLOT_IDX, kP, Constants.K_TIMEOUT_MS);
    talonFX.config_kI(Constants.K_SLOT_IDX, kI, Constants.K_TIMEOUT_MS);
    talonFX.config_kD(Constants.K_SLOT_IDX, kD, Constants.K_TIMEOUT_MS);

    /**
     * Reset/zero the TalonFX's sensor. Will be required for implementation into
     * chassis (position considered), but not launcher (velocity only).
     */
    talonFX.setSelectedSensorPosition(0, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS);
  }
}