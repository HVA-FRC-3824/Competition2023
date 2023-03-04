package frc.robot.subsystems;

// #region imports
// General
import frc.robot.Constants;
import frc.robot.RobotContainer;
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
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

// Odometry
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
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
  private PneumaticHub m_pneumaticHub;

  // Odometry
  private AHRS m_ahrs; // altitude and heading reference system [AHRS]
  public DifferentialDriveOdometry m_odometry;
  private RelativeEncoder m_leftEncoder;
  private RelativeEncoder m_rightEncoder;

  public WestCoastDrive(){
    // Instantiating drivetrain objects (configuring motor controllers, etc)
    m_leftMasterSpark = new CANSparkMax(Constants.WCD_LEFT_MASTER_ID, MotorType.kBrushless);
    RobotContainer.configureSparkMax(m_leftMasterSpark, m_leftMasterPIDController, true, 0, 0, 0,
    0, 0, 0, 0);

    m_leftSlaveSpark = new CANSparkMax(Constants.WCD_LEFT_SLAVE_ID, MotorType.kBrushless);
    RobotContainer.configureSparkMax(m_leftSlaveSpark, m_leftSlavePIDController, true, 0, 0, 0, 0,
    0, 0, 0);

    // Creates encoder object
    m_leftEncoder = m_leftMasterSpark.getEncoder();

    /* Set the control mode and output value for the leftSlave motor controller so that it will follow the leftMaster controller.
     * Could be interchanged with a motor controller group. */ 
    m_leftSlaveSpark.follow(m_leftMasterSpark);

    m_rightMasterSpark = new CANSparkMax(Constants.WCD_RIGHT_MASTER_ID, MotorType.kBrushless);
    RobotContainer.configureSparkMax(m_rightMasterSpark, m_rightMasterPIDController, true, 0, 0, 0, 0,
    0, 0, 0);

    m_rightSlaveSpark = new CANSparkMax(Constants.WCD_RIGHT_SLAVE_ID, MotorType.kBrushless);
    RobotContainer.configureSparkMax(m_rightSlaveSpark, m_rightSlavePIDController, true, 0, 0, 0, 0, 
    0, 0, 0);

    // Creates encoder object
    m_rightEncoder = m_rightMasterSpark.getEncoder();
 
    /* Set the control mode and output value for the rightSlave motor controller so that it will follow the rightMaster controller.
     * Could be interchanged with a motor controller group. */ 
    m_rightSlaveSpark.follow(m_rightMasterSpark);

    // creates a differential drive object so that we can use its methods and address all the motors as one drivetrain
    m_differentialDrive = new DifferentialDrive(m_leftMasterSpark, m_rightMasterSpark);

    /* Move m_odometry up here */
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_ahrs.getAngle()), getDistanceMeters(m_leftEncoder.getPosition()), getDistanceMeters(m_rightEncoder.getPosition()));

    m_pneumaticHub = new PneumaticHub(Constants.PNEUMATIC_HUB_ID);

    // configureing solenoids for each piston in the west coast drive "transmission"
    m_gearShiftLeft = new Solenoid(Constants.PNEUMATIC_HUB_ID, PneumaticsModuleType.REVPH, Constants.WCD_LEFT_SHIFTER_CHANNEL);
    m_gearShiftRight = new Solenoid(Constants.PNEUMATIC_HUB_ID, PneumaticsModuleType.REVPH, Constants.WCD_RIGHT_SHIFTER_CHANNEL);

    // Try to instantiate the navx gyro with exception catch, used for odometry
    try{
      m_ahrs = new AHRS(SPI.Port.kMXP);
    }catch(RuntimeException ex){
      System.out.println("\nError instantiating navX-MXP:\n" + ex.getMessage() + "\n");
    }
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic(){
    // Reminder, use .update instead of recreating the object using the constructor, does the same thing but prevents memory leakage
    m_odometry.update(Rotation2d.fromDegrees(m_ahrs.getAngle()), getDistanceMeters(m_leftEncoder.getPosition()), getDistanceMeters(m_rightEncoder.getPosition()));

    // Field2d odometry stuff will go here 
    SmartDashboard.putNumber("Gyro Heading: ", m_ahrs.getAngle());
    SmartDashboard.putNumber("Gyro Pitch: ", m_ahrs.getPitch());
    SmartDashboard.putNumber("Gyro Roll: ", m_ahrs.getRoll());

    // Puts pressure on the smart dashboard
    SmartDashboard.putNumber("System Pressure: ", m_pneumaticHub.getPressure(0));
    // If the Prssure is less than 110 then it enables the compressor and if the pressure is greater than 120 then it disables the compressor
    if(m_pneumaticHub.getPressure(Constants.ANALOG_PRESSURE_SENSOR_PH_ID) < 110){
        m_pneumaticHub.enableCompressorDigital();
    }else if(m_pneumaticHub.getPressure(Constants.ANALOG_PRESSURE_SENSOR_PH_ID) > 120){
        m_pneumaticHub.disableCompressor();
    }

    // Puts compressor status on Smart Dashboard
    SmartDashboard.putBoolean("Compressor on: ", m_pneumaticHub.getCompressor());
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
    m_differentialDrive.arcadeDrive(turn, power, true);
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
    //TODO get current position using encoders and make the drivetrain keep us at that position, add in deadzone
    // Why don't you just kill power to the motors at a certain point??
  }
  /* Inverse of f(x)= CIRCUMFERENCE * (degrees/360)*/
  public double getDegreesFromDistance(double meters){
    return (360*meters)/Constants.CIRCUMFERENCE;
  }

  /* Takes in meters and goes forward that amount */
  public void driveForwardMeters(double meters){

  }

  public void driveWithVelocity(boolean forward, double velocity){
    //TODO write method, used by autobalance command
  }

  public double getDistanceMeters(double degrees) {
    return Constants.CIRCUMFERENCE * (degrees/360);
  }

  // used for autobalance command
  public AHRS returnGyro(){
    return(m_ahrs);
  }
}