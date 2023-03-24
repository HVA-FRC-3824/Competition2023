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

    // encoder position variables
    private double armAngleDesiredPosition = 0;
    private double armAngleRawActualPosition;

    // private final SendableChooser<Boolean> angleEncoderReset = new SendableChooser<>();

    public ArmAngle(){
        // TODO PIDS
        armAngleMotor = new WPI_TalonSRX(Constants.ARM_ANGLE_MOTOR_CAN_ID);
        RobotContainer.configureTalonSRX(armAngleMotor, true, FeedbackDevice.CTRE_MagEncoder_Absolute, false, true,
        Constants.ARM_ANGLE_MOTOR_KF, Constants.ARM_ANGLE_MOTOR_KP, Constants.ARM_ANGLE_MOTOR_KI, Constants.ARM_ANGLE_MOTOR_KD, Constants.ARM_ANGLE_MOTOR_K_CRUISE_VELOCITY, Constants.ARM_ANGLE_MOTOR_K_ACCELERATION, false);
        //kF: 0, kP: .25, kI: 0.000015, kD: 0, kCruiseVelocity: 0, kAcceleration: 0
        armAngleMotor.setNeutralMode(NeutralMode.Brake);

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

        SmartDashboard.putNumber("Actual Arm Angle Motor Position: ", armAngleRawActualPosition);
        /*Shuffleboard.getTab("bestTab").add("Actual Arm Angle Motor Position: ", armAngleRawActualPosition);*/

        // ouput desired arm angle
        SmartDashboard.putNumber("Desired Arm Angle Motor Position ", armAngleDesiredPosition);
        /* Shuffleboard.getTab("bestTab").add("Desired Arm Angle Motor Position ", armAngleDesiredPosition); */
        
        // If reset encoder is selected, it runs encoder reset method
        // if(angleEncoderReset.getSelected()){
        //     resetAngleMotorEncoder();
        // }
    }

    // reset arm angle motor encoder
    public void resetAngleMotorEncoder(){
        armAngleMotor.setSelectedSensorPosition(0);
    }

    // Method that allows movement of the arm angle
    public void setDesiredArmPosition(double joystickAngle){
        // TODO: figure out max and min arm angle positions
       /* Top encoder pos:
        * Bottom encoder pos:
        */
        // makes sure we are inside acceptable area of motion
        if ((armAngleDesiredPosition <= Constants.MAX_ARM_ANGLE_POSITION) && (armAngleDesiredPosition >= Constants.MIN_ARM_ANGLE_POSITION)){
            // Joystick deadzone
            if(Math.abs(joystickAngle) > .1 ){
                armAngleDesiredPosition = armAngleDesiredPosition + (-joystickAngle * 200); // Set to constant 
            }
            
        // Makes sure desired pos doesn't go above or bellow max
        }else{
            if(armAngleDesiredPosition > Constants.MAX_ARM_ANGLE_POSITION){
                armAngleDesiredPosition = Constants.MAX_ARM_ANGLE_POSITION;
            }else if(armAngleDesiredPosition < Constants.MIN_ARM_ANGLE_POSITION){
                armAngleDesiredPosition = Constants.MIN_ARM_ANGLE_POSITION;
            }
        }

    }
    public void setArmActualPosToDesiredPos(){
        armAngleMotor.set(ControlMode.Position, armAngleDesiredPosition);
    }

    // IDLE MODE SETTER METHODS
    public void setArmAngleMotorCoast(){
        armAngleMotor.setNeutralMode(NeutralMode.Coast);
    }
    public void setArmAngleMotorBreak(){
        armAngleMotor.setNeutralMode(NeutralMode.Brake);
    }

    // CUSTOM MOVEMENT METHOD
    public void setArmActualPosCustom(double position){
        armAngleMotor.set(ControlMode.Position, position);
    }
}