package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Grabber {
    private DoubleSolenoid m_pistonGrabber;
    private PneumaticsModuleType REVPH;
    public Grabber(){
        m_pistonGrabber = new DoubleSolenoid(2, REVPH, 1, 0);
    }
}
