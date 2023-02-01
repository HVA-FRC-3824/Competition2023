package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//Controls LEDs
public class LEDs extends SubsystemBase
{
    // Declare LED objects
    private AddressableLED m_LED;
    private AddressableLEDBuffer m_LEDLength;
    private int m_hue = 0;

    public LEDs()
    {
        m_LED = new AddressableLED(Constants.LEDS_ID);
        m_LEDLength = new AddressableLEDBuffer(Constants.TOTAL_LEDS_COUNT);
        // Set LED length:
        m_LED.setLength(m_LEDLength.getLength());
        // Set output data & start LEDs
        m_LED.setData(m_LEDLength);
        // Start:
        m_LED.start();
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic()
    {
        LEDsColor(m_hue++, 225, 225);
        // Makes sure hue doesn't go out of range.
        if(m_hue >= 180)
        {
            m_hue = 0;
        }
        // Sets the LED HSV 
        m_LED.setData(m_LEDLength);
    }

    // Change LED colors in rainbow for launch
    public void LEDsColor(int hue, int saturation, int value)
    {
        for(int led = 0; led < m_LEDLength.getLength(); led++)
        {
            m_LEDLength.setHSV(led, hue, saturation, value);
            
        }
    }
}