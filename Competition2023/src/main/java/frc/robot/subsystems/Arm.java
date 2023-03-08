package frc.robot.subsystems;

// #region imports
import frc.robot.Constants;
import frc.robot.RobotContainer;

// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;



// Smart Dashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion

public class Arm extends SubsystemBase{
    private WPI_TalonSRX m_armAngleMotor;
    private WPI_TalonFX m_armExtendMotor;
    private double actualArmAngle;
    private double actualArmExtensionPos;
    private double m_rpm;

    public Arm(){
        m_armAngleMotor = new WPI_TalonSRX(Constants.ARM_ANGLE_MOTOR_ID);
        // should it be setInverted? setSensorPhase?
        RobotContainer.configureTalonSRX(m_armAngleMotor, false, null, false, false,
        0, 0, 0, 0.0, 0, 0, false);

        // created a new constant ARM_MOTOR_ID_REACH for the second motor
        m_armExtendMotor = new WPI_TalonFX(Constants.ARM_EXTEND_MOTOR_ID);
        RobotContainer.configureTalonFX(m_armExtendMotor, true, false, 0.0, 0.0, 0.0, 0.0);

        // TODO set actualArmAngle (using an encoder?)
        // TODO set actualArmExtentionPos
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Actual Arm Angle: ", actualArmAngle);
    }

    // TODO get this method up and running, PSEUDOCODE BELOW
    public void angleArm(double joyStickAngle) {
        m_rpm = joyStickAngle * 2;
        if ((Constants.MIN_ARM_ANGLE < actualArmAngle) && (actualArmAngle < Constants.MAX_ARM_ANGLE)) {
            // move the arm applying the joystickAngle
            // m_armAngleMotor.set(joyStickAngle); OR the line below
            m_armAngleMotor.set(ControlMode.PercentOutput, m_rpm);

            // we need to test to figure out rpm is how many degrees
            // or we could figure out rpm is how many feet the hand elevated

            // does the line below give the percent changed?
            System.out.println(ControlMode.PercentOutput);

            // update actual arm angle
            
        }
            
    }

    //TODO get these arm methods up and running, need encoders for all but extend arm and retract arm, but we want to make sure there are limits so we don't unwind it.
    public void extendArmBotton(){
        // sets arm to bottom grid score length
    }

    public void extendArmMiddle(){
        // sets arm to middle grid score length
    }

    public void extendArmTop(){
        // sets arm to top grid score length
    }

    public void extendArm(){
        // extends arm for fine tuning
        if(actualArmExtensionPos < Constants.MAX_ARM_EXTENSION){
            m_armExtendMotor.setVoltage(Constants.ARM_ANGLE_VOLTAGE);
        }
    }

    public void retractArm(){
        // retracts arm for fine tuning
        if(actualArmExtensionPos > Constants.MIN_ARM_EXTENSION){
            m_armExtendMotor.setVoltage(-Constants.ARM_ANGLE_VOLTAGE);
        }
    }

}