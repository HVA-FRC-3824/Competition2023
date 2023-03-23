package frc.robot.subsystems;

// import java.util.Random;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

// Class that controls LEDs
public class LEDs extends SubsystemBase{
    // enum objects
    public enum LEDsPattern {RAINBOW, RED, BLUE, GREEN, BOUNCE, TWINKLE, NOTHING};
    // private LEDsPattern currLEDsPattern = LEDsPattern.RAINBOW; // Default set to rainbow
    // Declare and instantiate LED objects and variables
    private AddressableLED LED = new AddressableLED(Constants.LEDS_ID);
    private AddressableLEDBuffer LEDsBuffer = new AddressableLEDBuffer(Constants.TOTAL_LEDS_COUNT);
    private int hue = 0;
    // private int i = 0;
    
    public LEDs(){ 
        // Sets the LEDs length to the buffer length
        LED.setLength(LEDsBuffer.getLength());
        // Sets the Leds to the data stored on the buffer
        LED.setData(LEDsBuffer);
        LED.start();
        setLEDsRainbow();
    }

    // This method will be called once per scheduler run
    @Override
    public void periodic(){
    //     switch(currLEDsPattern){
    //         case RAINBOW:
    //             // not checking if already in this state because the rainbow moves in a wave pattern
    //             setLEDsRainbow();
    //             break;
    //         case BLUE:
    //             if (currLEDsPattern != LEDsPattern.BLUE) {
    //                 // Blue Hue: 85-135
    //                 setLEDsColor(100, 255, 255);
    //                 this.setLEDsPattern(LEDsPattern.BLUE);
    //                 break;
    //             }
                
    //         case RED: 
    //             if (currLEDsPattern != LEDsPattern.RED) {
    //                 // Red Hue: 170-15 
    //                 setLEDsColor(180, 255, 255);
    //                 this.setLEDsPattern(LEDsPattern.RED);
    //                 break;
    //             }
    //         case GREEN:
    //             if (currLEDsPattern != LEDsPattern.GREEN) {
    //                 // Green Hue: 40-75
    //                 setLEDsColor(50, 255, 255);
    //                 this.setLEDsPattern(LEDsPattern.GREEN);
    //                 break;
    //             }
    //         case BOUNCE:
    //             setLEDsBounce();
    //             break;
    //         case TWINKLE:
    //             /* setLEDSTwinkle does NOT set the value once, and must be updated periodically */
    //             setLEDsTwinkle();
    //             break;
    //         case NOTHING:
    //             break;
    //         default: 
    //             setLEDsColor(180, 255, 255);
    //             System.out.println("ERROR: LEDs switch case Not getting a color pattern");
    //             break;   
    //     }
    //     // Sends the LED buffer data to the LEDS 
    //     LED.setData(LEDsBuffer);
    }

    private void setLEDsRainbow(){
        setLEDsColor(hue++, 255, 255);
        // Makes sure hue doesn't go out of range.
        if(hue >= 180){
            hue = 0;
        }          
    }

    // Change LED colors in rainbow for launch, regular run
    public void setLEDsColor(int hue, int saturation, int value){
        for(int led = 0; led < LEDsBuffer.getLength(); led++){
            LEDsBuffer.setHSV(led, hue, saturation, value);
        }
    }

    // private void setLEDsTwinkle(){
    //     Random rand = new Random();
    //     int led_max = LEDsBuffer.getLength();
        
    //     /* Set all to off */
    //     for(int i = 0; i < led_max; i++){
    //         LEDsBuffer.setHSV(i, 100, 255, 20);
    //     }

    //     /* Grab random led ten times */
    //     for(int i = 0; i < 30; i++){
    //         /* Set to lower value */
    //         LEDsBuffer.setHSV(rand.nextInt(led_max),100, 255, rand.nextInt(255));
    //     }
    //     // twinkle blue for rohawktics?
    // }

    // private void setLEDsBounce(){
    //     int led_max = LEDsBuffer.getLength();
    //     if(i == led_max){
    //        LEDsBuffer.setHSV(i, 100, 255, 0);
    //        i = 0;
    //        return;
    //     }
            
    //     if(i != 0){
    //         LEDsBuffer.setHSV(i-1, 100, 255, 0);
    //         i++;
    //         return;
    //     }
            
    //     LEDsBuffer.setHSV(i, 100, 255, 255);
    //     i++;
    // }
}