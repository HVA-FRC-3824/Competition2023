package frc.robot.subsystems;

// General
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
// Pneumatics
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// Smart Dashboard
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grabber extends SubsystemBase {
    // create grabber objects and variables
    private DoubleSolenoid m_pistonGrabber;
    private PneumaticHub m_pneumaticHub;
    private int pneumaticCounter = 1;

    public Grabber(){
        // object constructors
        m_pneumaticHub = new PneumaticHub(Constants.GRABBER_PNEUMATIC_HUB_ID);
        // m_pneumaticHub.enableCompressorAnalog(100, 120); // didn't work, need to try hybrid. If this doesn't work Ill just manually do it with an if statement and getPressure();
        m_pistonGrabber = new DoubleSolenoid(3, PneumaticsModuleType.REVPH, 2, 3); // Channels TBD, will be constants
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

    /**
     * This method sets the piston grabber to the different possitions depending on pneumaticCounter, which changes after every 
     * iteration, inducing a toggle effect... hopefully, it needs to be tested. 
     */ 
    public void togglePiston() {
        if (pneumaticCounter > 0) {
            m_pistonGrabber.set(DoubleSolenoid.Value.kReverse);
            System.out.println("Piston retract engauged");
        }else if (pneumaticCounter < 0) {
            m_pistonGrabber.set(DoubleSolenoid.Value.kForward);
            System.out.println("Piston extend engauged");
        }
        pneumaticCounter *= -1;
    }
}