package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

// import frc.robot.commands.Autobalance;

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
        // Select/left middle   - 7
        // Start/right middle - 8

        // DRIVER BUTTONS
        toggleDriveCentricityBtn = new JoystickButton(driverController, 7); // Select left middle
        // autoBalanceBtn = new JoystickButton(driverController, 5);           // LB
        jukeSpeedModeBtn = new JoystickButton(driverController, 6);         // RB 
        toggleDrivePowerBtn = new JoystickButton(driverController, 1);      // A
        xLockWheelsBtn = new JoystickButton(driverController, 2); // B


        // OPERATOR BUTTONS
        setArmTopPosBtn = new JoystickButton(operatorController, 4);        // Y
        setArmMiddlePosBtn = new JoystickButton(operatorController, 2);     // B
        setArmBottomPosBtn = new JoystickButton(operatorController, 1);     // A

        purpleCubeBtn = new POVButton(operatorController, 270);
        yellowConeBtn = new POVButton(operatorController, 90);
        normalLEDBtn = new POVButton(operatorController, 0);

        toggleArmExtensionLimiterBtn = new JoystickButton(operatorController, 3); // X
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
        xLockWheelsBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.xLockWheels);

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