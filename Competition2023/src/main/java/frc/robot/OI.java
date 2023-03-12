package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
// Joystick and Button
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

// Commands
import frc.robot.commands.Autobalance;

public class OI{
    // joysticks
    private static Joystick m_driverJoystick;
    // private static Joystick m_operatorJoystick;
    private static Joystick m_operatorController;
    
    // driver joystick buttons
    private static JoystickButton m_gearShiftBtn;
    
    // operator joystick buttons
    // private static JoystickButton m_joystickGrabberBtn;
    // private static JoystickButton m_joystickAutoBalanceBtn;
    // private static JoystickButton m_joystickSetAndHoldPoseBtn;
    // private static JoystickButton m_joystickSetArmTopPosBtn;
    // private static JoystickButton m_joystickSetArmMiddlePosLeftBtn;
    // private static JoystickButton m_joystickSetArmMiddlePosRightBtn;
    // private static JoystickButton m_joystickSetArmBottomPosBtn;
    // private static JoystickButton m_joystickExtendArmBtn;
    // private static JoystickButton m_joystickRetractArmBtn;
    
    // operator controller buttons
    private static JoystickButton m_grabberBtn;
    private static JoystickButton m_autoBalanceBtn;
    private static JoystickButton m_setAndHoldPosBtn;
    private static JoystickButton m_setArmTopPosBtn;
    private static JoystickButton m_setArmMiddlePosBtn;
    private static JoystickButton m_setArmBottomPosBtn;
    private static POVButton m_extendArmPOVBtn;
    private static POVButton m_retractArmPOVBtn;

    private static JoystickButton m_grabberCloseTempBtn;
    private static JoystickButton m_grabberOpenTempBtn;
    
    // Commands
    private Autobalance m_autoBalanceCommandClass = new Autobalance();

    public OI(){
        // Joysticks/Controllers
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        // m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
        m_operatorController = new Joystick(Constants.OPERATOR_CONTROLLER_PORT);
        
        // Driver buttons
        m_gearShiftBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GEAR_SHIFT_BTN_ID);
        
        // Operator joystick buttons
        // m_joystickGrabberBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_GRABBER_TOGGLE_BTN_ID);
        // m_joystickAutoBalanceBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_AUTOBALANCE_BTN_ID);
        // m_joystickSetAndHoldPoseBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_HOLD_POSE_TOGLE_BTN_ID);
        // m_joystickSetArmTopPosBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_TOP_POSE_BTN_ID);
        // m_joystickSetArmMiddlePosLeftBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_MIDDLE_POSE_LEFT_BTN_ID);
        // m_joystickSetArmMiddlePosRightBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_MIDDLE_POSE_RIGHT_BTN_ID);
        // m_joystickSetArmBottomPosBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_BOTTOM_POSE_BTN_ID);
        // m_joystickExtendArmBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_EXTEND_ARM_BTN_ID);
        // m_joystickRetractArmBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_RETRACT_ARM_BTN_ID);

        // Operator controller buttons
        // A     - 1
        // B     - 2
        // X     - 3
        // Y     - 4
        // LB    - 5
        // RB    - 6
        // Back  - 7
        // Start - 8
        m_grabberBtn = new JoystickButton(m_operatorController, 2);
        m_autoBalanceBtn = new JoystickButton(m_operatorController, 7);
        m_setAndHoldPosBtn = new JoystickButton(m_operatorController, 8);
        m_setArmTopPosBtn = new JoystickButton(m_operatorController, 4);
        m_setArmMiddlePosBtn = new JoystickButton(m_operatorController, 3);
        m_setArmBottomPosBtn = new JoystickButton(m_operatorController, 1);
        
        m_extendArmPOVBtn = new POVButton(m_operatorController, 0);
        m_retractArmPOVBtn = new POVButton(m_operatorController, 180);

        m_grabberCloseTempBtn = new JoystickButton(m_operatorController, 6);
        m_grabberOpenTempBtn = new JoystickButton(m_operatorController, 5);

    }

    // Used for driving command
    public Joystick getDriverJoystick(){
        return m_driverJoystick;
    }

    // Used for arm control command
    // public Joystick getOperatorJoystick(){
    //     return m_operatorJoystick;
    // }

    // Used for arm control command
    public Joystick getOperatorController(){
        return m_operatorController;
    }

    // returns if a controller is connected to port 2, used for deciding which controller to use for arm control
    public boolean isController(){
        return(DriverStation.isJoystickConnected(Constants.OPERATOR_CONTROLLER_PORT));
    }

    // used in robot.java to determine weather to set default commands or not
    public boolean isDriverConnected(){
        return(DriverStation.isJoystickConnected(Constants.DRIVER_JOYSTICK_PORT));
    }
    public boolean isOperatorConnected(){
        return(DriverStation.isJoystickConnected(Constants.OPERATOR_CONTROLLER_PORT) || DriverStation.isJoystickConnected(Constants.OPERATOR_JOYSTICK_PORT));
    }

    public void configureButtonBindings(){
        // WestCoastDrive
        // m_gearShiftBtn.onTrue(RobotContainer.m_inlineCommands.m_shiftHighGear);
        // m_gearShiftBtn.onFalse(RobotContainer.m_inlineCommands.m_shiftLowGear);
        
        // m_joystickAutoBalanceBtn.onTrue(m_autoBalanceCommandClass);
        m_autoBalanceBtn.onTrue(m_autoBalanceCommandClass);
        
        // m_joystickSetAndHoldPoseBtn.onTrue(RobotContainer.m_inlineCommands.m_setAndHoldPose);
        // m_setAndHoldPosBtn.onTrue(RobotContainer.m_inlineCommands.m_setAndHoldPose);
        
        // Grabber
        // m_joystickGrabberBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleGrabber);
        m_grabberBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleGrabber);
        
        // Arm
        // m_joystickSetArmTopPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armTopPos);
        m_setArmTopPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armTopPos);
        
        // m_joystickSetArmMiddlePosLeftBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        // m_joystickSetArmMiddlePosRightBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        m_setArmMiddlePosBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        
        // m_joystickSetArmBottomPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armBottomPos);
        m_setArmBottomPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armBottomPos);
        
        m_extendArmPOVBtn.whileTrue(RobotContainer.m_inlineCommands.m_extendArm).onFalse(RobotContainer.m_inlineCommands.m_extenderHalt);
        m_retractArmPOVBtn.whileTrue(RobotContainer.m_inlineCommands.m_retractArm).onFalse(RobotContainer.m_inlineCommands.m_extenderHalt);

        m_grabberOpenTempBtn.onTrue(RobotContainer.m_inlineCommands.m_grabberOpen).onFalse(RobotContainer.m_inlineCommands.m_grabberHalt);
        m_grabberCloseTempBtn.onTrue(RobotContainer.m_inlineCommands.m_grabberClose).onFalse(RobotContainer.m_inlineCommands.m_grabberHalt);
        
        // (m_grabberCloseTempBtn && m_grabberOpenTempBtn).whileFalse(RobotContainer.m_inlineCommands.m_grabberHalt);
    }
}