package frc.robot.subsystems;

// #region imports
// General
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// SmartDashboard
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.Field2d;

// Driving
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

// Pneumatics
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import java.util.List;

// Odometry
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.SPI;
// #endregion

public class WestCoastDrive extends SubsystemBase{
  // Motors
  private CANSparkMax m_leftMasterSpark;
  private SparkMaxPIDController m_leftMasterPIDController;

  private CANSparkMax m_leftSlaveSpark;
  private SparkMaxPIDController m_leftSlavePIDController;

  private CANSparkMax m_rightMasterSpark;
  private SparkMaxPIDController m_rightMasterPIDController;

  private CANSparkMax m_rightSlaveSpark;
  private SparkMaxPIDController m_rightSlavePIDController;

  private DifferentialDrive m_differentialDrive;

  // Pneumatic objects
  private Solenoid m_gearShiftRight;
  private Solenoid m_gearShiftLeft;
  // private PneumaticHub m_pneumaticHub;

  // Odometry
  private AHRS m_ahrs; // altitude and heading reference system [AHRS]
  public DifferentialDriveOdometry m_odometry;
  private RelativeEncoder m_leftEncoder;
  private RelativeEncoder m_rightEncoder;

  public WestCoastDrive(){
    // Instantiating drivetrain objects (configuring motor controllers, etc)
    m_leftMasterSpark = new CANSparkMax(Constants.WCD_LEFT_MASTER_ID, MotorType.kBrushless);
    m_leftMasterPIDController = m_leftMasterSpark.getPIDController();
    RobotContainer.configureSparkMax(m_leftMasterSpark, m_leftMasterPIDController, true, 0, 0, 0,
    0, 0, 0, 0);

    m_leftSlaveSpark = new CANSparkMax(Constants.WCD_LEFT_SLAVE_ID, MotorType.kBrushless);
    m_leftSlavePIDController = m_leftSlaveSpark.getPIDController();
    RobotContainer.configureSparkMax(m_leftSlaveSpark, m_leftSlavePIDController, true, 0, 0, 0, 0,
    0, 0, 0);

    // Creates encoder object
    m_leftEncoder = m_leftMasterSpark.getEncoder();

    /* Set the control mode and output value for the leftSlave motor controller so that it will follow the leftMaster controller.
     * Could be interchanged with a motor controller group. */ 
    //m_leftSlaveSpark.follow(m_leftMasterSpark);

    m_rightMasterSpark = new CANSparkMax(Constants.WCD_RIGHT_MASTER_ID, MotorType.kBrushless);
    m_rightMasterPIDController = m_rightMasterSpark.getPIDController();
    RobotContainer.configureSparkMax(m_rightMasterSpark, m_rightMasterPIDController, true, 0, 0, 0, 0,
    0, 0, 0);

    m_rightSlaveSpark = new CANSparkMax(Constants.WCD_RIGHT_SLAVE_ID, MotorType.kBrushless);
    m_rightSlavePIDController = m_rightSlaveSpark.getPIDController();
    RobotContainer.configureSparkMax(m_rightSlaveSpark, m_rightSlavePIDController, true, 0, 0, 0, 0, 
    0, 0, 0);

    // Creates encoder object
    m_rightEncoder = m_rightMasterSpark.getEncoder();
 
    /* Set the control mode and output value for the rightSlave motor controller so that it will follow the rightMaster controller.
     * Could be interchanged with a motor controller group. */ 
    //m_rightSlaveSpark.follow(m_rightMasterSpark);

    // creates a differential drive object so that we can use its methods and address all the motors as one drivetrain
    m_differentialDrive = new DifferentialDrive(m_leftMasterSpark, m_rightMasterSpark);

    // Try to instantiate the navx gyro with exception catch, used for odometry
    try{
      m_ahrs = new AHRS(SPI.Port.kMXP);
    }catch(RuntimeException ex){
      System.out.println("\nError instantiating navX-MXP:\n" + ex.getMessage() + "\n");
    }

    // used for auto
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_ahrs.getAngle()), getDistanceMeters(m_leftEncoder.getPosition()),
                                                                      getDistanceMeters(m_rightEncoder.getPosition()));

    // used for compressor
    // m_pneumaticHub = new PneumaticHub(Constants.PNEUMATIC_HUB_ID);

    // configureing solenoids for each piston in the west coast drive "transmission"
    m_gearShiftLeft = new Solenoid(Constants.PNEUMATIC_HUB_ID, PneumaticsModuleType.REVPH, Constants.WCD_LEFT_SHIFTER_CHANNEL);
    m_gearShiftRight = new Solenoid(Constants.PNEUMATIC_HUB_ID, PneumaticsModuleType.REVPH, Constants.WCD_RIGHT_SHIFTER_CHANNEL);

    // disables safety for the motors because otherwise auto doesn't work maybe
    m_differentialDrive.setSafetyEnabled(false);
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic(){
    // Reminder, use .update instead of recreating the object using the constructor, does the same thing but prevents memory leakage
    m_odometry.update(Rotation2d.fromDegrees(m_ahrs.getAngle()), getDistanceMeters(m_leftEncoder.getPosition()), 
                                             getDistanceMeters(m_rightEncoder.getPosition()));

    // Field2d odometry stuff will go here 
    SmartDashboard.putNumber("Gyro Heading: ", m_ahrs.getAngle());
    SmartDashboard.putNumber("Gyro Pitch: ", m_ahrs.getPitch());
    SmartDashboard.putNumber("Gyro Roll: ", m_ahrs.getRoll());

    // COMPRESSOR ISN'T ON ROBOT RN
    // // Puts pressure on the smart dashboard
    // SmartDashboard.putNumber("System Pressure: ", m_pneumaticHub.getPressure(0));
    // // If the Prssure is less than 110 then it enables the compressor and if the pressure is greater than 120 then it disables the compressor
    // if(m_pneumaticHub.getPressure(Constants.ANALOG_PRESSURE_SENSOR_PH_ID) < 110){
    //     m_pneumaticHub.enableCompressorDigital();
    // }else if(m_pneumaticHub.getPressure(Constants.ANALOG_PRESSURE_SENSOR_PH_ID) > 120){
    //     m_pneumaticHub.disableCompressor();
    // }

    // // Puts compressor status on Smart Dashboard
    // SmartDashboard.putBoolean("Compressor on: ", m_pneumaticHub.getCompressor());

  }

  // Method to drive robot, using power %
  public void drive(double power, double turn){ // all the way forward is -1, all the back is 1 for joystick (power parameter)
    // Reduces sensitivity of twist for turning.
    turn = turn/Constants.WCD_TURN_SENS; // increase number to decrease sensitvity
    // prevents the power from going` over max power
    if(power > Constants.WCD_MAX_POWER){
      power = Constants.WCD_MAX_POWER;
    }else if(power < -Constants.WCD_MAX_POWER){
      power = -Constants.WCD_MAX_POWER;
    }
    
    // applies the power to the drivetrain
    // negative turn and power because robot goes backwards otherwise.
    m_differentialDrive.arcadeDrive(-turn, -power, true);
  }

  // Methods to control gearbox shifter.
  public void shiftLowGear(){
    m_gearShiftLeft.set(false);
    m_gearShiftRight.set(false);
  }
  public void shiftHighGear(){
    m_gearShiftLeft.set(true);
    m_gearShiftRight.set(true);
  }

  public void setAndHoldPose(){
    // TODO TEST and add in deadzone, I don't even think these are the right methods
    double leftEncoderTempPos = m_leftEncoder.getPosition();
    double rightEncoderTempPos = m_leftEncoder.getPosition();
    m_rightMasterPIDController.setReference(rightEncoderTempPos, CANSparkMax.ControlType.kPosition);
    m_leftMasterPIDController.setReference(leftEncoderTempPos, CANSparkMax.ControlType.kPosition);
  }

  public void driveWithVelocity(boolean forward, double velocity){
    //TODO write method, to be used by autobalance command
  }

  public double getDistanceMeters(double degrees) {
    return Constants.WHEEL_CIRCUMFERENCE * (degrees/360);
  }

  // used for autobalance command
  public AHRS returnGyro(){
    return(m_ahrs);
  }

  // used for auto
  public Pose2d getPose(){
    return m_odometry.getPoseMeters();
  }
  
  // Used for auto ramsete driving
  public void driveWithVoltage(double leftVoltage, double rightVoltage){
    m_leftMasterSpark.setVoltage(-rightVoltage); // TODO figure out if still applicable: negative
    m_rightMasterSpark.setVoltage(leftVoltage); // TODO figure out if still applicable: positive because right side is inverted for the arcadeDrive method.
    m_differentialDrive.feed();
  }

  // AUTO
  public double getLeftEncoderRate(){
    return m_leftEncoder.getVelocity() * Constants.K_ENCODER_DISTANCE_PER_PULSE * 1000;
  }

  // AUTO
  public double getRightEncoderRate(){
    return -m_rightEncoder.getVelocity() * Constants.K_ENCODER_DISTANCE_PER_PULSE * 1000;
  }

  // AUTO
  public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(this.getLeftEncoderRate(), this.getRightEncoderRate());
  }

  // Reset gyro to zero the heading of the robot.
  public void zeroHeading(){
    m_ahrs.reset();
    m_ahrs.setAngleAdjustment(0.0);
  }

  // AUTO
  public void resetEncoders(){
    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);
  }

  /**
   * Generates ramsete command for following passed in path in autonomous.
   * @param startingPose is the position at which the robot starts up at.
   * @param waypoints are the points in which the robot travels through to arrive at its end point.
   * @param endingPose is the position at which the robot ends up at.
   * @param maxVelocity controls how fast the robot will move through the trajectory/path.
   * @param isReversed controls whether the robot travels forwards or backwards through the waypoints.
   * @return sequential command group that follows the path and stops when complete.
   */
  public SequentialCommandGroup generateRamsete(Pose2d startingPose, List<Translation2d> waypoints, Pose2d endingPose, 
                                                double maxVelocity, boolean isReversed){
    System.out.println("Generate Ramsete command method running...");
    // Voltage constraint so never telling robot to move faster than it is capable of achieving.
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(Constants.K_S_VOLTS,
                                                                       Constants.K_V_VOLT_SECONDS_PER_METER,
                                                                       Constants.K_A_VOLT_SECONDS_SQUARED_PER_METER), 
                                                                       Constants.K_DRIVE_KINEMATICS, 
                                                                       10);
    
    // Configuration for trajectory that wraps path constraints.
    TrajectoryConfig trajConfig = new TrajectoryConfig(maxVelocity, Constants.K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
      // Add kinematics to track robot speed and ensure max speed is obeyed.
      .setKinematics(Constants.K_DRIVE_KINEMATICS)
      // Apply voltage constraint created above.
      .addConstraint(autoVoltageConstraint)
      // Reverse the trajectory based on passed in parameter.
      .setReversed(isReversed);

    // Generate trajectory: initialPose, interiorWaypoints, endPose, trajConfig
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
      // Starting pose
      startingPose,
      // Pass through these interior waypoints
      waypoints,
      // Ending pose
      endingPose,
      // Pass config
      trajConfig);

    // Create command that will follow the trajectory.
    RamseteCommand ramseteCommand = new RamseteCommand(trajectory, RobotContainer.M_WEST_COAST_DRIVE::getPose,
                                                       new RamseteController(Constants.K_RAMSETE_B, Constants.K_RAMSETE_ZETA),
                                                       new SimpleMotorFeedforward(Constants.K_S_VOLTS,
                                                                                  Constants.K_V_VOLT_SECONDS_PER_METER,
                                                                                  Constants.K_A_VOLT_SECONDS_SQUARED_PER_METER),
                                                       Constants.K_DRIVE_KINEMATICS,
                                                       RobotContainer.M_WEST_COAST_DRIVE::getWheelSpeeds,
                                                       new PIDController(Constants.K_P_DRIVE_VEL, 0, 0),
                                                       new PIDController(Constants.K_P_DRIVE_VEL, 0, 0),
                                                       RobotContainer.M_WEST_COAST_DRIVE::driveWithVoltage, // RamseteCommand passes volts to the callback.
                                                       RobotContainer.M_WEST_COAST_DRIVE);

    // Return command group that will run path following command, then stop the robot at the end.
    return ramseteCommand.andThen(new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.driveWithVoltage(0, 0)));
  }
}