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
    private WPI_TalonFX grabberMotor;
    // private boolean grabberStatus = false; // starts as false because grabber starts as open
    public Grabber(){
        // Motor for moving the string
        grabberMotor = new WPI_TalonFX(Constants.GRABBER_MOTOR_ID);
        RobotContainer.configureTalonFX(grabberMotor, false, false, 0, .1, 0, 0);
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic(){
        // SmartDashboard.putBoolean("Grabber status (true closed, false open)", grabberStatus);
        SmartDashboard.putNumber("Grabber Encoder pos: ", grabberMotor.getSelectedSensorPosition());
    }

    // public void toggleGrabber(){
    //     if(grabberStatus){
    //         // Open grabber
    //         grabberMotor.set(ControlMode.Position, 0);
    //         grabberStatus = false;
    //     }else{
    //         // Close grabber
    //         grabberMotor.setVoltage(-Constants.GRABBER_VOLTAGE);
    //         grabberStatus = true;
    //     }
    // }

    public void grabberSetVoltage(double voltage){
        grabberMotor.setVoltage(voltage);
    }
}