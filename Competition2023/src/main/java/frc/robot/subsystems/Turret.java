package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Turret {
    private WPI_TalonFX m_turretMotor;
    public Turret(){
        m_turretMotor = new WPI_TalonFX(Constants.TURRET_MOTOR_ID);
        RobotContainer.configureTalonFX(m_turretMotor, false, false, 0.0, 0.0, 0.0, 0.0);
    }
}
