// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// import com.ctre.phoenix.sensors.CANCoder; // ABS ENC

public class SwerveDrive extends SubsystemBase{
  // Declare chassis objects
  private AHRS m_ahrs;

  private WPI_TalonFX m_angleMotorFrontRight;
  private WPI_TalonFX m_speedMotorFrontRight;
  
  private WPI_TalonFX m_angleMotorFrontLeft;
  private WPI_TalonFX m_speedMotorFrontLeft;

  private WPI_TalonFX m_angleMotorBackLeft;
  private WPI_TalonFX m_speedMotorBackLeft;

  private WPI_TalonFX m_angleMotorBackRight;
  private WPI_TalonFX m_speedMotorBackRight;

  private double swervePower;
  private double defenseSwervePower = 0.9;
  
  private boolean isDefending = false;
  private boolean robotCentric = false;

  // public CANCoder AbsEncoderFR; // ABS ENC
  // public CANCoder AbsEncoderFL; // ABS ENC
  // public CANCoder AbsEncoderBL; // ABS ENC
  // public CANCoder AbsEncoderBR; // ABS ENC

  /* Array of values for calculating swerve angle & speed
  *  {VX, VY, Speed, Angle, Previous Angle, Offset} */
  public double [] frontRight = {0, 0, 0, 0, 0, 0};
  public double [] frontLeft = {0, 0, 0, 0, 0, 0};
  public double [] backLeft = {0, 0, 0, 0, 0, 0};
  public double [] backRight = {0, 0, 0, 0, 0, 0};

  public SwerveDrive(){   
    //Try to instantiate the NavX Gyro with exception catch
    try{
      m_ahrs = new AHRS(SPI.Port.kMXP);
    }catch (RuntimeException ex){
      System.out.println("\nError instantiating navX-MXP:\n" + ex.getMessage() + "\n");
    }

    // AbsEncoderFR = new CANCoder(Constants.ABS_ENCODER_FR_ID); // ABS ENC
    // AbsEncoderFL = new CANCoder(Constants.ABS_ENCODER_FL_ID); // ABS ENC
    // AbsEncoderBL = new CANCoder(Constants.ABS_ENCODER_BL_ID); // ABS ENC
    // AbsEncoderBR = new CANCoder(Constants.ABS_ENCODER_BR_ID); // ABS ENC

    //Configure drivetrain motors
    m_angleMotorFrontRight = new WPI_TalonFX(Constants.FRONT_RIGHT_ANGLE_MOTOR_ID);
      RobotContainer.configureTalonFX(m_angleMotorFrontRight, false, false, 0.0, Constants.K_CHASSIS_RIGHT_ANGLE_P, Constants.K_CHASSIS_RIGHT_ANGLE_I, Constants.K_CHASSIS_RIGHT_ANGLE_D);
    m_speedMotorFrontRight = new WPI_TalonFX(Constants.FRONT_RIGHT_SPEED_MOTOR_ID);
      RobotContainer.configureTalonFX(m_speedMotorFrontRight, false, false, 0.0, 0.0, 0.0, 0.0);

    m_angleMotorFrontLeft = new WPI_TalonFX(Constants.FRONT_LEFT_ANGLE_MOTOR_ID);
      RobotContainer.configureTalonFX(m_angleMotorFrontLeft, false, false, 0.0, Constants.K_CHASSIS_LEFT_ANGLE_P, Constants.K_CHASSIS_LEFT_ANGLE_I, Constants.K_CHASSIS_LEFT_ANGLE_D);
    m_speedMotorFrontLeft = new WPI_TalonFX(Constants.FRONT_LEFT_SPEED_MOTOR_ID);
      RobotContainer.configureTalonFX(m_speedMotorFrontLeft, false, false, 0.0, 0.0, 0.0, 0.0);
    
    m_angleMotorBackLeft = new WPI_TalonFX(Constants.BACK_LEFT_ANGLE_MOTOR_ID);
      RobotContainer.configureTalonFX(m_angleMotorBackLeft, false, false, 0.0, Constants.K_CHASSIS_LEFT_ANGLE_P, Constants.K_CHASSIS_LEFT_ANGLE_I, Constants.K_CHASSIS_LEFT_ANGLE_D);
    m_speedMotorBackLeft = new WPI_TalonFX(Constants.BACK_LEFT_SPEED_MOTOR_ID);
      RobotContainer.configureTalonFX(m_speedMotorBackLeft, false, false, 0.0, 0.0, 0.0, 0.0);

    m_angleMotorBackRight = new WPI_TalonFX(Constants.BACK_RIGHT_ANGLE_MOTOR_ID);
      RobotContainer.configureTalonFX(m_angleMotorBackRight, false, false, 0.0, Constants.K_CHASSIS_RIGHT_ANGLE_P, Constants.K_CHASSIS_RIGHT_ANGLE_I, Constants.K_CHASSIS_RIGHT_ANGLE_D);
    m_speedMotorBackRight = new WPI_TalonFX(Constants.BACK_RIGHT_SPEED_MOTOR_ID);
      RobotContainer.configureTalonFX(m_speedMotorBackRight, false, false, 0.0, 0.0, 0.0, 0.0);

    swervePower = Constants.SWERVE_POWER;
  }

  // This method will be called once per scheduler run.
  @Override
  public void periodic(){
    // Update drivetrain information on SmartDashboard for testing.
    SmartDashboard.putNumber("FR Angle Motor Pos in Rel Degrees", m_angleMotorFrontRight.getSelectedSensorPosition() * 360/ Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION);
    SmartDashboard.putNumber("FL Angle Motor Pos in Rel Degrees", m_angleMotorFrontLeft.getSelectedSensorPosition() * 360/ Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION);
    SmartDashboard.putNumber("BR Angle Motor Pos in Rel Degrees", m_angleMotorBackRight.getSelectedSensorPosition() * 360/ Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION);
    SmartDashboard.putNumber("BL Angle Motor Pos in Rel Degrees", m_angleMotorBackLeft.getSelectedSensorPosition() * 360/ Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION);
  }

