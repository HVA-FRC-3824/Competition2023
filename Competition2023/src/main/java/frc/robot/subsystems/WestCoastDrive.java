package frc.robot.subsystems;

// #region imports
// General
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// SmartDashboard
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.Field2d;

// Driving
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// Pneumatics
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

// Odometry
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
//import com.ctre.phoenix.sensors.CANCoder;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import edu.wpi.first.math.geometry.Rotation2d;
//import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
// #endregion

public class WestCoastDrive extends SubsystemBase{
  // Motors
  private WPI_TalonSRX m_leftMasterSRX;
  private CANSparkMax m_leftMasterSpark;
  private SparkMaxPIDController m_leftMasterPIDController;
  private RelativeEncoder m_leftEncoder;

  private WPI_TalonSRX m_leftSlaveSRX;
  private CANSparkMax m_leftSlaveSpark;
  private SparkMaxPIDController m_leftSlavePIDController;

  private WPI_TalonSRX m_rightMasterSRX;
  private CANSparkMax m_rightMasterSpark;
  private SparkMaxPIDController m_rightMasterPIDController;
  private RelativeEncoder m_rightEncoder;

  private WPI_TalonSRX m_rightSlaveSRX;
  private CANSparkMax m_rightSlaveSpark;
  private SparkMaxPIDController m_rightSlavePIDController;

  private DifferentialDrive m_differentialDrive;

  // Pneumatic objects
  private Solenoid m_gearShiftRight;
  private Solenoid m_gearShiftLeft;
  private PneumaticHub m_pneumaticHub;

  // Odometry
  private AHRS m_ahrs; // altitude and heading reference system [AHRS]
  // private CANCoder m_rightCanCoder;
  // private CANCoder m_leftCanCoder;
  // private final DifferentialDriveOdometry m_odometry;

  // Robot State
  private String m_state = "JOYSTICK_MODE";
  private String m_prevState = "JOYSTICK";

