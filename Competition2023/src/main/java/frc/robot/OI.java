package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI{
    // joysticks
    private static Joystick m_driverJoystick;
    private static Joystick m_operatorJoystick;
    // buttons
    private static JoystickButton m_gearShiftBtn;
    private static JoystickButton m_grabberBtn;
    private static JoystickButton m_driveTrainStateToggleBtn;
    public OI(){
        // Joysticks/Controllers
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
        // Driver buttons
        m_gearShiftBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GEAR_SHIFT_BTN_ID);
        // Operator buttons
        m_driveTrainStateToggleBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_CHANGE_STATE_TOGGLE_BTN_ID);
    }

    // Used for driving command
    public Joystick getDriverJoystick(){
        return m_driverJoystick;
    }

    // Will be used for arm control command
    public Joystick getOperatorJoystick(){
        return m_operatorJoystick;
    }

    public void configureButtonBindings(){
        // WestCoastDrive
        m_gearShiftBtn.onTrue(RobotContainer.m_inlineCommands.m_shiftHighGear);
        m_gearShiftBtn.onFalse(RobotContainer.m_inlineCommands.m_shiftLowGear);
        m_driveTrainStateToggleBtn.onTrue(RobotContainer.m_inlineCommands.m_changeState);
        // Grabber
    }
}