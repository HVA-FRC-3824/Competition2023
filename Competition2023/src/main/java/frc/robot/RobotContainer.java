package frc.robot;

import frc.robot.commands.AutonomousDefault;
import frc.robot.commands.AutonomousMTM;
import frc.robot.commands.AutonomousRTM;
import frc.robot.commands.InlineCommands;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkMaxRelativeEncoder;
// importing subsystems
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.WestCoastDrive;
import frc.robot.subsystems.LEDs;

/* This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here. */
public class RobotContainer{
  // The robot's subsystems and commands are defined here...
  public static final WestCoastDrive M_WEST_COAST_DRIVE = new WestCoastDrive();
  public static final Arm M_ARM = new Arm();
  public static final Grabber M_GRABBER = new Grabber();
  public static final LEDs M_LEDS = new LEDs();
 
  /* Instantiate inline commands before OI because OI requires commands before binding to buttons
   * Inline commands requires OI when retrieving joystick values. */
  public static final InlineCommands m_inlineCommands = new InlineCommands();
  public static final OI m_OI = new OI();
  
  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();
  /* The container for the robot. Contains subsystems, Operator interface, and commands. 
   * All objects, methods, and classes should be accessed through this class. */
  public RobotContainer(){
    // Configures the button bindings obviously
    m_OI.configureButtonBindings();

    this.initializeAutoChooser();
  }

  // Called when teleop is initialized
  public static void initializeTeleopDefaultCommands(){
    // Sets default command for the westcostdrivetrain to driving with driver joystick.
    M_WEST_COAST_DRIVE.setDefaultCommand(m_inlineCommands.m_driveWithJoystick);
    // Sets default command for the arm to angling with operator joystick
    M_ARM.setDefaultCommand(m_inlineCommands.m_angleArmWithJoystick);
  }

