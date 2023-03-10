package frc.robot;

import java.lang.ModuleLayer.Controller;

// Joystick and Button
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// Controller, axis and button
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;

// Commands
import frc.robot.commands.Autobalance;

public class OI{
    // joysticks
    private static Joystick m_driverJoystick;
    private static Joystick m_operatorJoystick;
    private static Joystick m_operatorController;
    
    // driver joystick buttons
    private static JoystickButton m_gearShiftBtn;
    
    // operator joystick buttons
    private static JoystickButton m_joystickGrabberBtn;
    private static JoystickButton m_joystickAutoBalanceBtn;
    private static JoystickButton m_joystickSetAndHoldPoseBtn;
    private static JoystickButton m_joystickSetArmTopPosBtn;
    private static JoystickButton m_joystickSetArmMiddlePosLeftBtn;
    private static JoystickButton m_joystickSetArmMiddlePosRightBtn;
    private static JoystickButton m_joystickSetArmBottomPosBtn;
    private static JoystickButton m_joystickExtendArmBtn;
    private static JoystickButton m_joystickRetractArmBtn;
    
    // operator controller buttons
    private static JoystickButton m_grabberBtn;
    private static JoystickButton m_autoBalanceBtn;
    private static JoystickButton m_setAndHoldPosBtn;
    private static JoystickButton m_setArmTopPosBtn;
    private static JoystickButton m_setArmMiddlePosBtn;
    private static JoystickButton m_setArmBottomPosBtn;
    private static JoystickButton m_extendArmBtn;
    private static JoystickButton m_retractArmBtn;
    
    // Commands
    private Autobalance m_autoBalanceCommandClass = new Autobalance();

    public OI(){
        // Joysticks/Controllers
        m_driverJoystick = new Joystick(Constants.DRIVER_JOYSTICK_PORT);
        m_operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
        m_operatorController = new Joystick(Constants.OPERATOR_CONTROLLER_PORT);
        
        // Driver buttons
        m_gearShiftBtn = new JoystickButton(m_driverJoystick, Constants.DRIVER_GEAR_SHIFT_BTN_ID);
        
        // Operator joystick buttons
        m_joystickGrabberBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_GRABBER_TOGGLE_BTN_ID);
        m_joystickAutoBalanceBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_AUTOBALANCE_BTN_ID);
        m_joystickSetAndHoldPoseBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_HOLD_POSE_TOGLE_BTN_ID);
        m_joystickSetArmTopPosBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_TOP_POSE_BTN_ID);
        m_joystickSetArmMiddlePosLeftBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_MIDDLE_POSE_LEFT_BTN_ID);
        m_joystickSetArmMiddlePosRightBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_MIDDLE_POSE_RIGHT_BTN_ID);
        m_joystickSetArmBottomPosBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_SET_ARM_BOTTOM_POSE_BTN_ID);
        m_joystickExtendArmBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_EXTEND_ARM_BTN_ID);
        m_joystickRetractArmBtn = new JoystickButton(m_operatorJoystick, Constants.OPERATOR_RETRACT_ARM_BTN_ID);

        // Operator controller buttons
        // TODO set button numbers 
        // A     - 1
        // B     - 2
        // X     - 3
        // Y     - 4
        // LB    - 5
        // RB    - 6
        // Back  - 7
        // Start - 8
        m_grabberBtn = new JoystickButton(m_operatorController, 0);
        m_autoBalanceBtn = new JoystickButton(m_operatorController, 0);
        m_setAndHoldPosBtn = new JoystickButton(m_operatorController, 0);
        m_setArmTopPosBtn = new JoystickButton(m_operatorController, 0);
        m_setArmMiddlePosBtn = new JoystickButton(m_operatorController, 0);
        m_setArmBottomPosBtn = new JoystickButton(m_operatorController, 0);
        m_extendArmBtn = new JoystickButton(m_operatorController, 0);
        m_retractArmBtn = new JoystickButton(m_operatorController, 0);
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
        
        m_joystickAutoBalanceBtn.onTrue(m_autoBalanceCommandClass);
        m_autoBalanceBtn.onTrue(m_autoBalanceCommandClass);
        
        m_joystickSetAndHoldPoseBtn.onTrue(RobotContainer.m_inlineCommands.m_setAndHoldPose);
        m_setAndHoldPosBtn.onTrue(RobotContainer.m_inlineCommands.m_setAndHoldPose);
        
        // Grabber
        m_joystickGrabberBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleGrabber);
        m_grabberBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleGrabber);
        
        // Arm
        m_joystickSetArmTopPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armTopPos);
        m_setArmTopPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armTopPos);
        
        m_joystickSetArmMiddlePosLeftBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        m_joystickSetArmMiddlePosRightBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        m_setArmMiddlePosBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        
        m_joystickSetArmBottomPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armBottomPos);
        m_setArmBottomPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armBottomPos);
        
        m_joystickExtendArmBtn.whileTrue(RobotContainer.m_inlineCommands.m_extendArm);
        m_extendArmBtn.whileTrue(RobotContainer.m_inlineCommands.m_extendArm);
        
        m_joystickRetractArmBtn.whileTrue(RobotContainer.m_inlineCommands.m_retractArm);
        m_retractArmBtn.whileTrue(RobotContainer.m_inlineCommands.m_retractArm);
    }
}