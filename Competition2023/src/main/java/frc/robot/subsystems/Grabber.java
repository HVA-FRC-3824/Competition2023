package frc.robot.subsystems;

// #region imports
// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;
import frc.robot.RobotContainer;
// Smart Dashboard
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion
public class Grabber extends SubsystemBase{
    private WPI_TalonFX m_grabberMotor;
    private boolean m_grabberStatus = false; // starts as false because grabber starts as open
    public Grabber(){
        // Motor for moving the string
        m_grabberMotor = new WPI_TalonFX(Constants.GRABBER_MOTOR_ID);
        RobotContainer.configureTalonFX(m_grabberMotor, false, false, 0, .1, 0, 0);
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Grabber status (true closed, false open)", m_grabberStatus);
    }

    public void toggleGrabber(){
        if(m_grabberStatus){
            m_grabberMotor.setVoltage(Constants.GRABBER_OPEN_VOLTAGE);
            m_grabberStatus = false;
        }else{
            m_grabberMotor.setVoltage(Constants.GRABBER_CLOSE_VOLTAGE);
            m_grabberStatus = true;
        }
    }
}