  /* Set options for autonomous command chooser and display them for selection on
   * the SmartDashboard. Using string chooser rather than command chooser because
   * if using a command chooser, will instantiate all the autonomous commands.
   * This may cause problems (e.g. initial trajectory position is from a different
   * command's path). */
  private void initializeAutoChooser(){
    // Add options (which autonomous commands can be selected) to chooser.
    m_autoChooser.setDefaultOption("DEFAULT COMMAND DRIVE FORWARD", "default");
    m_autoChooser.addOption("MIDDLE POS|TOP ROW|MIDDLE COLUMN", "MidTopMid");
    m_autoChooser.addOption("RIGHT POS|TOP ROW|MIDDLE COLUMN", "RightTopMid");
    m_autoChooser.addOption("LEFT POS|TOP ROW|MIDDLE COLUMN", "LeftTopMid");

    // Display chooser on SmartDashboard for operators to select which autonomous command to run during the auto period.
    SmartDashboard.putData("Autonomous Command", m_autoChooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(){
    switch (m_autoChooser.getSelected()){
      case "default":
        return new AutonomousDefault();
      case "MidTopMid":
        return new AutonomousMTM();
      case "RightTopMid":
        return new AutonomousRTM();
      default:
        System.out.println("\nError selecting autonomous command:\nCommand selected: " + m_autoChooser.getSelected() + "\n");
        return null;
    }
  }
  
  /* Configures TalonFX (Falcon 500) objects with passed in parameters. Falcon 500s will be used for the 
   * chassis only, thus Motion Magic (Control mode for Talon SRX that provides the benefits of 
   * Motion Profiling without needing to generate motion profile trajectory points.) is not required. 
   * (PIDController with Gyro/Vision or ControlMode.Velocity will be used instead). */
  public static void configureTalonFX(WPI_TalonFX talonFX, boolean setInverted, boolean setSensorPhase, double kF,double kP, 
                                      double kI, double kD){
    // Factory default to reset TalonFX and prevent unexpected behavior.
    talonFX.configFactoryDefault();
    // Configure Sensor Source for Primary PID.
    talonFX.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS);
    // Configure TalonFX to drive forward when LED is green.
    talonFX.setInverted(setInverted);
    // Configure TalonFX's sensor to increment its value as it moves forward.
    talonFX.setSensorPhase(setSensorPhase);
    /* Configure the nominal and peak output forward/reverse.
     * Nominal Output: minimal/weakest motor output allowed during closed-loop. Peak
     * Output: maximal/strongest motor output allowed during closed-loop. */
    talonFX.configNominalOutputForward(0, Constants.K_TIMEOUT_MS);
    talonFX.configNominalOutputReverse(0, Constants.K_TIMEOUT_MS);
    talonFX.configPeakOutputForward(1, Constants.K_TIMEOUT_MS);
    talonFX.configPeakOutputReverse(-1, Constants.K_TIMEOUT_MS);
    // Set the Velocity gains (FPID) in slot0.
    talonFX.selectProfileSlot(Constants.K_SLOT_IDX, Constants.K_PID_LOOP_IDX);
    talonFX.config_kF(Constants.K_SLOT_IDX, kF, Constants.K_TIMEOUT_MS);
    talonFX.config_kP(Constants.K_SLOT_IDX, kP, Constants.K_TIMEOUT_MS);
    talonFX.config_kI(Constants.K_SLOT_IDX, kI, Constants.K_TIMEOUT_MS);
    talonFX.config_kD(Constants.K_SLOT_IDX, kD, Constants.K_TIMEOUT_MS);
    /* Reset/zero the TalonFX's sensor. Will be required for implementation into
     * chassis (position considered), but not launcher (velocity only). */
    talonFX.setSelectedSensorPosition(0, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS);
  }

  /**
   * Configures TalonSRX objects with passed in parameters.
   * 
   * @param controlMode If true, configure with Motion Magic. If false, configure
   *                    without Motion Magic. (Motion Magic not required for
   *                    TalonSRXs that will set with ControlMode.Velocity).
   */
  public static void configureTalonSRX(WPI_TalonSRX talonSRX, boolean controlMode, FeedbackDevice feedbackDevice, 
                                      boolean setInverted, boolean setSensorPhase, double kF, double kP, double kI, 
                                      double kD, int kCruiseVelocity, int kAcceleration, boolean resetPos){
    // Factory default to reset TalonSRX and prevent unexpected behavior.
    talonSRX.configFactoryDefault();
    // Configure Sensor Source for Primary PID.
    if (feedbackDevice == null){
      System.out.println("Motor(ID:" + talonSRX.getDeviceID() + ") without feedback device configuring");
    }else{
      System.out.println("Motor(ID:" + talonSRX.getDeviceID() + ") with feedback device configuring");
      talonSRX.configSelectedFeedbackSensor(feedbackDevice, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS);
    }
    // Configure TalonSRX to drive forward when LED is green.
    talonSRX.setInverted(setInverted);
    // Configure TalonSRX's sensor to increment its value as it moves forward.
    talonSRX.setSensorPhase(setSensorPhase);
    // Determine if the internal PID is being used
    if (controlMode){
      // Set relevant frame periods (Base_PIDF0 and MotionMagic) to periodic rate (10ms).
      talonSRX.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.K_TIMEOUT_MS);
      talonSRX.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.K_TIMEOUT_MS);
    }
    /* Configure the nominal and peak output forward/reverse.
     * 
     * Nominal Output: minimal/weakest motor output allowed during closed-loop. Peak
     * Output: maximal/strongest motor output allowed during closed-loop. */
    talonSRX.configNominalOutputForward(0, Constants.K_TIMEOUT_MS);
    talonSRX.configNominalOutputReverse(0, Constants.K_TIMEOUT_MS);
    talonSRX.configPeakOutputForward(1, Constants.K_TIMEOUT_MS);
    talonSRX.configPeakOutputReverse(-1, Constants.K_TIMEOUT_MS);
    // Set Motion Magic/Velocity gains (FPID) in slot0.
    talonSRX.selectProfileSlot(Constants.K_SLOT_IDX, Constants.K_PID_LOOP_IDX);
    talonSRX.config_kF(Constants.K_SLOT_IDX, kF, Constants.K_TIMEOUT_MS);
    talonSRX.config_kP(Constants.K_SLOT_IDX, kP, Constants.K_TIMEOUT_MS);
    talonSRX.config_kI(Constants.K_SLOT_IDX, kI, Constants.K_TIMEOUT_MS);
    talonSRX.config_kD(Constants.K_SLOT_IDX, kD, Constants.K_TIMEOUT_MS);
    // Determine if the internal PID is being used
    if (controlMode){
      // Set acceleration and cruise velocity for Motion Magic.
      talonSRX.configMotionCruiseVelocity(kCruiseVelocity, Constants.K_TIMEOUT_MS);
      talonSRX.configMotionAcceleration(kAcceleration, Constants.K_TIMEOUT_MS);
    }
    // Reset/zero the TalonSRX's sensor.
    if (resetPos){
      talonSRX.setSelectedSensorPosition(0, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS);
    }
    System.out.println("Motor (ID:" + talonSRX.getDeviceID() + ") sucessfully configured");
  }

  public static void configureSparkMax(CANSparkMax sparkMax, SparkMaxPIDController pidController, boolean inverted, double kP, double kI,
                                      double kD, double kIz, double kFF, double kMinOutput, double kMaxOutput ){
    /* The restoreFactoryDefaults method can be used to reset the configuration parameters in the SPARK MAX to 
     * their factory default state. If no argument is passed, these parameters will not persist between power cycles */
    sparkMax.restoreFactoryDefaults();

    sparkMax.setInverted(inverted);
    
    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setIZone(kIz);
    pidController.setFF(kFF);
    pidController.setOutputRange(kMinOutput, kMaxOutput);

    sparkMax.burnFlash();
  }
}