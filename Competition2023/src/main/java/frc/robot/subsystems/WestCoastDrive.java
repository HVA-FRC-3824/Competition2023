package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class WestCoastDrive extends SubsystemBase {
  private WPI_TalonSRX m_leftMaster;
  private WPI_TalonSRX m_leftSlave;

  private WPI_TalonSRX m_rightMaster;
  private WPI_TalonSRX m_rightSlave;

  private DoubleSolenoid m_gearShift;
  private PneumaticsModuleType REVPH;
  private Compressor m_compressor;

  private AHRS m_ahrs; // altitude and heading reference system [AHRS]

  private DifferentialDrive m_differentialDrive;

  // private final DifferentialDriveOdometry m_odometry;

  // private AHRS m_ahrs;
  public WestCoastDrive() {
    // Instantiating drivetrain objects
    m_leftMaster = new WPI_TalonSRX(Constants.WCD_LEFT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_leftMaster, false, null, false, false, 0, 0, 0, 0, 0, 0, false);

    m_leftSlave = new WPI_TalonSRX(Constants.WCD_LEFT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_leftSlave, false, null, false, false, 0, 0, 0, 0, 0, 0, false);

    m_leftSlave.follow(m_leftMaster);

    m_rightMaster = new WPI_TalonSRX(Constants.WCD_RIGHT_MASTER_ID);
    RobotContainer.configureTalonSRX(m_rightMaster, false, null, false, false, 0, 0, 0, 0, 0, 0, false);

    m_rightSlave = new WPI_TalonSRX(Constants.WCD_RIGHT_SLAVE_ID);
    RobotContainer.configureTalonSRX(m_rightSlave, false, null, false, false, 0, 0, 0, 0, 0, 0, false);

    m_rightSlave.follow(m_rightMaster);

    m_differentialDrive = new DifferentialDrive(m_leftMaster, m_rightMaster);

    m_gearShift = new DoubleSolenoid(REVPH, Constants.WCD_GEARSHIFT_PORT_A, Constants.WCD_GEARSHIFT_PORT_B);
    m_compressor = new Compressor(REVPH);
    m_differentialDrive.setSafetyEnabled(false);

    // Try to instantiate the navx gyro with exception catch
    try {
      m_ahrs = new AHRS(SPI.Port.kMXP);
    }catch (RuntimeException ex) {
      System.out.println("\nError instantiating navX-MXP:\n" + ex.getMessage() + "\n");
    }

    m_compressor.enableHybrid(80, 120);
    
    // Used for tracking robot position.
    // m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(this.getHeading()));
    //                                                                   this.getHeading() is deprecated, replaced by needing xtra encoders
    // m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_ahrs.getAngle()) , m_leftMaster.idk, m_rightMaster.idk);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("System Pressure", m_compressor.getPressure());
    SmartDashboard.updateValues();
    // Field2d
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void teleopDrive(double power, double turn){
    // Reduces sensitivity of twist for turning.
    turn = turn/1.5;

    if (power > Constants.WCD_MAX_POWER){
      power = Constants.WCD_MAX_POWER;
    }else if (power < -Constants.WCD_MAX_POWER){
      power = -Constants.WCD_MAX_POWER;
    }
    
    m_differentialDrive.arcadeDrive(power, turn, true);
  }

  // Methods to control gearbox shifter.
  public void shiftLowGear(){
    m_gearShift.set(Value.kReverse);
  }
  public void shiftHighGear(){
    m_gearShift.set(Value.kForward);
  }
}