  //Gets motors for use in commands
  public WPI_TalonFX getMotorFR (){
    return m_angleMotorFrontRight;
  }
  public WPI_TalonFX getMotorFL (){
    return m_angleMotorFrontLeft;
  }
  public WPI_TalonFX getMotorBL (){
    return m_angleMotorBackLeft;
  }
  public WPI_TalonFX getMotorBR (){
    return m_angleMotorBackRight;
  }

  // (x1 joystick input left/right, y1 joystick input front/back, x2 joystick input turn)
  public void convertSwerveValues (double x1, double y1, double x2){
    // Width and length between center of wheels
    double w = 21.5;
    double l = 25;
    
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

    // Set deadzone & dampening turn
    if (Math.abs(x2) > 0.2){
      turn = x2 *0.7;
    }    

    // Set length & width proportional to chassis size by using circle of radius 1
    // turn_angle gets tan of length & width (in radians)
    double turn_angle = Math.atan2(l, w);
    wr = Math.cos(turn_angle); //x, cos
    lr = Math.sin(turn_angle); //y, sin

    // Set deadzone & adjusting drive input to controller (negative forward)
    if (Math.abs(x1) > 0.15){
      vX = x1;
    }
    if (Math.abs(y1) > 0.15){
      vY = -y1;
    }

    // Set gyro to current angle of sensor  
    double gyro_current = m_ahrs.getYaw();

    // Set fixed driving directions
    // Set (input) r = magnitude, square root of x^2 * y^2
    double r = Math.sqrt(vX * vX + vY * vY);
    // strafe_angle: robot's current heading
    double strafe_angle = Math.atan2(vY, vX);


    // Set strafe_angle to NavX reported angle
    if (robotCentric == false){
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

    // When desired heading is >180 from current, make wheels turn the opposite direction
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
    drive(m_speedMotorFrontRight, m_angleMotorFrontRight, -frontRight[2], -(frontRight[3] + frontRight[5])  / (Math.PI * 2) * Constants.WHEEL_MOTOR_TICKS_PER_REVOLUTION);
    drive(m_speedMotorFrontLeft, m_angleMotorFrontLeft, frontLeft[2], -(frontLeft[3] + frontLeft[5]) / (Math.PI * 2) * Constants.WHEEL_MOTOR_TICKS_PER_REVOLUTION);
    drive(m_speedMotorBackLeft, m_angleMotorBackLeft, backLeft[2], -(backLeft[3] + backLeft[5])  / (Math.PI * 2) * Constants.WHEEL_MOTOR_TICKS_PER_REVOLUTION);
    drive(m_speedMotorBackRight, m_angleMotorBackRight, backRight[2], -(backRight[3] + backRight[5]) / (Math.PI * 2) * Constants.WHEEL_MOTOR_TICKS_PER_REVOLUTION);
  }


  // Moves each wheel (1 speed + 1 angle motor)
  public void drive (WPI_TalonFX speedMotor, WPI_TalonFX angleMotor, double speed, double angle){
    // Set speed motor position
    speedMotor.set(speed * swervePower);

    // Set angle motor position + print values
    angleMotor.set(TalonFXControlMode.Position, angle); //0

    // SmartDashboard.putNumber("Angle", angle);
  }

  // Not used: work in progress 3/24 // ABS ENC
  // Sets motors to external encoder values, permanent forward direction
  public void setMotorPosition(){
    // m_angleMotorFrontRight.setSelectedSensorPosition(AbsEncoderFR.getAbsolutePosition() * Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION / 360, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS); // ABS ENC
    // m_angleMotorFrontLeft.setSelectedSensorPosition(AbsEncoderFL.getAbsolutePosition() * Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION / 360, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS); // ABS ENC
    // m_angleMotorBackLeft.setSelectedSensorPosition(AbsEncoderBL.getAbsolutePosition() * Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION / 360, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS); // ABS ENC
    // m_angleMotorBackRight.setSelectedSensorPosition(AbsEncoderBR.getAbsolutePosition() * Constants.K_SWERVE_ENCODER_TICKS_PER_REVOLUTION / 360, Constants.K_PID_LOOP_IDX, Constants.K_TIMEOUT_MS); // ABS ENC
  }

  // Reset gyro to zero the heading of the robot.
  public void zeroHeading(){
    m_ahrs.reset();
    m_ahrs.setAngleAdjustment(0.0); //offset
  }

  // Set gyro to a certain heading
  public void setHeading(double heading){
    m_ahrs.setAngleAdjustment(heading);
  }

  /**
   * Get gyro heading between -180 to 180.
   * Uses Math.IEEEremainder to get range of -180 to 180 --> dividend - (divisor * Math.Round(dividend / divisor)).
   * @return the robot's heading in degrees.
   */
  public double getHeading(){
    return Math.IEEEremainder(m_ahrs.getAngle(), 360) * (Constants.K_SWERVE_GYRO_REVERSED ? -1.0 : 1.0);
  }

  //Toggle drive between field centric and robot centric
  public void toggleDriveCentricity(){
    robotCentric = !robotCentric;
  }

  public void toggleDefenseMode(){
    if(swervePower == Constants.SWERVE_POWER){
      swervePower = defenseSwervePower;
      isDefending = true;
    }else if(swervePower == defenseSwervePower){
      swervePower = Constants.SWERVE_POWER;
      isDefending = false;
    }
  }

  public boolean getDefenseStatus(){
    return isDefending;
  }
}