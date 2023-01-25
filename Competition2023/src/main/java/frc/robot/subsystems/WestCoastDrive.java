package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.simulation.DoubleSolenoidSim;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class WestCoastDrive extends SubsystemBase {
  // Motors
  private WPI_TalonSRX m_leftMaster;
  private WPI_TalonSRX m_leftSlave;
  private WPI_TalonSRX m_rightMaster;
  private WPI_TalonSRX m_rightSlave;
  private DifferentialDrive m_differentialDrive;

  // Pneumatic objects
  private DoubleSolenoid m_gearShiftRight;
  private DoubleSolenoid m_gearShiftLeft;
  private PneumaticsModuleType REVPH;
  // private PneumaticHub m_revPneumaticHub;
  // private Compressor m_compressor;

  // Odometry
  // private AHRS m_ahrs; // altitude and heading reference system [AHRS]
  // private final DifferentialDriveOdometry m_odometry;

  public WestCoastDrive() {
    // Instantiating drivetrain objects (configuring motor controllers, etc)
    m_leftMaster = new WPI_TalonSRX(Constants.WCD_LEFT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_leftMaster, false, null, false, false, 
                                  0, 0, 0, 0, 0, 0, false);

    m_leftSlave = new WPI_TalonSRX(Constants.WCD_LEFT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_leftSlave, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    /** 
     *Set the control mode and output value for the leftSlave motor controller so that it will follow the leftMaster controller.
     *Could be interchanged with a motor controller group 
     */ 
    m_leftSlave.follow(m_leftMaster);

    m_rightMaster = new WPI_TalonSRX(Constants.WCD_RIGHT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_rightMaster, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    m_rightSlave = new WPI_TalonSRX(Constants.WCD_RIGHT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_rightSlave, false, null, false, false,
                                  0, 0, 0, 0, 0, 0, false);

    /** 
     *Set the control mode and output value for the rightSlave motor controller so that it will follow the rightMaster controller.
     *Could be interchanged with a motor controller group 
     */ 
    m_rightSlave.follow(m_rightMaster);

    // creates a differential drive object so that we can use its methods and address all the motors as one drivetrain
    m_differentialDrive = new DifferentialDrive(m_leftMaster, m_rightMaster);

    // automatically sets the safety mode as disabled, IDK what it does yet, im asking Jovi
    m_differentialDrive.setSafetyEnabled(false);

    // m_revPneumaticHub = new PneumaticHub();
    // m_compressor = new Compressor(PneumaticsModuleType.REVPH);
    // m_compressor.enableAnalog(90, 120);

    // configureing solenoids for each piston in the west coast drive "transmission"
    m_gearShiftLeft = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, 1, 0);
    m_gearShiftRight = new DoubleSolenoid(2, PneumaticsModuleType.REVPH, 1, 0); // unsure about channels

    // Try to instantiate the navx gyro with exception catch, used for odometry
    // try {
    //   m_ahrs = new AHRS(SPI.Port.kMXP);
    // }catch (RuntimeException ex) {
    //   System.out.println("\nError instantiating navX-MXP:\n" + ex.getMessage() + "\n");
    // }

    // Used for tracking robot position.
    // m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(this.getHeading()));
    // this.getHeading() appears to be deprecated, replaced by needing xtra encoders
    // m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_ahrs.getAngle()) , m_leftMaster.NEED_ENCODERS, m_rightMaster.NEED_ENCODERS);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("System Pressure", m_compressor.getPressure());
    // Field2d odometry stuff to go here
  }

  // method to drive robot
  public void drive(double power, double turn){ // all the way forward is -1, all the back is 1 for joystick (power parameter)
    // Reduces sensitivity of twist for turning.
    turn = turn/1.25; // 80% of the power? (1/1.25 = .8)

    // prevents the power to go over max power
    if (power > Constants.WCD_MAX_POWER){
      power = Constants.WCD_MAX_POWER;
    }else if (power < -Constants.WCD_MAX_POWER){
      power = -Constants.WCD_MAX_POWER;
    }
    
    // applies the power to the drivetrain
    m_differentialDrive.arcadeDrive(turn, power, true);
  }

  // Methods to control gearbox shifter.
  public void shiftLowGear(){
    m_gearShiftLeft.set(Value.kReverse);
    m_gearShiftRight.set(Value.kReverse);
  }

  public void shiftHighGear(){
    m_gearShiftLeft.set(Value.kForward);
    m_gearShiftRight.set(Value.kForward);
  }
}