  public WestCoastDrive(){
    // Instantiating drivetrain objects (configuring motor controllers, etc)
    m_leftMasterSpark = new CANSparkMax(Constants.WCD_LEFT_MASTER_ID, MotorType.kBrushless);
    m_leftEncoder = RobotContainer.configureSparkMax(m_leftMasterSpark, m_leftMasterPIDController, m_leftEncoder, false, 0, 
                                                 0, 0, 0, 0, 0, 0);
    m_leftMasterSRX = new WPI_TalonSRX(Constants.WCD_LEFT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_leftMasterSRX, false, null, false, false, 
                                  0, 0, 0, 0, 0, 0, false);

    m_leftSlaveSpark = new CANSparkMax(Constants.WCD_LEFT_SLAVE_ID, MotorType.kBrushless);
    RobotContainer.configureSparkMax(m_leftSlaveSpark, m_leftSlavePIDController, null, false, 0,
                                  0, 0, 0, 0, 0, 0);
    m_leftSlaveSRX = new WPI_TalonSRX(Constants.WCD_LEFT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_leftSlaveSRX, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    /* Set the control mode and output value for the leftSlave motor controller so that it will follow the leftMaster controller.
     * Could be interchanged with a motor controller group. */ 
    m_leftSlaveSRX.follow(m_leftMasterSRX);
    m_leftSlaveSpark.follow(m_leftMasterSpark);

    m_rightMasterSpark = new CANSparkMax(Constants.WCD_RIGHT_MASTER_ID, MotorType.kBrushless);
    m_rightEncoder = RobotContainer.configureSparkMax(m_rightMasterSpark, m_rightMasterPIDController, m_rightEncoder, false, 0, 
                                                  0, 0, 0, 0, 0, 0);
    m_rightMasterSRX = new WPI_TalonSRX(Constants.WCD_RIGHT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_rightMasterSRX, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    m_rightSlaveSpark = new CANSparkMax(Constants.WCD_RIGHT_SLAVE_ID, MotorType.kBrushless);
    RobotContainer.configureSparkMax(m_rightSlaveSpark, m_rightSlavePIDController, null, false, 0,
                                  0, 0, 0, 0, 0, 0);
    m_rightSlaveSRX = new WPI_TalonSRX(Constants.WCD_RIGHT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_rightSlaveSRX, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    /* Set the control mode and output value for the rightSlave motor controller so that it will follow the rightMaster controller.
     * Could be interchanged with a motor controller group. */ 
    m_rightSlaveSRX.follow(m_rightMasterSRX);
    m_rightSlaveSpark.follow(m_rightMasterSpark);

    // creates a differential drive object so that we can use its methods and address all the motors as one drivetrain
    m_differentialDrive = new DifferentialDrive(m_leftMasterSRX, m_rightMasterSRX);
    // m_differentialDrive = new DifferentialDrive(m_leftMasterSpark, m_rightMasterSpark);

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

  //   Used for tracking robot position.
  //   m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(this.getHeading()));
  //   this.getHeading() appears to be deprecated, replaced by needing xtra encoders
  //   m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_ahrs.getAngle()) , m_leftMaster.NEED_ENCODERS, m_rightMaster.NEED_ENCODERS);
  
  // m_leftCanCoder = new CANCoder(7);
  // m_rightCanCoder = new CANCoder(8);
  
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic(){
    // Field2d odometry stuff will go here 
    SmartDashboard.putNumber("Gyro Heading: ", m_ahrs.getAngle());
    SmartDashboard.putNumber("Gyro Pitch: ", m_ahrs.getPitch());
    SmartDashboard.putNumber("Gyro Roll: ", m_ahrs.getRoll());

    /* If statement to switch the mode we are in, makes sure teleop is initialized, and sets prevState so that it doesn't set
     * to default in the middle of it running. m_teleopInit is used to make sure we have already entered into teleop period. */
    // TODO Rewrite this into a command that requires the westcoast susbsystem, which will end the default driveWithJoystick
    if((m_state.equals("JOYSTICK_MODE")) && (Robot.m_teleopInit == 1) && (!m_prevState.equals("JOYSTICK"))){
      // Cancel the old command
      RobotContainer.m_inlineCommands.m_autoBalance.cancel();
      // Set the new command
      RobotContainer.M_WEST_COAST_DRIVE.setDefaultCommand(RobotContainer.m_inlineCommands.m_driveWithJoystick);
      // Set our previous state to our new state
      m_prevState = "JOYSTICK";
    }else if(m_state.equals("BALANCE_MODE") && (Robot.m_teleopInit == 1) && (!m_prevState.equals("BALANCE"))){
      // Cancel the old command
      RobotContainer.m_inlineCommands.m_driveWithJoystick.cancel();
      // Set the new command
      RobotContainer.M_WEST_COAST_DRIVE.setDefaultCommand(RobotContainer.m_inlineCommands.m_autoBalance);
      // Set our previous state to our new state
      m_prevState = "BALANCE";
    }
    // Puts current state of drive train on smart dashboard
    SmartDashboard.putString("Current Drive Train State: ", m_prevState);

    // Puts pressure on the smart dashboard
    SmartDashboard.putNumber("System Pressure: ", m_pneumaticHub.getPressure(0));
    // If the Prssure is less than 110 then it enables the compressor and if the pressure is greater than 120 then it disables the compressor
    if(m_pneumaticHub.getPressure(Constants.PNEUMATIC_HUB_ANALOG_ID) < 110){
        m_pneumaticHub.enableCompressorDigital();
    }else if(m_pneumaticHub.getPressure(Constants.PNEUMATIC_HUB_ANALOG_ID) > 120){
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

  // method to drive a specific distance using PIDs and the encoders
  public void driveWithEcoders(double distance){
    //TODO write method
  }

  /* This method will be called when the button for auto balance is clicked, and also during autonomous to
   * balance while other robots are climbing on. It works by having 6 'states', 3 each direction of leaning,
   * 3 degrees of either how leaned it is or how far to move, the more leaned the further it moves. */
  public void autoBalance(){
    float tempRollRate = m_ahrs.getRoll();
    if(tempRollRate > 1.0){
      if(tempRollRate > 2.5){
        if(tempRollRate > 5.0){
          // driveWithEncoders(move a lot);
        }else{
          // driveWithEncoders(move a little more);
        }
      }else{
        // driveWithEncoders(move a little);
      }
    }else if(tempRollRate < -1.0){
      if(tempRollRate < -2.5){
        if(tempRollRate < -5.0){
          // driveWithEncoders(-move a lot);
        }else{
          // driveWithEncoders(-move a litte more);
        }
      }else{
        // driveWithEncoders(-move a litte);
      }
    }
  }

  // method used to calculate the distance into degrees inteligable to the encoder
  public double getDegrees(double desired_travel_distance) {
    return (360 * desired_travel_distance) / Constants.CIRCUMFERENCE;
  }

  // method used to turn the degrees of the encoder into distance
  public double getDistance(double provided_degrees) {
    return Constants.CIRCUMFERENCE * (provided_degrees/360);
  }

  // used to change the state of the robot, called when clicking the change state button
  public void changeState(){
    if(m_state == "JOYSTICK_MODE"){
      m_state = "BALANCE_MODE";
    }else if(m_state == "BALANCE_MODE"){
      m_state = "JOYSTICK_MODE";
    }
  }
}