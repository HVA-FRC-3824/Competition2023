package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.util.WPILibVersion;
import frc.robot.Constants;
import frc.robot.RobotContainer;
public class Arm {
    private WPI_TalonFX m_winchArm;
    public Arm(){
        m_winchArm = new WPI_TalonFX(Constants.ARM_MOTOR_ID);
        RobotContainer.configureTalonFX(m_winchArm, false, false, 0, 0, 0, 0);
    }
}
