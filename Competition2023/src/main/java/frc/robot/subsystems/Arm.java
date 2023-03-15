package frc.robot.subsystems;

// #region imports
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

// Smart Dashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion

public class Arm extends SubsystemBase{
    // motors
    private WPI_TalonSRX armAngleMotor;
    private WPI_TalonFX armExtendMotor;

    // encoder position variables
    private double armAngleDesiredPosition = 0;
    private double armAngleRawActualPosition;
    // private double armAngleActualPosition;
    private double actualArmExtensionPos;

    private final SendableChooser<Boolean> angleEncoderReset = new SendableChooser<>();
    // private final SendableChooser<Boolean> extensionEncoderReset = new SendableChooser<>();

    public Arm(){
        //TODO PIDS
        armAngleMotor = new WPI_TalonSRX(Constants.ARM_ANGLE_MOTOR_CAN_ID);
        RobotContainer.configureTalonSRX(armAngleMotor, true, FeedbackDevice.CTRE_MagEncoder_Absolute, false, true,
        0, .2, 0.00001, 0, 0, 0, false);
        armAngleMotor.setNeutralMode(NeutralMode.Brake);

        //TODO PIDS
        armExtendMotor = new WPI_TalonFX(Constants.ARM_EXTEND_MOTOR_CAN_ID);
        RobotContainer.configureTalonFX(armExtendMotor, true, true, 0.0, 0, 0.0, 0.0);
        armExtendMotor.setNeutralMode(NeutralMode.Brake);

        // Angle reset
        angleEncoderReset.setDefaultOption("False: ", false);
        angleEncoderReset.addOption("True: ", true);
        SmartDashboard.putData("Reset Arm Angle Encoder: ", angleEncoderReset);

        // Extension reset
        // extensionEncoderReset.setDefaultOption("False: ", false);
        // extensionEncoderReset.addOption("True: ", true);
        // SmartDashboard.putData("Reset Arm Angle Encoder: ", extensionEncoderReset);
    }

    @Override
    public void periodic() {
        // output encoder position for angleMotor
        armAngleRawActualPosition = armAngleMotor.getSelectedSensorPosition();
        // armAngleActualPosition = (armAngleRawActualPosition / Constants.ARM_ANGLE_GEAR_RATIO) / 2048;
        SmartDashboard.putNumber("Actual Arm Angle Motor Position: ", armAngleRawActualPosition);

        // ouput desired arm angle
        SmartDashboard.putNumber("Desired Arm Angle Motor Position ", armAngleDesiredPosition);

        // set actual arm extension and output encoder position for extendMotor
        actualArmExtensionPos = (armExtendMotor.getSelectedSensorPosition() / Constants.ARM_EXTENSION_GEAR_RATIO) / 2048;
        SmartDashboard.putNumber("Actual Arm Extension Position: ", actualArmExtensionPos);

        // If reset encoder is selected, it runs encoder reset method
        if(angleEncoderReset.getSelected()){
            resetAngleMotorEncoder();
        }

        // If reset encoder is selected, it runs encoder reset method
        // if(extensionEncoderReset.getSelected()){
        //     resetExtensionMotorEncoder();
        // }
    }

    // reset arm angle motor encoder
    public void resetAngleMotorEncoder(){
        armAngleMotor.setSelectedSensorPosition(0);
    }

    // reset arm angle motor encoder
    public void resetExtensionMotorEncoder(){
        armExtendMotor.setSelectedSensorPosition(0);
    }

    // Method that allows movement of the arm angle
    public void setDesiredArmPosition(double joystickAngle){
       /* Top encoder pos:
        * Bottom encoder pos:
        */
        // makes sure we are inside acceptable area of motion
        if ((armAngleDesiredPosition <= Constants.MAX_ARM_POSITION) && (armAngleDesiredPosition >= Constants.MIN_ARM_POSITION)){
            // Joystick deadzone
            if(Math.abs(joystickAngle) > .1 ){
                armAngleDesiredPosition = armAngleDesiredPosition + (joystickAngle * 100); // TODO probably need to increase, also set to constant 
            }
            
        // Makes sure desired pos doesn't go above or bellow max
        }else{
            if(armAngleDesiredPosition > Constants.MAX_ARM_POSITION){
                armAngleDesiredPosition = Constants.MAX_ARM_POSITION;
            }else if(armAngleDesiredPosition < Constants.MIN_ARM_POSITION){
                armAngleDesiredPosition = Constants.MIN_ARM_POSITION;
            }
        }

    }

    public void setArmActualPosToDesiredPos(){
        armAngleMotor.set(ControlMode.Position, armAngleDesiredPosition);
    }

    public void setArmMotorsCoast(){
        armAngleMotor.setNeutralMode(NeutralMode.Coast);
        armExtendMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void setArmMotorsBreak(){
        armAngleMotor.setNeutralMode(NeutralMode.Brake);
        armExtendMotor.setNeutralMode(NeutralMode.Brake);
    }

    // sets arm to bottom grid score length
    public void extendArmBotton(){
        armExtendMotor.set(ControlMode.Position, Constants.ARM_BOTTOM_EXTENSION_VALUE);
    }

    // sets arm to middle grid score length
    public void extendArmMiddle(){
        armExtendMotor.set(ControlMode.Position, Constants.ARM_MIDDLE_EXTENSION_VALUE);
    }

    // sets arm to top grid score length
    public void extendArmTop(){
        armExtendMotor.set(ControlMode.Position, Constants.ARM_TOP_EXTENSION_VALUE);
    }

    public void armExtendCustom(double pos){
        armExtendMotor.set(ControlMode.Position, pos);
    }

    // extends arm for fine tuning
    public void extendArm(){
        if(actualArmExtensionPos < Constants.MAX_ARM_EXTENSION){
            armExtendMotor.setVoltage(Constants.ARM_EXTENSION_VOLTAGE);
        }else{
            System.out.println("WARNING: Arm Extension Position is greater than Max extension!!! ");
            armExtendMotor.setVoltage(0);
        }
    }

    // retracts arm for fine tuning
    public void retractArm(){
        if(actualArmExtensionPos > Constants.MIN_ARM_EXTENSION){
            armExtendMotor.setVoltage(-Constants.ARM_EXTENSION_VOLTAGE);
        }else{
            System.out.println("WARNING: Arm Extension Position is less than Minimum extension!!! ");
            armExtendMotor.setVoltage(0);
        }
    }

    public void stopArm(){
        armExtendMotor.setVoltage(0);
    }
}