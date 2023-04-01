package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
// import frc.robot.commands.simpleCommands.ResetAngleMotorEncoder;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class ArmAngle extends SubsystemBase{
    // motors
    private WPI_TalonSRX armAngleMotor;

    private boolean angleLimiter = true; // We want to start with the limit on, so true

    // encoder position variables
    private double armAngleDesiredPosition = 0;
    private double armAngleRawActualPosition;

    // private final SendableChooser<Boolean> angleEncoderReset = new SendableChooser<>();

    public ArmAngle(){
        // TODO PIDS
        armAngleMotor = new WPI_TalonSRX(Constants.ARM_ANGLE_MOTOR_CAN_ID);
        RobotContainer.configureTalonSRX(armAngleMotor, true, FeedbackDevice.CTRE_MagEncoder_Relative, false, true,
        Constants.ARM_ANGLE_MOTOR_KF, Constants.ARM_ANGLE_MOTOR_KP, Constants.ARM_ANGLE_MOTOR_KI, Constants.ARM_ANGLE_MOTOR_KD, Constants.ARM_ANGLE_MOTOR_K_CRUISE_VELOCITY, Constants.ARM_ANGLE_MOTOR_K_ACCELERATION, false);
        //kF: 0, kP: .25, kI: 0.000015, kD: 0, kCruiseVelocity: 0, kAcceleration: 0
        armAngleMotor.setNeutralMode(NeutralMode.Brake);
        armAngleDesiredPosition = armAngleMotor.getSelectedSensorPosition();

        // Angle reset smartdashboard chooser
        // angleEncoderReset.setDefaultOption("False: ", false);
        // angleEncoderReset.addOption("True: ", true);
        // SmartDashboard.putData("Reset Arm Angle Encoder: ", angleEncoderReset);

        // SmartDashboard.putData("RESET ARM ANGLE ENCODER", new ResetAngleMotorEncoder()); 
        
        armAngleMotor.setSelectedSensorPosition(0); // CANCELING OUT ABSOLUTE FUNCTION   
    }

    @Override
    public void periodic() {
        // output encoder position for angleMotor
        armAngleRawActualPosition = armAngleMotor.getSelectedSensorPosition();

        SmartDashboard.putNumber("Actual Arm Abs Angle Motor Position: ", armAngleRawActualPosition);
        // Shuffleboard.getTab("bestTab").add("Actual Arm Angle Motor Position: ", armAngleRawActualPosition);

        // ouput desired arm angle
        SmartDashboard.putNumber("Desired Arm Angle Motor Position: ", armAngleDesiredPosition);
        // Shuffleboard.getTab("bestTab").add("Desired Arm Angle Motor Position ", armAngleDesiredPosition);

        SmartDashboard.putNumber("Arm Angle: ", getArmAngle());
        
        // If reset encoder is selected, it runs encoder reset method
        // if(angleEncoderReset.getSelected()){
        //     resetAngleMotorEncoder();
        // }

        // Display if our limiter is on or not
        SmartDashboard.putBoolean("ARM ANGLE LIMITER", angleLimiter);
    }

    // reset arm angle motor encoder
    public void resetAngleMotorEncoder(){
        armAngleMotor.setSelectedSensorPosition(0);
    }

    public void toggleAngleLimiter(){
        if(angleLimiter){
          System.out.println("DISENGAGING ANGLE LIMITER");
          angleLimiter = false;
        }else if(!angleLimiter){
          System.out.println("ENGAGING ANGLE LIMITER");
          angleLimiter = true;
        }
      }

    // Method that allows movement of the arm angle
    public void setDesiredArmPosition(double joystickAngle){
        // TODO: figure out max and min arm angle positions
       /* 
        * Top encoder pos:
        * Bottom encoder pos:
        */
        // makes sure we are inside acceptable area of motion
        if(angleLimiter){
            if ((armAngleDesiredPosition <= Constants.MAX_ARM_ANGLE_POSITION) && (armAngleDesiredPosition >= Constants.MIN_ARM_ANGLE_POSITION)){
                // Joystick deadzone
                if(Math.abs(joystickAngle) > .1 ){
                    armAngleDesiredPosition = armAngleDesiredPosition + (-joystickAngle * Constants.ARM_ANGLE_MOTOR_SENS); 
                }
                
            // Makes sure desired pos doesn't go above or bellow max
            }else{
                if(armAngleDesiredPosition > Constants.MAX_ARM_ANGLE_POSITION){
                    armAngleDesiredPosition = Constants.MAX_ARM_ANGLE_POSITION;
                }else if(armAngleDesiredPosition < Constants.MIN_ARM_ANGLE_POSITION){
                    armAngleDesiredPosition = Constants.MIN_ARM_ANGLE_POSITION;
                }
            }
        }else{
            if(Math.abs(joystickAngle) > .1 ){
                armAngleDesiredPosition = armAngleDesiredPosition + (-joystickAngle * Constants.ARM_ANGLE_MOTOR_SENS); 
            }
        }
    }
    public void setArmActualPosToDesiredPos(){
        armAngleMotor.set(ControlMode.Position, armAngleDesiredPosition);
    }

    public double getArmAngle(){
        return((0.0064754546 * armAngleRawActualPosition) + 33.34754546);
    }

    // IDLE MODE SETTER METHODS
    public void setArmAngleMotorCoast(){
        armAngleMotor.setNeutralMode(NeutralMode.Coast);
    }
    public void setArmAngleMotorBreak(){
        armAngleMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void setArmAngleTopScoringPos(){
        armAngleDesiredPosition = Constants.ARM_ANGLE_TOP_SCORE_POS;
    }
    public void setArmAngleMiddleScoringPos(){
        armAngleDesiredPosition = Constants.ARM_ANGLE_MIDDLE_SCORE_POS;
    }
    public void setArmAnglePickUpPos(){
        armAngleDesiredPosition = Constants.ARM_ANGLE_PICK_UP_POS;
    }

    // CUSTOM MOVEMENT METHOD
    public void setArmActualPosCustom(double position){
        armAngleMotor.set(ControlMode.Position, position);
    }
}