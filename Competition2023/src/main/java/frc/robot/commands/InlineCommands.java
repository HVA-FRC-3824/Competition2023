package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LEDs.LEDsPattern;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/* Inline commands allow the creation of new commands without a new CommandBase file.
 * Usage: single/double commands (Example: extending a piston)
 * Can be used in other files (other commands or OI.java for binding commands to buttons).
 * For chains of commands (Example: ten-ball autonomous command sequence), create a separate CommandBase/CommandGroup file. */
public class InlineCommands{
    // Swerve Drive
    public final Command driveWithJoystick;
    public final Command toggleDriveCentricity;
    public final Command toggleDrivePower;
    public final Command jukeSpeed;
    public final Command normalSpeed;

    // Grabber
    // public final Command toggleGrabber;
    public final Command grabberOpen;
    public final Command grabberClose;
    public final Command grabberStop;

    // Arm
    public final Command angleArmWithController;
    public final Command extendArmWithController;
    public final Command armTopPos;
    public final Command armMiddlePos;
    public final Command armBottomPos;
    public final Command extendArm;
    public final Command retractArm;
    public final Command extenderStop;
    public final Command toggleExtensionLimiter;

    // LEDs
    public final Command setPurpleLED;
    public final Command setYellowLED;
    public final Command setNormalLED;

    public InlineCommands(){
        // SwerveDrive
        driveWithJoystick = new RunCommand(() -> 
            RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(
            RobotContainer.OI_OBJ.getDriverController().getRawAxis(0), 
            RobotContainer.OI_OBJ.getDriverController().getRawAxis(1), 
            RobotContainer.OI_OBJ.getDriverController().getRawAxis(4)), 
            RobotContainer.SWERVE_DRIVE_OBJ);
        toggleDriveCentricity = new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.toggleDriveCentricity());
        toggleDrivePower = new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.toggleDrivePower());
        jukeSpeed = new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.jukeSpeedMode());
        normalSpeed = new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.normalSpeedMode());

        // Grabber
        // toggleGrabber = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.toggleGrabber());
        grabberOpen = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(Constants.GRABBER_VOLTAGE));
        grabberClose = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(-Constants.GRABBER_VOLTAGE));
        grabberStop = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(0));

        // Arm Angle
        angleArmWithController = new RunCommand(() -> 
            RobotContainer.ARM_ANGLE_OBJ.setDesiredArmPosition(
            RobotContainer.OI_OBJ.getOperatorController().getRawAxis(1)), 
            RobotContainer.ARM_ANGLE_OBJ);
        
        toggleExtensionLimiter = new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.toggleExtensionLimiter());
        // Arm Extension
        extendArmWithController = new RunCommand(() ->
            RobotContainer.ARM_EXTENSION_OBJ.extendAndRetractArm(
            RobotContainer.OI_OBJ.getOperatorController().getRawAxis(5)), 
            RobotContainer.ARM_EXTENSION_OBJ); 
        armTopPos = new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.extendArmTop());
        armMiddlePos = new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.extendArmMiddle());
        armBottomPos = new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.extendArmBottom());
        
        extendArm = new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.extendArm());
        retractArm = new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.retractArm());
        extenderStop = new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.stopArmExtension());
        
        // LED Buttons
        setPurpleLED = new InstantCommand(() -> RobotContainer.LEDS_OBJ.setLEDsColor(60, 100, 100));
        setYellowLED = new InstantCommand(() -> RobotContainer.LEDS_OBJ.setLEDsColor(300, 100, 50));
        setNormalLED = new InstantCommand(() -> RobotContainer.LEDS_OBJ.setLEDsPattern(LEDsPattern.RAINBOW));
    
    }
}