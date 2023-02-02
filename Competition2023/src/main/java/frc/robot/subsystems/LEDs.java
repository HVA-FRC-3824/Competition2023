package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

// Class that controls LEDs
public class LEDs extends SubsystemBase{
    // Declare LED objects
    private AddressableLED m_LED;
    private AddressableLEDBuffer m_LEDsBuffer;
    private int m_hue = 0;
    private boolean m_isRed = false;

    public LEDs(){
        m_LED = new AddressableLED(Constants.LEDS_ID);
        m_LEDsBuffer = new AddressableLEDBuffer(Constants.TOTAL_LEDS_COUNT);
        // Sets the LEDs length to the buffer length
        m_LED.setLength(m_LEDsBuffer.getLength());
        // Sets the Leds to the data stored on the buffer
        m_LED.setData(m_LEDsBuffer);
        m_LED.start();
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic(){
        setLEDsColor(m_hue++, 225, 225);
        // Makes sure hue doesn't go out of range.
        if(m_hue >= 180){
            m_hue = 0;
        }   
        // Sets the LED HSV 
        m_LED.setData(m_LEDsBuffer);

        // if reverse, then do reverse lights *** CURRENTLY FIGHTING THE RAINBOW LOOP
        if (RobotContainer.M_WEST_COAST_DRIVE.isReverse() && !m_isRed) {
            this.reverseLEDsColor(180, 180, 180);
        }
    }

    // Change LED colors (when the robot is moveing backwords)
    public void reverseLEDsColor(int hue, int saturation, int value){
        for (int led = 0; led < m_LEDsBuffer.getLength(); led++){
            m_LEDsBuffer.setHSV(0, hue, saturation, value);
        }
        m_isRed = true;
    }

    // Change LED colors in rainbow for launch, regular run
    public void setLEDsColor(int hue, int saturation, int value){
        for(int led = 0; led < m_LEDsBuffer.getLength(); led++){
            m_LEDsBuffer.setHSV(led, hue, saturation, value);
        }
        m_isRed = false;
    }
}