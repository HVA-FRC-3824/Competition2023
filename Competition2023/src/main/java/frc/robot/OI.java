package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.ArmExtension;
import frc.robot.commands.ArmRetraction;
import frc.robot.commands.Autobalance;
import frc.robot.commands.SetAndHoldPosition;

public class OI{
    // joysticks
    private static Joystick driverController;
    private static Joystick operatorController;
    
    // driver joystick buttons
    private static JoystickButton toggleDriveCentricityBtn;
    private static JoystickButton autoBalanceBtn;
    private static JoystickButton toggleSetAndHoldPosBtn;
    private static JoystickButton toggleDrivePowerBtn;
    private static JoystickButton jukeSpeedModeBtn;
    
    // operator controller buttons
    // private static JoystickButton grabberBtn;
    private static JoystickButton grabberCloseBtn;
    private static JoystickButton grabberOpenBtn;

    private static JoystickButton setArmTopPosBtn;
    private static JoystickButton setArmMiddlePosBtn;
    private static JoystickButton setArmBottomPosBtn;
    private static POVButton extendArmPOVBtn;
    private static POVButton retractArmPOVBtn;

    private static JoystickButton toggleArmExtensionLimiterBtn;

    // COMMANDS
    private Autobalance autoBalanceCommandClass = new Autobalance();
    private SetAndHoldPosition setAndHoldPositionCommandClass = new SetAndHoldPosition();
    private ArmExtension armExtensionCommandClass = new ArmExtension();
    private ArmRetraction armRetractionCommandClass = new ArmRetraction();

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
        autoBalanceBtn = new JoystickButton(driverController, 6);           // RB
        jukeSpeedModeBtn = new JoystickButton(driverController, 5);         // LB 
        toggleSetAndHoldPosBtn = new JoystickButton(driverController, 4);   // Y
        toggleDrivePowerBtn = new JoystickButton(driverController, 1);      // A


        // OPERATOR BUTTONS
        // grabberBtn = new JoystickButton(operatorController, 2);             // B
        grabberCloseBtn = new JoystickButton(operatorController, 6);        // RB
        grabberOpenBtn = new JoystickButton(operatorController, 5);         // LB

        setArmTopPosBtn = new JoystickButton(operatorController, 4);        // Y
        setArmMiddlePosBtn = new JoystickButton(operatorController, 2);     // B
        setArmBottomPosBtn = new JoystickButton(operatorController, 1);     // A
        extendArmPOVBtn = new POVButton(operatorController, 0);                    // Dpad up
        retractArmPOVBtn = new POVButton(operatorController, 180);                 // Dpad down
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
        // WestCoastDrive
        autoBalanceBtn.onTrue(autoBalanceCommandClass);
        toggleDriveCentricityBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.toggleDriveCentricity);
        toggleSetAndHoldPosBtn.toggleOnTrue(setAndHoldPositionCommandClass); //TODO check toggle?
        toggleDrivePowerBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.toggleDrivePower);
        jukeSpeedModeBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.jukeSpeed).onFalse(RobotContainer.INLINE_COMMANDS_OBJ.normalSpeed);

        // Grabber
        // grabberBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.toggleGrabber);
        grabberOpenBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.grabberOpen).onFalse(RobotContainer.INLINE_COMMANDS_OBJ.grabberStop);
        grabberCloseBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.grabberClose).onFalse(RobotContainer.INLINE_COMMANDS_OBJ.grabberStop);

        // Arm
        setArmTopPosBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.armTopPos);
        setArmMiddlePosBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.armMiddlePos);
        setArmBottomPosBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.armBottomPos);
        
        extendArmPOVBtn.onTrue(armExtensionCommandClass).onFalse(RobotContainer.INLINE_COMMANDS_OBJ.extenderStop);
        retractArmPOVBtn.onTrue(armRetractionCommandClass).onFalse(RobotContainer.INLINE_COMMANDS_OBJ.extenderStop);

        toggleArmExtensionLimiterBtn.onTrue(RobotContainer.INLINE_COMMANDS_OBJ.toggleExtensionLimiter);
    }
}