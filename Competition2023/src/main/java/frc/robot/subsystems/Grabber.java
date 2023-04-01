package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.RobotContainer;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Grabber extends SubsystemBase{
    private WPI_TalonSRX grabberMotor;
    
    public Grabber(){
        grabberMotor = new WPI_TalonSRX(Constants.GRABBER_CAN_ID);
        RobotContainer.configureTalonSRX(grabberMotor, false, null, false,
                         false, 0, 0, 0, 0, 0, 0, false);
        
        // Sets us to brake by default to hold gamepieces
        grabberMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic(){}

    public void realese() {
        grabberMotor.setVoltage(Constants.GRABBER_VOLTAGE);
    }

    public void stop() {
        grabberMotor.setVoltage(0);
    }
    
    public void grab() {
       grabberMotor.setVoltage(-Constants.GRABBER_VOLTAGE); 
    }
}