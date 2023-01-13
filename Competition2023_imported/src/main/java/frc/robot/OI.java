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
    public OI(){
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        // m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
        m_gearShiftBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GEAR_SHIFT_BTN_ID);
    }
    public void configureButtonBindings(){
        m_gearShiftBtn.onTrue(RobotContainer.m_inlineCommands.m_shiftHighGear);
        m_gearShiftBtn.onFalse(RobotContainer.m_inlineCommands.m_shiftLowGear);
    }
    public Joystick getDriverJoystick() {
        return m_driverJoystick;
    }
}