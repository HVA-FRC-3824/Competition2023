package frc.robot;

// Joystick and Button
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// Commands
import frc.robot.commands.Autobalance;

public class OI{
    // joysticks
    private static Joystick m_driverJoystick;
    private static Joystick m_operatorJoystick;
    // driver buttons
    private static JoystickButton m_gearShiftBtn;
    // operator buttons
    private static JoystickButton m_grabberBtn;
    private static JoystickButton m_autoBalanceBtn;
    private static JoystickButton m_setAndHoldPoseBtn;
    private static JoystickButton m_setArmTopPosBtn;
    private static JoystickButton m_setArmMiddlePosLeftBtn;
    private static JoystickButton m_setArmMiddlePosRightBtn;
    private static JoystickButton m_setArmBottomPosBtn;
    private static JoystickButton m_extendArmBtn;
    private static JoystickButton m_retractArmBtn;
    // Commands
    private Autobalance m_autoBalanceCommandClass = new Autobalance();

    public OI(){
        // Joysticks/Controllers
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
        // Driver buttons
        m_gearShiftBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GEAR_SHIFT_BTN_ID);
        // Operator buttons
        m_grabberBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_GRABBER_TOGGLE_BTN_ID);
        m_autoBalanceBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_AUTOBALANCE_BTN_ID);
        m_setAndHoldPoseBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_HOLD_POSE_TOGLE_BTN_ID);
        m_setArmTopPosBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_TOP_POSE_BTN_ID);
        m_setArmMiddlePosLeftBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_MIDDLE_POSE_LEFT_BTN_ID);
        m_setArmMiddlePosRightBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_MIDDLE_POSE_RIGHT_BTN_ID);
        m_setArmBottomPosBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_BOTTOM_POSE_BTN_ID);
        m_extendArmBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_EXTEND_ARM_BTN_ID);
        m_retractArmBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_RETRACT_ARM_BTN_ID);
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
        m_autoBalanceBtn.onTrue(m_autoBalanceCommandClass);
        m_setAndHoldPoseBtn.onTrue(RobotContainer.m_inlineCommands.m_setAndHoldPose);
        // Grabber
        m_grabberBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleGrabber);
        // Arm
        m_setArmTopPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armTopPos);
        m_setArmMiddlePosLeftBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        m_setArmMiddlePosRightBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        m_setArmBottomPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armBottomPos);
        m_extendArmBtn.whileTrue(RobotContainer.m_inlineCommands.m_extendArm);
        m_retractArmBtn.whileTrue(RobotContainer.m_inlineCommands.m_retractArm);
    }
}