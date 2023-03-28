package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import com.kauailabs.navx.frc.AHRS;

public class SwerveDrive extends SubsystemBase{
  private AHRS ahrs;

  private WPI_TalonFX angleMotorFrontRight;
  private WPI_TalonSRX frontRightAbsEncoder;
  private WPI_TalonFX driveMotorFrontRight;
  
  private WPI_TalonFX angleMotorFrontLeft;
  private WPI_TalonSRX frontLeftAbsEncoder;
  private WPI_TalonFX driveMotorFrontLeft;

  private WPI_TalonFX angleMotorBackLeft;
  private WPI_TalonSRX backLeftAbsEncoder;
  private WPI_TalonFX driveMotorBackLeft;

  private WPI_TalonFX angleMotorBackRight;
  private WPI_TalonSRX backRightAbsEncoder;
  private WPI_TalonFX driveMotorBackRight;

  private double swervePower;

  private boolean wheelsZeroed = false;
  private boolean robotCentric = false;
  private boolean powerModeScore = false;
  private final SendableChooser<Boolean> gyroReset = new SendableChooser<>();

  public boolean endMove = false;

  /* Array of values for calculating swerve angle & speed
   * {VX, VY, Speed, Desired Angle, Current Angle, Offset} */
  public double [] frontRight = {0, 0, 0, 0, 0, 0};
  public double [] frontLeft = {0, 0, 0, 0, 0, 0};
  public double [] backLeft = {0, 0, 0, 0, 0, 0};
  public double [] backRight = {0, 0, 0, 0, 0, 0};

