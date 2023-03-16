package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.PneumaticHub;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// #endregion
public class Grabber extends SubsystemBase{
    // private PneumaticHub pneumaticHub;
    // private Solenoid grabberPiston;
    // private boolean grabberOpen = true;
    private WPI_TalonFX grabberMotor;

    public Grabber(){
        // pneumaticHub = new PneumaticHub(Constants.PNEUMATIC_HUB_CAN_ID);
        // grabberPiston = new Solenoid(Constants.PNEUMATIC_HUB_CAN_ID, PneumaticsModuleType.REVPH, Constants.GRABBER_RPH_ID);
        grabberMotor = new WPI_TalonFX(Constants.GRABBER_MOTOR_CAN_ID);
        RobotContainer.configureTalonFX(grabberMotor, false, false, 0, .1, 0, 0);
        grabberMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void grabberSetVoltage(double voltage){
        grabberMotor.setVoltage(voltage);
    }
    
    /* This method sets the piston grabber to the different possitions depending on pneumaticBool, which changes after every 
       iteration, inducing a toggle effect */ 
    // public void toggleGrabber(){
    //     if (grabberOpen) {
    //         grabberPiston.set(true);
    //         grabberOpen = false;
    //     }else if (!grabberOpen) {
    //         grabberPiston.set(false);
    //         grabberOpen = true;
    //     }
    // }

    // This method will be called once per scheduler run
    @Override
    public void periodic(){
        // Puts pressure on the smart dashboard
        // SmartDashboard.putNumber("System Pressure: ", pneumaticHub.getPressure(0));
        // // If the Prssure is less than 110 then it enables the compressor and if the pressure is greater than 120 then it disables the compressor
        // if(pneumaticHub.getPressure(Constants.PNEUMATIC_HUB_ANALOG_ID) < 110){
        //     pneumaticHub.enableCompressorDigital();
        // }else if(pneumaticHub.getPressure(Constants.PNEUMATIC_HUB_ANALOG_ID) > 120){
        //     pneumaticHub.disableCompressor();
        // }

        // // Puts compressor status on Smart Dashboard
        // SmartDashboard.putBoolean("Compressor on: ", pneumaticHub.getCompressor());

        SmartDashboard.putNumber("Grabber Encoder pos: ", grabberMotor.getSelectedSensorPosition());
    }
}