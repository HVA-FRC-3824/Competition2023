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
import com.ctre.phoenix.motorcontrol.NeutralMode;

// Smart Dashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion

public class Arm extends SubsystemBase{
    // motors
    private WPI_TalonSRX m_armAngleMotor;
    private WPI_TalonFX m_armExtendMotor;

    // encoder position variables
    private double m_armAngleDesiredPosition = 0;
    private double m_armAngleActualPosition;
    private double m_actualArmExtensionPos;

    private final SendableChooser<Boolean> m_encoderReset = new SendableChooser<>();

    public Arm(){
        //TODO PIDS
        m_armAngleMotor = new WPI_TalonSRX(Constants.ARM_ANGLE_MOTOR_ID);
        RobotContainer.configureTalonSRX(m_armAngleMotor, false, null, false, false,
        0, 0.1, .1, 0.2, 0, 0, false);
        m_armAngleMotor.setNeutralMode(NeutralMode.Brake);

        //TODO PIDS
        m_armExtendMotor = new WPI_TalonFX(Constants.ARM_EXTEND_MOTOR_ID);
        RobotContainer.configureTalonFX(m_armExtendMotor, true, true, 0.0, 0.1, 0.0, 0.0);
        m_armExtendMotor.setNeutralMode(NeutralMode.Brake);

        m_encoderReset.setDefaultOption("False: ", false);
        m_encoderReset.addOption("True: ", true);
        SmartDashboard.putData("Reset Arm Angle Encoder: ", m_encoderReset);
    }

    @Override
    public void periodic() {
        // output encoder position for angleMotor
        m_armAngleActualPosition = m_armAngleMotor.getSelectedSensorPosition();
        SmartDashboard.putNumber("Actual Arm Angle Motor Position: ", m_armAngleActualPosition);

        SmartDashboard.putNumber("Desired Arm Angle Motor Position ", m_armAngleDesiredPosition);

        // output encoder position for extendMotor
        m_actualArmExtensionPos = m_armExtendMotor.getSelectedSensorPosition();
        SmartDashboard.putNumber("Actual Arm Extension Position: ", m_actualArmExtensionPos);

        if(m_encoderReset.getSelected()){
            resetAngleMotorEncoder();
        }
    }

    public void resetAngleMotorEncoder(){
        m_armAngleMotor.setSelectedSensorPosition(0);
    }

    // Method that allows movement of the arm angle
    public void setDesiredArmPosition(double joystickAngle){
       /* Top encoder pos:
        * Bottom encoder pos:
        */
        // makes sure we are inside acceptable area of motion
        if ((m_armAngleDesiredPosition < Constants.MAX_ARM_POSITION) && (m_armAngleDesiredPosition > Constants.MIN_ARM_POSITION)){
            // Joystick deadzone
            if(Math.abs(joystickAngle) > .1 ){
                m_armAngleDesiredPosition = m_armAngleDesiredPosition + (joystickAngle * 2); // TODO probably need to increase, also set to constant 
            }
            
        // Makes sure desired pos doesn't go above or bellow max
        }else{
            if(m_armAngleDesiredPosition > Constants.MAX_ARM_POSITION){
                m_armAngleDesiredPosition = Constants.MAX_ARM_POSITION;
            }else if(m_armAngleDesiredPosition < Constants.MIN_ARM_POSITION){
                m_armAngleDesiredPosition = Constants.MIN_ARM_POSITION;
            }
        }
    }

    public void setArmActualPosToDesiredPos(){
        m_armAngleMotor.set(ControlMode.Position, m_armAngleDesiredPosition);
    }

    public void setArmMotorsCoast(){
        m_armAngleMotor.setNeutralMode(NeutralMode.Coast);
        m_armExtendMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void setArmMotorsBreak(){
        m_armAngleMotor.setNeutralMode(NeutralMode.Brake);
        m_armExtendMotor.setNeutralMode(NeutralMode.Brake);
    }

    // sets arm to bottom grid score length
    public void extendArmBotton(){
        m_armExtendMotor.set(ControlMode.Position, Constants.ARM_BOTTOM_EXTENSION_VALUE);
    }

    // sets arm to middle grid score length
    public void extendArmMiddle(){
        m_armExtendMotor.set(ControlMode.Position, Constants.ARM_MIDDLE_EXTENSION_VALUE);
    }

    // sets arm to top grid score length
    public void extendArmTop(){
        m_armExtendMotor.set(ControlMode.Position, Constants.ARM_TOP_EXTENSION_VALUE);
    }

    // extends arm for fine tuning
    public void extendArm(){
        if(m_actualArmExtensionPos < Constants.MAX_ARM_EXTENSION){
            m_armExtendMotor.setVoltage(Constants.ARM_EXTENSION_VOLTAGE);
        }else{
            System.out.println("WARNING: Arm Extension Position is greater than Max extension!!! ");
        }
    }

    // retracts arm for fine tuning
    public void retractArm(){
        if(m_actualArmExtensionPos > Constants.MIN_ARM_EXTENSION){
            m_armExtendMotor.setVoltage(-Constants.ARM_EXTENSION_VOLTAGE);
        }else{
            System.out.println("WARNING: Arm Extension Position is less than Minimum extension!!! ");
        }
    }

    public void stopArm(){
        m_armExtendMotor.setVoltage(0);
    }
}