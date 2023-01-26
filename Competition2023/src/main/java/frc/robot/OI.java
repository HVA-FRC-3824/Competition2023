package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import edu.wpi.first.wpilibj2.command.button.Trigger; // I thought I needed this for the onFalse and onTrue methods

public class OI{
    // joysticks
    private static Joystick m_driverJoystick;
    // private static Joystick m_operatorJoystick;

    private static JoystickButton m_gearShiftBtn;

    private static JoystickButton m_grabberBtn;

    public OI(){
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        // m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);

        m_gearShiftBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GEAR_SHIFT_BTN_ID);

        m_grabberBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GRABBER_TOGGLE_BTN_ID);
    }
    
    public Joystick getDriverJoystick() {
        return m_driverJoystick;
    }

    public void configureButtonBindings(){
        m_gearShiftBtn.onTrue(RobotContainer.m_inlineCommands.m_shiftHighGear);
        m_gearShiftBtn.onFalse(RobotContainer.m_inlineCommands.m_shiftLowGear);

        m_grabberBtn.onTrue(RobotContainer.m_inlineCommands.m_togglePiston);
    }
   
}