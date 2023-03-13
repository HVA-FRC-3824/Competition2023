package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.Autobalance;
import frc.robot.commands.SetAndHoldPosition;

public class OI{
    // joysticks
    private static Joystick m_driverController;
    private static Joystick m_operatorController;
    
    // driver joystick buttons
    private static JoystickButton m_toggleDriveCentricityBtn;
    private static JoystickButton m_autoBalanceBtn;
    private static JoystickButton m_toggleSetAndHoldPosBtn;
    private static JoystickButton m_toggleDefenseModeBtn;
    
    // operator controller buttons
    // private static JoystickButton m_grabberBtn;
    private static JoystickButton m_grabberCloseBtn;
    private static JoystickButton m_grabberOpenBtn;

    private static JoystickButton m_setArmTopPosBtn;
    private static JoystickButton m_setArmMiddlePosBtn;
    private static JoystickButton m_setArmBottomPosBtn;
    private static POVButton m_extendArmPOVBtn;
    private static POVButton m_retractArmPOVBtn;

    // COMMANDS
    private Autobalance m_autoBalanceCommandClass = new Autobalance();
    private SetAndHoldPosition m_setAndHoldPositionClass = new SetAndHoldPosition();

    public OI(){
        // CONTROLLERS
        m_driverController = new Joystick(Constants.DRIVER_CONTROLLER_PORT);
        m_operatorController = new Joystick(Constants.OPERATOR_CONTROLLER_PORT);
        
        // CONTROLER BUTTON IDS
        // A     - 1
        // B     - 2
        // X     - 3
        // Y     - 4
        // LB    - 5
        // RB    - 6
        // Back  - 7
        // Start - 8

        // DRIVER BUTTONS
        m_toggleDriveCentricityBtn = new JoystickButton(m_driverController, 5); // LB
        m_autoBalanceBtn = new JoystickButton(m_driverController, 6);           // RB
        m_toggleSetAndHoldPosBtn = new JoystickButton(m_driverController, 4);   // Y
        m_toggleDefenseModeBtn = new JoystickButton(m_driverController, 1);     // A

        // OPERATOR BUTTONS
        // m_grabberBtn = new JoystickButton(m_operatorController, 2);             // B
        m_grabberCloseBtn = new JoystickButton(m_operatorController, 6);        // RB
        m_grabberOpenBtn = new JoystickButton(m_operatorController, 5);         // LB

        m_setArmTopPosBtn = new JoystickButton(m_operatorController, 4);        // Y
        m_setArmMiddlePosBtn = new JoystickButton(m_operatorController, 3);     // X
        m_setArmBottomPosBtn = new JoystickButton(m_operatorController, 1);     // A
        
        m_extendArmPOVBtn = new POVButton(m_operatorController, 0);                    // Dpad up
        m_retractArmPOVBtn = new POVButton(m_operatorController, 180);                 // Dpad down
    }

    // Used for driving command
    public Joystick getDriverController(){
        return m_driverController;
    }

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
        return(DriverStation.isJoystickConnected(Constants.DRIVER_CONTROLLER_PORT));
    }
    public boolean isOperatorConnected(){
        return(DriverStation.isJoystickConnected(Constants.OPERATOR_CONTROLLER_PORT) || DriverStation.isJoystickConnected(Constants.OPERATOR_CONTROLLER_PORT));
    }

    public void configureButtonBindings(){
        // WestCoastDrive
        m_autoBalanceBtn.onTrue(m_autoBalanceCommandClass);
        m_toggleDriveCentricityBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleDriveCentricity);
        m_toggleSetAndHoldPosBtn.toggleOnTrue(m_setAndHoldPositionClass);
        m_toggleDefenseModeBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleDefenseMode);

        // Grabber
        //m_grabberBtn.onTrue(RobotContainer.m_inlineCommands.m_toggleGrabber);
        m_grabberOpenBtn.onTrue(RobotContainer.m_inlineCommands.m_grabberOpen).onFalse(RobotContainer.m_inlineCommands.m_grabberHalt);
        m_grabberCloseBtn.onTrue(RobotContainer.m_inlineCommands.m_grabberClose).onFalse(RobotContainer.m_inlineCommands.m_grabberHalt);

        // Arm
        m_setArmTopPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armTopPos);
        m_setArmMiddlePosBtn.onTrue(RobotContainer.m_inlineCommands.m_armMiddlePos);
        m_setArmBottomPosBtn.onTrue(RobotContainer.m_inlineCommands.m_armBottomPos);
        
        m_extendArmPOVBtn.whileTrue(RobotContainer.m_inlineCommands.m_extendArm).onFalse(RobotContainer.m_inlineCommands.m_extenderHalt);
        m_retractArmPOVBtn.whileTrue(RobotContainer.m_inlineCommands.m_retractArm).onFalse(RobotContainer.m_inlineCommands.m_extenderHalt);
    }
}