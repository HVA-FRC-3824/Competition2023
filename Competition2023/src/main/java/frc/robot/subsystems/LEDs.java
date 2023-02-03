package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Class that controls LEDs
public class LEDs extends SubsystemBase{
    // enum objects
    public enum LEDsPattern {RAINBOW, RED, BLUE, GREEN, BOUNCE, TWINKLE};
    private LEDsPattern m_LEDsPattern = LEDsPattern.RAINBOW; // Default set to rainbow
    // Declare and instantiate LED objects and variables
    private AddressableLED m_LED = new AddressableLED(Constants.LEDS_ID);
    private AddressableLEDBuffer m_LEDsBuffer = new AddressableLEDBuffer(Constants.TOTAL_LEDS_COUNT);
    private int m_hue = 0;
    
    public LEDs(){ 
        // Sets the LEDs length to the buffer length
        m_LED.setLength(m_LEDsBuffer.getLength());
        // Sets the Leds to the data stored on the buffer
        m_LED.setData(m_LEDsBuffer);
        m_LED.start();
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic(){
        switch(m_LEDsPattern){
            case RAINBOW:
                setLEDsRainbow();
                break;
            case BLUE:
                // Blue Hue: 85-135
                setLEDsColor(100, 255, 255);
                break;
            case RED: 
                // Red Hue: 170-15 
                setLEDsColor(180, 255, 255);
                break;
            case GREEN:
                // Green Hue: 40-75
                setLEDsColor(50, 255, 255);
                break;
            case BOUNCE:
                setLEDsBounce();
                break;
            case TWINKLE:
                setLEDsTwinkle();
                break;
            default: 
                setLEDsColor(180, 255, 255);
                setLEDsColor(20, 255, 255);
                System.out.println("ERROR: LEDs switch case Not getting a color pattern");
                break;   
        }
        // Sends the LED buffer data to the LEDS 
        m_LED.setData(m_LEDsBuffer);
    }

    private void setLEDsRainbow(){
        setLEDsColor(m_hue++, 255, 255);
        // Makes sure hue doesn't go out of range.
        if(m_hue >= 180){
            m_hue = 0;
        }          
    }

    // Change LED colors in rainbow for launch, regular run
    private void setLEDsColor(int hue, int saturation, int value){
        for(int led = 0; led < m_LEDsBuffer.getLength(); led++){
            m_LEDsBuffer.setHSV(led, hue, saturation, value);
        }
    }

    private void setLEDsTwinkle(){
        // TODO write this method
        // twinkle blue for rohawktics?
    }

    private void setLEDsBounce(){
        // TODO write this method
        // bounce blue for rohawktics?
    }

    // Method called in other places to set a led pattern
    public void setLEDsPattern(LEDsPattern pattern){
        m_LEDsPattern = pattern;
    }
}