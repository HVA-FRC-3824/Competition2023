package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class WestCoastDrive extends SubsystemBase {
  private WPI_TalonFX m_leftMaster;
  private WPI_TalonFX m_leftSlave;

  private WPI_TalonFX m_rightMaster;
  private WPI_TalonFX m_rightSlave;

  private DoubleSolenoid m_gearShift;
  private PneumaticsModuleType REVPH;
  private Compressor m_compressor;

  private AHRS m_ahrs; // altitude and heading reference system [AHRS]

  private DifferentialDrive m_differentialDrive;

  // private final DifferentialDriveOdometry m_odometry;

  // private AHRS m_ahrs;
  public WestCoastDrive() {
    // Instantiating drivetrain objects
    m_leftMaster = new WPI_TalonFX(Constants.CHASSIS_LEFT_MASTER_ID);
    RobotContainer.configureTalonFX(m_leftMaster, false, false, 0.0, 0.0, 0.0, 0.0);

    m_leftSlave = new WPI_TalonFX(Constants.CHASSIS_LEFT_SLAVE_ID);
    RobotContainer.configureTalonFX(m_leftSlave, false, false, 0.0, 0.0, 0.0, 0.0);

    m_leftSlave.follow(m_leftMaster);

    m_rightMaster = new WPI_TalonFX(Constants.CHASSIS_RIGHT_MASTER_ID);
    RobotContainer.configureTalonFX(m_rightMaster, false, false, 0.0, 0.0, 0.0, 0.0);

    m_rightSlave = new WPI_TalonFX(Constants.CHASSIS_RIGHT_SLAVE_ID);
    RobotContainer.configureTalonFX(m_rightSlave, false, false, 0.0, 0.0, 0.0, 0.0);

    m_rightSlave.follow(m_rightMaster);

    m_differentialDrive = new DifferentialDrive(m_leftMaster, m_rightMaster);

    m_gearShift = new DoubleSolenoid(REVPH, Constants.CHASSIS_GEARSHIFT_PORT_A, Constants.CHASSIS_GEARSHIFT_PORT_B);
    m_compressor = new Compressor(REVPH);
    // m_differentialDrive.setSafetyEnabled(false);

    // Try to instantiate the navx gyro with exception catch
    try {
      m_ahrs = new AHRS(SPI.Port.kMXP);
    }catch (RuntimeException ex) {
      System.out.println("\nError instantiating navX-MXP:\n" + ex.getMessage() + "\n");
    }

    // Used for tracking robot position.
    // m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(this.getHeading()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run\
    //SmartDashboard.
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void teleopDrive(double power, double turn){
    // Reduces sensitivity of twist for turning.
    turn = turn/1.5;
    if (power > Constants.CHASSIS_MAX_POWER){
      power = Constants.CHASSIS_MAX_POWER;
    }else if (power < -Constants.CHASSIS_MAX_POWER){
      power = -Constants.CHASSIS_MAX_POWER;
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
