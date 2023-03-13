package frc.robot.subsystems;

// #region imports
import frc.robot.Constants;
import frc.robot.RobotContainer;
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
    private double armAngleDesiredPosition = 0;
    private double armAngleActualPosition;
    private double actualArmExtensionPos;

    public Arm(){
        //TODO PIDS
        m_armAngleMotor = new WPI_TalonSRX(Constants.ARM_ANGLE_MOTOR_ID);
        RobotContainer.configureTalonSRX(m_armAngleMotor, false, null, false, false,
        0, 0.1, 0, 0.0, 0, 0, false);
        m_armAngleMotor.setNeutralMode(NeutralMode.Brake);

        //TODO PIDS
        m_armExtendMotor = new WPI_TalonFX(Constants.ARM_EXTEND_MOTOR_ID);
        RobotContainer.configureTalonFX(m_armExtendMotor, true, true, 0.0, 0.1, 0.0, 0.0);
        m_armExtendMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {
        // output encoder position for angleMotor
        armAngleActualPosition = m_armAngleMotor.getSelectedSensorPosition();
        SmartDashboard.putNumber("Actual Arm Angle Motor Position: ", armAngleActualPosition);

        SmartDashboard.putNumber("Desired Arm Angle Motor Position ", armAngleDesiredPosition);

        // output encoder position for extendMotor
        actualArmExtensionPos = m_armExtendMotor.getSelectedSensorPosition();
        SmartDashboard.putNumber("Actual Arm Extension Position: ", actualArmExtensionPos);
    }

    // Method that allows movement of the arm angle
    public void setDesiredArmPosition(double joystickAngle){
       /* Top encoder pos:
        * Bottom encoder pos:
        */
        if ((armAngleDesiredPosition < Constants.MAX_ARM_POSITION) && (armAngleDesiredPosition > Constants.MIN_ARM_POSITION)){
            armAngleDesiredPosition = armAngleDesiredPosition + (joystickAngle * 2); // TODO probably need to increase, also set to constant 


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
        m_armAngleMotor.set(ControlMode.Position, armAngleDesiredPosition);
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
        if(actualArmExtensionPos < Constants.MAX_ARM_EXTENSION){
            m_armExtendMotor.setVoltage(Constants.ARM_EXTENSION_VOLTAGE);
        }
    }

    // retracts arm for fine tuning
    public void retractArm(){
        if(actualArmExtensionPos > Constants.MIN_ARM_EXTENSION){
            m_armExtendMotor.setVoltage(-Constants.ARM_EXTENSION_VOLTAGE);
        }
    }

    public void stopArm(){
        m_armExtendMotor.setVoltage(0);
    }
}