package frc.robot.subsystems;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #region imports
// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

//import frc.robot.Constants;
// Smart Dashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion

public class Arm extends SubsystemBase{

    private WPI_TalonFX m_postArm;
    private WPI_TalonFX m_reachArm;
    private double m_postToggleExtend;
    private double m_reachToggleExtend;

    public Arm(){
        // 1.2 just because. Just test it
        m_postToggleExtend = 1.2;
        m_reachToggleExtend = 1.2;

        m_postArm = new WPI_TalonFX(Constants.ARM_MOTOR_ID_POST);
        // should it be setInverted? setSensorPhase?
        RobotContainer.configureTalonFX(m_postArm, true, false, 0.0, 0.0, 0.0, 0.0);

        // created a new constant ARM_MOTOR_ID_REACH for the second motor
        m_reachArm = new WPI_TalonFX(Constants.ARM_MOTOR_ID_REACH);
        RobotContainer.configureTalonFX(m_reachArm, true, false, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Temperature of POST motor", m_postArm.getTemperature());
        SmartDashboard.putNumber("Temperature of REACH motor", m_reachArm.getTemperature());
        
    }

    public void togglePost(double power) {
        m_postArm.set(ControlMode.PercentOutput, power * m_postToggleExtend);
    }

}