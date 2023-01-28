package frc.robot.subsystems;
// #region imports
// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
// Pneumatics
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// Smart Dashboard
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// #endregion
public class Grabber extends SubsystemBase {
    // #region Grabber objects and variables
    private Solenoid m_pistonGrabber;
    private PneumaticHub m_pneumaticHub;
    private boolean pneumaticBool = true;
    // #endregion
    public Grabber(){
        // object constructors
        m_pneumaticHub = new PneumaticHub(Constants.GRABBER_PNEUMATIC_HUB_ID);
        m_pistonGrabber = new Solenoid(6, PneumaticsModuleType.REVPH, 2); // Channels TBD, will be constants
    }
    // This method will be called once per scheduler run
    @Override
    public void periodic() {
        // Prints out system pressure
        // System.out.println("System Pressure" + m_pneumaticHub.getPressure(0)); // Channels TBD, will be constants
        // Puts compressor status on Smart Dashboard
        SmartDashboard.putBoolean("Compressor on: ", m_pneumaticHub.getCompressor());
        // Puts pressure on the smart dashboard
        SmartDashboard.putNumber("System Pressure: ", m_pneumaticHub.getPressure(0));
        
        // If the Prssure is less than 110 then it enables the compressor and if greater than 120 then it disables the compressor
        if(m_pneumaticHub.getPressure(0) < 110){
            // Enables compressor
            m_pneumaticHub.enableCompressorDigital();
        }else if(m_pneumaticHub.getPressure(0) > 120){
            // Disables compressor
            m_pneumaticHub.disableCompressor();
        }
    }
    /* This method sets the piston grabber to the different possitions depending on pneumaticBool, which changes after every 
     iteration, inducing a toggle effect... hopefully, it needs to be tested. */ 
    public void togglePiston() {
        if (pneumaticBool) {
            m_pistonGrabber.set(true);
            System.out.println("Piston retract engauged");
            pneumaticBool = false;
            
        }else if (!pneumaticBool) {
            m_pistonGrabber.set(false);
            System.out.println("Piston extend engauged");
            pneumaticBool = true;
        }
    }
}