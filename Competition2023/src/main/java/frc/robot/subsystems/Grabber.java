package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// #endregion
public class Grabber extends SubsystemBase{
    private WPI_TalonSRX grabberMotor;
    public Grabber(){
        grabberMotor = new WPI_TalonSRX(13); //TODO: make it a constant
        RobotContainer.configureTalonSRX(grabberMotor, false, null, false,
                         false, 0, 0, 0, 0, 0, 0, false);
        grabberMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void realese() {
        grabberMotor.setVoltage(-Constants.GRABBER_VOLTAGE);
    }

    public void stop() {
        grabberMotor.setVoltage(0);
    }
    
    public void grab() {
       grabberMotor.setVoltage(Constants.GRABBER_VOLTAGE); 
    }
    
    @Override
    public void periodic(){}
}

// Grabber Notes
// communicate with build and design (Michael/Sarah/Sam/Gavin)
// what motors (how many- about 1 or 2)
// what motor controllers
// gear ratios
// everything I need to be able to code the grabber