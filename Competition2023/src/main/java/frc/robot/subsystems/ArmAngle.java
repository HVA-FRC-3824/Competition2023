package frc.robot.subsystems;

// #region imports
import frc.robot.Constants;
import frc.robot.RobotContainer;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ResetAngleMotorEncoder;
// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

// Smart Dashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion

public class ArmAngle extends SubsystemBase{
    // motors
    private WPI_TalonSRX armAngleMotor;

    // encoder position variables
    private double armAngleDesiredPosition = 0;
    private double armAngleRawActualPosition;

    // private final SendableChooser<Boolean> angleEncoderReset = new SendableChooser<>();

    public ArmAngle(){
        //TODO PIDS
        armAngleMotor = new WPI_TalonSRX(Constants.ARM_ANGLE_MOTOR_CAN_ID);
        RobotContainer.configureTalonSRX(armAngleMotor, true, FeedbackDevice.CTRE_MagEncoder_Absolute, false, true,
        0, .2, 0.00001, 0, 0, 0, false);
        armAngleMotor.setNeutralMode(NeutralMode.Brake);

        // Angle reset smartdashboard chooser
        // angleEncoderReset.setDefaultOption("False: ", false);
        // angleEncoderReset.addOption("True: ", true);
        // SmartDashboard.putData("Reset Arm Angle Encoder: ", angleEncoderReset);

        SmartDashboard.putData("RESET ARM ANGLE ENCODER", new ResetAngleMotorEncoder());
    }

    @Override
    public void periodic() {
        // output encoder position for angleMotor
        armAngleRawActualPosition = armAngleMotor.getSelectedSensorPosition();
        // armAngleActualPosition = (armAngleRawActualPosition / Constants.ARM_ANGLE_GEAR_RATIO) / 2048;
        SmartDashboard.putNumber("Actual Arm Angle Motor Position: ", armAngleRawActualPosition);

        // ouput desired arm angle
        SmartDashboard.putNumber("Desired Arm Angle Motor Position ", armAngleDesiredPosition);

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

    public void setArmAngleMotorCoast(){
        armAngleMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void setArmAngleMotorBreak(){
        armAngleMotor.setNeutralMode(NeutralMode.Brake);
    }
}