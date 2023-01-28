package frc.robot.subsystems;

// #region imports
// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// Pneumatics
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// Smart Dashboard
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion

public class Grabber extends SubsystemBase {
    // Grabber objects and variables
    private Solenoid m_pistonGrabber;
    private PneumaticHub m_pneumaticHub;
    private boolean m_pneumaticBool = true;

    // Object Constructors
    public Grabber(){
        m_pneumaticHub = new PneumaticHub(Constants.PNEUMATIC_HUB_ID);
        m_pistonGrabber = new Solenoid(Constants.PNEUMATIC_HUB_ID, PneumaticsModuleType.REVPH, Constants.GRABBER_CHANNEL_ID);
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic() {
        // Puts pressure on the smart dashboard
        SmartDashboard.putNumber("System Pressure: ", m_pneumaticHub.getPressure(0));
        // If the Prssure is less than 110 then it enables the compressor and if the pressure is greater than 120 then it disables the compressor
        if(m_pneumaticHub.getPressure(Constants.PNEUMATIC_HUB_ANALOG_ID) < 110){
            // Enables compressor
            m_pneumaticHub.enableCompressorDigital();
        }else if(m_pneumaticHub.getPressure(Constants.PNEUMATIC_HUB_ANALOG_ID) > 120){
            // Disables compressor
            m_pneumaticHub.disableCompressor();
        }
        // Puts compressor status on Smart Dashboard
        SmartDashboard.putBoolean("Compressor on: ", m_pneumaticHub.getCompressor());
        // Puts piston status on Smart Dashboard | NEEDS TESTED***********************
        SmartDashboard.putBoolean("Piston Status [true=extended|false=retracted]: ", m_pneumaticBool);
    }

    /* This method sets the piston grabber to the different possitions depending on pneumaticBool, which changes after every 
     iteration, inducing a toggle effect */ 
    public void togglePiston() {
        if (m_pneumaticBool) {
            m_pistonGrabber.set(true);
            m_pneumaticBool = false;
        }else if (!m_pneumaticBool) {
            m_pistonGrabber.set(false);
            m_pneumaticBool = true;
        }
    }
}