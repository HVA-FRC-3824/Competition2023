package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI{
    //joysticks
    private static Joystick m_driverJoystick;
    private static Joystick m_operatorJoystick;
    public OI(){
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
    }
    public void configureButtonBindings(){

    }
}