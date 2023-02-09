package frc.robot.subsystems;

// #region imports
// General
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// SmartDashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.Field2d;
// Driving
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// Pneumatics
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
// Odometry
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.sensors.CANCoder;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import edu.wpi.first.math.geometry.Rotation2d;
//import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
// #endregion

public class WestCoastDrive extends SubsystemBase{
  // Motors
  private WPI_TalonSRX m_leftMaster;
  private WPI_TalonSRX m_leftSlave;
  private WPI_TalonSRX m_rightMaster;
  private WPI_TalonSRX m_rightSlave;
  private DifferentialDrive m_differentialDrive;

  // Pneumatic objects
  private Solenoid m_gearShiftRight;
  private Solenoid m_gearShiftLeft;

  // Odometry
  private AHRS m_ahrs; // altitude and heading reference system [AHRS]
  private CANCoder m_rightCanCoder;
  private CANCoder m_leftCanCoder;
  // private final DifferentialDriveOdometry m_odometry;

  public WestCoastDrive(){
    // Instantiating drivetrain objects (configuring motor controllers, etc)
    m_leftMaster = new WPI_TalonSRX(Constants.WCD_LEFT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_leftMaster, false, null, false, false, 
                                  0, 0, 0, 0, 0, 0, false);

    m_leftSlave = new WPI_TalonSRX(Constants.WCD_LEFT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_leftSlave, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    /* Set the control mode and output value for the leftSlave motor controller so that it will follow the leftMaster controller.
     * Could be interchanged with a motor controller group. */ 
    m_leftSlave.follow(m_leftMaster);

    m_rightMaster = new WPI_TalonSRX(Constants.WCD_RIGHT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_rightMaster, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    m_rightSlave = new WPI_TalonSRX(Constants.WCD_RIGHT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_rightSlave, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    /* Set the control mode and output value for the rightSlave motor controller so that it will follow the rightMaster controller.
     * Could be interchanged with a motor controller group. */ 
    m_rightSlave.follow(m_rightMaster);

    // creates a differential drive object so that we can use its methods and address all the motors as one drivetrain
    m_differentialDrive = new DifferentialDrive(m_leftMaster, m_rightMaster);

    // NEEDS TESTED *************** automatically sets the safety mode as disabled, IDK what it does yet, im asking Jovi, update, he has no clue, but ill leave it for now until I test it
    m_differentialDrive.setSafetyEnabled(false);

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
  
  m_leftCanCoder = new CANCoder(7);
  m_rightCanCoder = new CANCoder(8);
  
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic(){
    // Field2d odometry stuff will go here 
    SmartDashboard.putNumber("Gyro Heading: ", m_ahrs.getAngle());
    SmartDashboard.putNumber("Gyro Pitch: ", m_ahrs.getPitch());
    SmartDashboard.putNumber("Gyro Roll: ", m_ahrs.getRoll());
    SmartDashboard.putNumber("Left Encoder: ", m_leftCanCoder.getPosition());
    SmartDashboard.putNumber("Right Encoder: ", m_rightCanCoder.getPosition());
  }

  // Method to drive robot
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

  public void driveWithEcoders(double distance){

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

  public double getDegrees(double desired_travel_distance) {
    return (360 * desired_travel_distance) / Constants.CIRCUMFERENCE;
  }

  public double getDistance(double provided_degrees) {
    return Constants.CIRCUMFERENCE * (provided_degrees/360);
  }

}