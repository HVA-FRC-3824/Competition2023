package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub; // I figure I only need this as an object once so maybe I'll make a compressor class
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Grabber {
    private DoubleSolenoid m_pistonGrabber;
    private PneumaticsModuleType REVPH;
    public Grabber(){
        // configures doulbe solenoid for the grabber
        // m_pistonGrabber = new DoubleSolenoid(2, REVPH, 1, 0);
    }

    // method to toggle grabber piston
    public void togglePiston(){
        // m_pistonGrabber.set();
        System.out.println("Piston toggle engauged");
    }
}