  public SwerveDrive(){   
    // Try to instantiate the NavX Gyro with exception catch
    try{
      ahrs = new AHRS(SPI.Port.kMXP);
    }catch (RuntimeException ex){
      System.out.println("\nError instantiating navX-MXP:\n" + ex.getMessage() + "\n");
    }

    // Configure drivetrain motors
    angleMotorFrontRight = new WPI_TalonFX(Constants.FRONT_RIGHT_ANGLE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(angleMotorFrontRight, false, FeedbackDevice.IntegratedSensor, 
      false, 0.0, Constants.K_CHASSIS_RIGHT_ANGLE_P, Constants.K_CHASSIS_RIGHT_ANGLE_I, 
      Constants.K_CHASSIS_RIGHT_ANGLE_D);
    driveMotorFrontRight = new WPI_TalonFX(Constants.FRONT_RIGHT_DRIVE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(driveMotorFrontRight, false, FeedbackDevice.IntegratedSensor,
      false, 0.0, 0.0, 0.0, 0.0);
    frontRightAbsEncoder = new WPI_TalonSRX(Constants.FRONT_RIGHT_ABSOLUTE_ENCODER_CAN_ID);
      RobotContainer.configureTalonSRX(frontRightAbsEncoder, true, FeedbackDevice.CTRE_MagEncoder_Absolute,
      false, false, 0, 0, 0, 0, 0, 0, false);

    angleMotorFrontLeft = new WPI_TalonFX(Constants.FRONT_LEFT_ANGLE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(angleMotorFrontLeft, false, FeedbackDevice.IntegratedSensor,
      false, 0.0, Constants.K_CHASSIS_LEFT_ANGLE_P, Constants.K_CHASSIS_LEFT_ANGLE_I, 
      Constants.K_CHASSIS_LEFT_ANGLE_D);
    driveMotorFrontLeft = new WPI_TalonFX(Constants.FRONT_LEFT_DRIVE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(driveMotorFrontLeft, false, FeedbackDevice.IntegratedSensor,
      false, 0.0, 0.0, 0.0, 0.0);
    frontLeftAbsEncoder = new WPI_TalonSRX(Constants.FRONT_LEFT_ABSOLUTE_ENCODER_CAN_ID);
      RobotContainer.configureTalonSRX(frontLeftAbsEncoder, true, FeedbackDevice.CTRE_MagEncoder_Absolute,
      false, false, 0, 0, 0, 0, 0, 0, false);
    
    angleMotorBackLeft = new WPI_TalonFX(Constants.BACK_LEFT_ANGLE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(angleMotorBackLeft, false, FeedbackDevice.IntegratedSensor,
      false, 0.0, Constants.K_CHASSIS_LEFT_ANGLE_P, Constants.K_CHASSIS_LEFT_ANGLE_I, 
      Constants.K_CHASSIS_LEFT_ANGLE_D);
    driveMotorBackLeft = new WPI_TalonFX(Constants.BACK_LEFT_DRIVE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(driveMotorBackLeft, false, FeedbackDevice.IntegratedSensor, 
      false, 0.0, 0.0, 0.0, 0.0);
    backLeftAbsEncoder = new WPI_TalonSRX(Constants.BACK_LEFT_ABSOLUTE_ENCODER_CAN_ID);
      RobotContainer.configureTalonSRX(backLeftAbsEncoder, true, FeedbackDevice.CTRE_MagEncoder_Absolute,
      false, false, 0, 0, 0, 0, 0, 0, false);

    angleMotorBackRight = new WPI_TalonFX(Constants.BACK_RIGHT_ANGLE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(angleMotorBackRight, false, FeedbackDevice.IntegratedSensor, 
      false, 0.0, Constants.K_CHASSIS_RIGHT_ANGLE_P, Constants.K_CHASSIS_RIGHT_ANGLE_I, 
      Constants.K_CHASSIS_RIGHT_ANGLE_D);
    driveMotorBackRight = new WPI_TalonFX(Constants.BACK_RIGHT_DRIVE_MOTOR_CAN_ID);
      RobotContainer.configureTalonFX(driveMotorBackRight, false, FeedbackDevice.IntegratedSensor, 
      false, 0.0, 0.0, 0.0, 0.0);
    backRightAbsEncoder = new WPI_TalonSRX(Constants.BACK_RIGHT_ABSOLUTE_ENCODER_CAN_ID);
      RobotContainer.configureTalonSRX(backRightAbsEncoder, true, FeedbackDevice.CTRE_MagEncoder_Absolute,
      false, false, 0, 0, 0, 0, 0, 0, false);

    swervePower = Constants.SWERVE_POWER;

    gyroReset.setDefaultOption("RESET GYRO FALSE", false);
    gyroReset.addOption("RESET GYRO TRUE", true);
    SmartDashboard.putData("RESET GRYO", gyroReset);
    /* Shuffleboard.getTab("bestTab").add("RESET GRYO", gyroReset); */
    
    // Button that calls the reset field forward position command
    // SmartDashboard.putData("RESET GYRO", new ResetFieldForwardPositionGyro());
  }

  @Override
  public void periodic(){
    // Update drivetrain information on SmartDashboard for testing.
    SmartDashboard.putNumber("FR Angle Motor Pos in Rel Degrees", angleMotorFrontRight.getSelectedSensorPosition() * 360 / (Constants.FALCON_500_ENCODER_COUNTS_PER_REV * 12));
    SmartDashboard.putNumber("FL Angle Motor Pos in Rel Degrees", angleMotorFrontLeft.getSelectedSensorPosition()* 360 / (Constants.FALCON_500_ENCODER_COUNTS_PER_REV * 12));
    SmartDashboard.putNumber("BR Angle Motor Pos in Rel Degrees", angleMotorBackRight.getSelectedSensorPosition() * 360 / (Constants.FALCON_500_ENCODER_COUNTS_PER_REV * 12));
    SmartDashboard.putNumber("BL Angle Motor Pos in Rel Degrees", angleMotorBackLeft.getSelectedSensorPosition() * 360 / (Constants.FALCON_500_ENCODER_COUNTS_PER_REV * 12));

    SmartDashboard.putNumber("FR Abs Pos in Rel Degrees", frontRightAbsEncoder.getSelectedSensorPosition() * 360/ Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV);
    SmartDashboard.putNumber("FL Abs Pos in Rel Degrees", frontLeftAbsEncoder.getSelectedSensorPosition() * 360/ Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV);
    SmartDashboard.putNumber("BR Abs Pos in Rel Degrees", backRightAbsEncoder.getSelectedSensorPosition() * 360/ Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV);
    SmartDashboard.putNumber("BL Abs Pos in Rel Degrees", backLeftAbsEncoder.getSelectedSensorPosition() * 360/ Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV);

    SmartDashboard.putBoolean("Swerve Power score Mode: ", powerModeScore);
    /* Shuffleboard.getTab("bestTab").add("Swerve Power score Mode: ", powerModeScore); */
    
    SmartDashboard.putNumber("Swerve Current Power", swervePower);
    /* Shuffleboard.getTag("bestTag").add("Swerve Current Power", swervePower); */
    
    SmartDashboard.putNumber("Gyro heading", ahrs.getAngle());
    /* Shuffleboard.getTab("bestTag").add("Gyro heading", ahrs.getAngle()); */

    SmartDashboard.putNumber("Gyro pitch", getGyroPitch());

    if(gyroReset.getSelected()){
      resetFieldCentricity();
    }
  }

  public void resetFieldCentricity(){
    ahrs.reset();
  }

  // (x1 joystick input left/right, y1 joystick input front/back, x2 joystick input turn)
  public void convertSwerveValues (double x1, double y1, double x2){
    // Width and length between center of wheels
    double w = 17; // 21.5
    double l = 29; // 25
    
    // Width and length relative ratios
    double wr;
    double lr;

    // Input velocities and turn
    double vX = 0;
    double vY = 0;
    double turn = 0;

    // Simplification for adding turn and strafe velocity for each wheel
    double a;
    double b;
    double c;
    double d;

    // Set deadzone & dampening turn, more dampened if score power mode is enabled
    if (Math.abs(x2) > 0.13){
      if(powerModeScore){
        turn = x2 * 0.5;
      }else{
        turn = x2 * 0.9;
      }
    }    

    // Set length & width proportional to chassis size by using circle of radius 1
    // turn_angle gets tan of length & width (in radians)
    double turn_angle = Math.atan2(l, w);
    wr = Math.cos(turn_angle); // x, cos
    lr = Math.sin(turn_angle); // y, sin

    // Set deadzone & adjusting drive input to controller (negative forward)
    if (Math.abs(x1) > 0.1){
      vX = x1;
    }
    if (Math.abs(y1) > 0.1){
      vY = -y1;
    }

    // Set gyro to current angle of sensor  
    double gyro_current = ahrs.getYaw();

    // Set fixed driving directions
    // Set (input) r = magnitude, square root of x^2 * y^2
    double r = Math.sqrt(vX * vX + vY * vY);
    // strafe_angle: robot's current heading
    double strafe_angle = Math.atan2(vY, vX);


    // Set strafe_angle to NavX reported angle
    if (!robotCentric){
      strafe_angle += (gyro_current) / 360 * 2 * Math.PI;
    }

    // Set velocity to desired velocity (r) and direction (strafe_angle)
    vX = r * Math.cos(strafe_angle);
    vY = r * Math.sin(strafe_angle);

    // a/b back/front wheels, c/d left/right wheels
    // a & b x vectors, c & d y vectors
    a = vX - turn * lr;
    b = vX + turn * lr;
    c = vY - turn * wr;
    d = vY + turn * wr;

    // X & y velocities for each wheel (not sent to wheels)
    // [vx, vy, speed, angle, last angle, offset];
    // [0] = .5, [1]= 0
    frontRight[0] = b;
    frontRight[1] = c;
    frontLeft[0] = b;
    frontLeft[1] = d;
    backLeft[0] = a;
    backLeft[1] = d;
    backRight[0] = a;
    backRight[1] = c;

    // Set speed of each wheel with their individual x & y velocities
    frontRight[2] = Math.sqrt(Math.abs(b * b + c * c));
    frontLeft[2] = Math.sqrt(Math.abs(b * b + d * d));
    backLeft[2] = Math.sqrt(Math.abs(a * a + d * d));
    backRight[2] = Math.sqrt(Math.abs(a * a + c * c));

    // Adjust for motor max speed limit & set all wheels to same speed
    double highestSpeed = Math.max(Math.max(Math.max(frontRight[2], frontLeft[2]), backLeft[2]), backRight[2]);
    if (highestSpeed > 1){
      frontRight[2] = frontRight[2] / highestSpeed;
      frontLeft[2] = frontLeft[2] / highestSpeed;
      backLeft[2] = backLeft[2] / highestSpeed;
      backRight[2] = backRight[2] / highestSpeed;
    }

    // Save current angle as previous angle for offset creation
    frontRight[4] = frontRight[3];
    frontLeft[4] = frontLeft[3];
    backLeft[4] = backLeft[3];
    backRight[4] = backRight[3];

    // Update current angle
    if (!(vX == 0 && vY == 0 && turn == 0)){
      // Find angle of each wheel based on velocities
      frontRight[3] = Math.atan2(c, b) - Math.PI / 2;
      frontLeft[3] = Math.atan2(d, b) - Math.PI / 2;
      backLeft[3] = Math.atan2(d, a) - Math.PI / 2;
      backRight[3] = Math.atan2(c, a) - Math.PI / 2;
    }

    // When desired heading is > 180 from current, make wheels turn the opposite direction
    if (Math.abs(frontRight[4] - frontRight[3]) > Math.PI && frontRight[4] < frontRight[3]){
      frontRight[5] -= 2 * Math.PI;}
    if (Math.abs(frontRight[4] - frontRight[3]) > Math.PI && frontRight[4] > frontRight[3]){
      frontRight[5] += 2 * Math.PI;}
    if (Math.abs(frontLeft[4] - frontLeft[3]) > Math.PI && frontLeft[4] < frontLeft[3]){
      frontLeft[5] -= 2 * Math.PI;}
    if (Math.abs(frontLeft[4] - frontLeft[3]) > Math.PI && frontLeft[4] > frontLeft[3]){
      frontLeft[5] += 2 * Math.PI;}

    if (Math.abs(backLeft[4] - backLeft[3]) > Math.PI && backLeft[4] < backLeft[3]){
      backLeft[5] -= 2 * Math.PI;}
    if (Math.abs(backLeft[4] - backLeft[3]) > Math.PI && backLeft[4] > backLeft[3]){
      backLeft[5] += 2 * Math.PI;}
    if (Math.abs(backRight[4] - backRight[3]) > Math.PI && backRight[4] < backRight[3]){
      backRight[5] -= 2 * Math.PI;}
    if (Math.abs(backRight[4] - backRight[3]) > Math.PI && backRight[4] > backRight[3]){
      backRight[5] += 2 * Math.PI;}

    // Send values to motors, angle variable math is percentage of chassis circle
    drive(driveMotorFrontRight, angleMotorFrontRight, -frontRight[2], 
        -(frontRight[3] + frontRight[5])  / (Math.PI * 2) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
    drive(driveMotorFrontLeft, angleMotorFrontLeft, frontLeft[2], 
        -(frontLeft[3] + frontLeft[5]) / (Math.PI * 2) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
    drive(driveMotorBackLeft, angleMotorBackLeft, backLeft[2], 
        -(backLeft[3] + backLeft[5])  / (Math.PI * 2) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
    drive(driveMotorBackRight, angleMotorBackRight, backRight[2], 
        -(backRight[3] + backRight[5]) / (Math.PI * 2) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
  }

  // Moves each wheel (1 speed + 1 angle motor)
  public void drive (WPI_TalonFX driveMotor, WPI_TalonFX angleMotor, double speed, double angle){
    // Set speed motor position
    driveMotor.set(speed * swervePower);

    // Set angle motor position + print values
    angleMotor.set(TalonFXControlMode.Position, angle);

    SmartDashboard.putNumber("Angle", speed);
    /*
    Shuffleboard.getTab("bestTab")
      .add("Angle, speed");
    */
  }

  /* Method to put wheels in X positions for locking on the charging pad
   * degreesOffStraight is only applicable in one direction of motion, so name isn't completely accurate */
  public void xLockWheels(){
    System.out.println("RUNNING X WHEELS METHOD");
    // FRONT LEFT WHEEL
    double desiredPositionFrontLeft;
    double rotationsFL = angleMotorFrontLeft.getSelectedSensorPosition() / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    double degreesOffStraightFL = (rotationsFL % 1) * 360;
    double overDifferenceFL = (135 - degreesOffStraightFL)/360; // 225
    double underDifferenceFL = (495 - degreesOffStraightFL)/360; // -135
    if(Math.abs(overDifferenceFL) < Math.abs(underDifferenceFL)){
      desiredPositionFrontLeft = (rotationsFL + overDifferenceFL) *  Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }else{
      desiredPositionFrontLeft = (rotationsFL + underDifferenceFL) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }
    angleMotorFrontLeft.set(TalonFXControlMode.Position, desiredPositionFrontLeft);

    // FRONT RIGHT WHEEL
    double desiredPositionFrontRight;
    double rotationsFR = angleMotorFrontRight.getSelectedSensorPosition() / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    double degreesOffStraightFR = (rotationsFR % 1) * 360;
    double overDifferenceFR = (225 - degreesOffStraightFR)/360; // 135
    double underDifferenceFR = (-135 - degreesOffStraightFR)/360; // 495
    if(Math.abs(overDifferenceFR) < Math.abs(underDifferenceFR)){
      desiredPositionFrontRight = (rotationsFR + overDifferenceFR) *  Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }else{
      desiredPositionFrontRight = (rotationsFR + underDifferenceFR) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }
    angleMotorFrontRight.set(TalonFXControlMode.Position, desiredPositionFrontRight);

    // BACK LEFT WHEEL
    double desiredPositionBackLeft;
    double rotationsBL = angleMotorBackLeft.getSelectedSensorPosition() / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    double degreesOffStraightBL = (rotationsBL % 1) * 360;
    double overDifferenceBL = (45 - degreesOffStraightBL)/360; // 315
    double underDifferenceBL = (405 - degreesOffStraightBL)/360; // -45
    if(Math.abs(overDifferenceBL) < Math.abs(underDifferenceBL)){
      desiredPositionBackLeft = (rotationsBL + overDifferenceBL) *  Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }else{
      desiredPositionBackLeft = (rotationsBL + underDifferenceBL) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }
    angleMotorBackLeft.set(TalonFXControlMode.Position, desiredPositionBackLeft);

    // BACK RIGHT WHEEL
    double desiredPositionBackRight;
    double rotationsBR = angleMotorBackRight.getSelectedSensorPosition() / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    double degreesOffStraightBR = (rotationsBR % 1) * 360;
    double overDifferenceBR = (315 - degreesOffStraightBR)/360; // 45
    double underDifferenceBR = (-45 - degreesOffStraightBR)/360; // 405
    if(Math.abs(overDifferenceBR) < Math.abs(underDifferenceBR)){
      desiredPositionBackRight = (rotationsBR + overDifferenceBR) *  Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }else{
      desiredPositionBackRight = (rotationsBR + underDifferenceBR) * Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION;
    }
    angleMotorBackRight.set(TalonFXControlMode.Position, desiredPositionBackRight);
    System.out.println("Back right current: " + angleMotorBackRight);
    System.out.println("Back right desired: " + desiredPositionBackRight);
  }

  public WPI_TalonFX getFLDrive(){
    return(driveMotorFrontLeft);
  }
  public WPI_TalonFX getFRDrive(){
    return(driveMotorFrontRight);
  }
  public WPI_TalonFX getBLDrive(){
    return(driveMotorBackLeft);
  }
  public WPI_TalonFX getBRDrive(){
    return(driveMotorBackRight);
  }

  // Reset gyro to zero the heading of the robot.
  public void zeroHeading(){
    ahrs.reset();
    ahrs.setAngleAdjustment(0.0); //offset
  }

  // Set gyro to a certain heading
  public void setHeading(double heading){
    ahrs.setAngleAdjustment(heading);
  }

  /**
   * Get gyro heading between -180 to 180.
   * Uses Math.IEEEremainder to get range of -180 to 180 --> dividend - (divisor * Math.Round(dividend / divisor)).
   * @return the robot's heading in degrees.
   */
  public double getHeading(){
    return Math.IEEEremainder(ahrs.getAngle(), 360) * (Constants.K_SWERVE_GYRO_REVERSED ? -1.0 : 1.0);
  }

  // Toggle drive between field centric and robot centric
  public void toggleDriveCentricity(){
    robotCentric = !robotCentric;
  }

  // Toggle drive between normal power and scoring/slower power, ON PRESSED
  public void toggleDrivePower(){
    if(powerModeScore){
      swervePower = Constants.SWERVE_POWER;
      powerModeScore = false;
    }else if(!powerModeScore){
      swervePower = Constants.SWERVE_SCORE_POWER;
      powerModeScore = true;
    }
  }

  // Methods for toggling between normal power and juke power, WHEN HELD
  public void jukeSpeedMode(){
    swervePower = Constants.SWERVE_JUKE_POWER;
  }
  public void normalSpeedMode(){
    if(!powerModeScore){
      swervePower = Constants.SWERVE_POWER;
    }else{
      swervePower = Constants.SWERVE_SCORE_POWER;
    }
  }

  public void moveForward(){
    endMove = false;
    convertSwerveValues(0, -.4, 0);
  }
  public void moveBack(){
    endMove = false;
    convertSwerveValues(0, .4, 0);
  }
  public void moveLeft(){
    endMove = false;
    convertSwerveValues(.4, 0, 0);
  }
  public void moveRight(){
    endMove = false;
    convertSwerveValues(-.4, 0, 0);
  }
  public void endMove(){
    endMove = true;
  }

  // public void zeroWheelsWithABSEncoders(){
  //   wheelsZeroed = true;
  //   System.out.println("Wheel zeroing method running... ");
  //   angleMotorFrontLeft.set(TalonFXControlMode.Position, (Constants.SWERVE_FRONT_LEFT_ABS_FORWARD_POSITION_RSU / Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV) * Constants.FALCON_500_ENCODER_COUNTS_PER_REV * 12);
  //   angleMotorFrontLeft.setSelectedSensorPosition(0);
  //   angleMotorFrontRight.set(TalonFXControlMode.Position, (Constants.SWERVE_FRONT_RIGHT_ABS_FORWARD_POSITION_RSU / Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV) * Constants.FALCON_500_ENCODER_COUNTS_PER_REV * 12);
  //   angleMotorFrontRight.setSelectedSensorPosition(0);
  //   angleMotorBackLeft.set(TalonFXControlMode.Position, (Constants.SWERVE_BACK_LEFT_ABS_FORWARD_POSITION_RSU / Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV) * Constants.FALCON_500_ENCODER_COUNTS_PER_REV * 12);
  //   angleMotorBackLeft.setSelectedSensorPosition(0);
  //   angleMotorBackRight.set(TalonFXControlMode.Position, (Constants.SWERVE_BACK_RIGHT_ABS_FORWARD_POSITION_RSU / Constants.CTRE_MAG_ENCODER_COUNTS_PER_REV) * Constants.FALCON_500_ENCODER_COUNTS_PER_REV *12);
  //   angleMotorBackRight.setSelectedSensorPosition(0);
  // }

  public void setIdleModeBrake(){
    driveMotorBackLeft.setNeutralMode(NeutralMode.Brake);
    driveMotorBackRight.setNeutralMode(NeutralMode.Brake);
    driveMotorFrontLeft.setNeutralMode(NeutralMode.Brake);
    driveMotorFrontRight.setNeutralMode(NeutralMode.Brake);
    angleMotorFrontLeft.setNeutralMode(NeutralMode.Brake);
    angleMotorFrontRight.setNeutralMode(NeutralMode.Brake);
    angleMotorBackLeft.setNeutralMode(NeutralMode.Brake);
    angleMotorBackRight.setNeutralMode(NeutralMode.Brake);
  }

  public void setIdleModeCoast(){

  }

  /* AUTONOMOUS METHODS */

  // Method to return the pitch of the Gryo, roll because the gyro is mounted sideways, used in autobalance
  public float getGyroPitch(){
    return(ahrs.getRoll() + 1.4f);
  }

  public boolean approachAndClimbOverChargeStationForward(){
    System.out.println("RUNNING APPROACH AND CLIMB OVER CHARGE STATION FORWARD COMMAND... ");
    // moves fast until on robot is on charging pad
    while(getGyroPitch() <  3 ){
      convertSwerveValues(0, -0.6, 0);
      System.out.println("while loop 1");
    }
    // moves slower while robot is going up charging pad
    while(getGyroPitch() > 0){
      convertSwerveValues(0, -0.4, 0);
      System.out.println("while loop 2");
    }
    // moves even slower while robot is going down the charging pad
    while(getGyroPitch() < -0.01){
      convertSwerveValues(0, -0.3, 0);
      System.out.println("while loop 3");
    }
    return(true);
  }

  public void zeroGyro(){
    ahrs.calibrate();
  }

  public boolean approachChargeStationBackward(){
    // move fast backwards toward charging station
    while(getGyroPitch() >  -7 ){
      convertSwerveValues(0, 0.5, 0);
    }
    return(true);
  }
}