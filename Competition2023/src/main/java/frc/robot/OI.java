package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.simpleCommands.xWheelsLock;

public class OI{
    // joysticks
    private static Joystick driverController;
    private static Joystick operatorController;
    
    // driver joystick buttons
    private static JoystickButton toggleDriveCentricityBtn;
    // private static JoystickButton autoBalanceBtn;
    private static JoystickButton toggleDrivePowerBtn;
    private static JoystickButton jukeSpeedModeBtn;
    private static JoystickButton xLockWheelsBtn;

    // operator controller buttons

    private static JoystickButton setArmTopPosBtn;
    private static JoystickButton setArmMiddlePosBtn;
    private static JoystickButton setArmBottomPosBtn;

    private static POVButton purpleCubeBtn;
    private static POVButton yellowConeBtn;
    private static POVButton normalLEDBtn;

    private static JoystickButton toggleArmExtensionLimiterBtn;

    // COMMANDS
    // private Autobalance autoBalanceCommandClass = new Autobalance();

    public OI(){
        // CONTROLLERS
        driverController = new Joystick(Constants.DRIVER_CONTROLLER_PORT);
        operatorController = new Joystick(Constants.OPERATOR_CONTROLLER_PORT);
        
        // CONTROLER BUTTON IDS
        // A                  - 1
        // B                  - 2
        // X                  - 3
        // Y                  - 4
        // LB                 - 5
        // RB                 - 6
        // Select/left middle - 7
        // Start/right middle - 8

        // DRIVER BUTTONS
        toggleDriveCentricityBtn = new JoystickButton(driverController, Constants.TOGGLE_DRIVE_CENTRICITY_BTN_ID); // Select
        // autoBalanceBtn = new JoystickButton(driverController, 5);           // LB
        jukeSpeedModeBtn = new JoystickButton(driverController, Constants.JUKE_SPEED_MODE_BTN_ID);          // RB       
        toggleDrivePowerBtn = new JoystickButton(driverController, Constants.TOGGLE_DRIVE_POWER_BTN_ID);    // A   
        xLockWheelsBtn = new JoystickButton(driverController, Constants.XLOCK_WHEELS_BTN_ID);               // B

        // OPERATOR BUTTONS
        setArmTopPosBtn = new JoystickButton(operatorController, Constants.SET_ARM_TOP_POS_BTN_ID);        // Y
        setArmMiddlePosBtn = new JoystickButton(operatorController, Constants.SET_ARM_MIDDLE_POS_BTN_ID);  // B
        setArmBottomPosBtn = new JoystickButton(operatorController, Constants.SET_ARM_BOTTOM_POS_BTN_ID);  // A

        purpleCubeBtn = new POVButton(operatorController, Constants.PURPLE_CUBE_BTN_ID);                   // right
        yellowConeBtn = new POVButton(operatorController, Constants.YELLOW_CONE_BTN_ID);                   // left
        normalLEDBtn = new POVButton(operatorController, Constants.NORMAL_LED_BTN_ID);                     // up

        toggleArmExtensionLimiterBtn = new JoystickButton(operatorController, Constants.TOGGLE_ARM_EXTENSION_LIMITER_BTN_ID);
    }

    // Used for driving command
    public Joystick getDriverController(){
        return driverController;
    }

    // Used for arm control command
    public Joystick getOperatorController(){
        return operatorController;
    }

    public void configureButtonBindings(){
        // Swerve Drive
        // autoBalanceBtn.onTrue(autoBalanceCommandClass);
        toggleDriveCentricityBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.toggleDriveCentricity);
        toggleDrivePowerBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.toggleDrivePower);
        jukeSpeedModeBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.jukeSpeed).onFalse(RobotContainer.INLINE_COMMANDS_OBJ.normalSpeed);
        xLockWheelsBtn.onTrue(new xWheelsLock(RobotContainer.SWERVE_DRIVE_OBJ)).onFalse(RobotContainer.INLINE_COMMANDS_OBJ.driveWithJoystick);

        // Grabber

        // Arm
        setArmTopPosBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.armTopPos);
        setArmMiddlePosBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.armMiddlePos);
        setArmBottomPosBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.armBottomPos);

        toggleArmExtensionLimiterBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.toggleExtensionLimiter);
        
        // LEDs
        purpleCubeBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.setPurpleLED);
        yellowConeBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.setYellowLED);
        normalLEDBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.setNormalLED);
    }
}