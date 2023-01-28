package frc.robot;
// #region
import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import edu.wpi.first.wpilibj2.command.button.Trigger; // I thought I needed this for the onFalse and onTrue methods
// #endregion
public class OI{
    // #region OI objects
    // joysticks
    private static Joystick m_driverJoystick;
    private static Joystick m_operatorJoystick;

    // buttons
    private static JoystickButton m_gearShiftBtn;
    private static JoystickButton m_grabberBtn;
    // #endregion
    public OI(){
        // #region Instantiate OI objects
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);

        m_gearShiftBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GEAR_SHIFT_BTN_ID);

        m_grabberBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GRABBER_TOGGLE_BTN_ID);
        // #endregion
    }
    public Joystick getDriverJoystick() {
        return m_driverJoystick;
    }
    public void configureButtonBindings(){
        // West Coast
        m_gearShiftBtn.onTrue(RobotContainer.m_inlineCommands.m_shiftHighGear);
        m_gearShiftBtn.onFalse(RobotContainer.m_inlineCommands.m_shiftLowGear);
        // Grabber
        m_grabberBtn.onTrue(RobotContainer.m_inlineCommands.m_togglePiston);
    }
}