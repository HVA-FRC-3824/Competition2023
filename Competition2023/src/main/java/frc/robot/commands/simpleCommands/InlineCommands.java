package frc.robot.commands.simpleCommands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/* Inline commands allow the creation of new commands without a new CommandBase file.
 * Usage: single/double commands (Example: extending a piston)
 * Can be used in other files (other commands or OI.java for binding commands to buttons).
 * For chains of commands (Example: ten-ball autonomous command sequence), create a separate CommandBase/CommandGroup file. */
public class InlineCommands{
    // SWERVE DRIVE
    public final Command driveWithJoystick;
    public final Command toggleDriveCentricity;
    public final Command toggleDrivePower;
    public final Command jukeSpeed;
    public final Command normalSpeed;
    // public final Command zeroAngleMotors;

    // GRABBER
    public final Command grabberIntake;
    public final Command grabberRelease;
    public final Command grabberStop;

    // ARM
    public final Command angleArmWithController;
    public final Command extendArmWithController;
    public final Command toggleLimiters;

    public final Command scoreTopPos;
    public final Command scoreMiddlePos;
    public final Command pickUpPos;

    // LEDS
    public final Command setPurpleLED;
    public final Command setYellowLED;
    public final Command setNormalLED;

    public InlineCommands(){
        // SWERVE DRIVE
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
        // zeroAngleMotors = new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.zeroWheelsWithABSEncoders());
        
        // GRABBER
        grabberIntake = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grab());
        grabberStop = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.stop());
        grabberRelease = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.realese());
        
        // ARM ANGLE
        angleArmWithController = new RunCommand(() -> 
            RobotContainer.ARM_ANGLE_OBJ.setDesiredArmPosition(
            RobotContainer.OI_OBJ.getOperatorController().getRawAxis(1)), 
            RobotContainer.ARM_ANGLE_OBJ);

        // ARM ANGLE AND EXTENSION
        scoreTopPos = new InstantCommand(() -> RobotContainer.ARM_ANGLE_OBJ.setArmAngleTopScoringPos())
              .alongWith(new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.extendArmTop()));
        scoreMiddlePos = new InstantCommand(() -> RobotContainer.ARM_ANGLE_OBJ.setArmAngleMiddleScoringPos())
              .alongWith(new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.extendArmMiddle()));
        pickUpPos = new InstantCommand(() -> RobotContainer.ARM_ANGLE_OBJ.setArmAnglePickUpPos())
              .alongWith(new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.extendArmPickUp()));
        toggleLimiters = new InstantCommand(() -> RobotContainer.ARM_ANGLE_OBJ.toggleAngleLimiter())
              .alongWith(new InstantCommand(() -> RobotContainer.ARM_EXTENSION_OBJ.toggleExtensionLimiter()));
        
        // ARM EXTENSION
        extendArmWithController = new RunCommand(() ->
            RobotContainer.ARM_EXTENSION_OBJ.extendAndRetractArm(
            RobotContainer.OI_OBJ.getOperatorController().getRawAxis(5)), 
            RobotContainer.ARM_EXTENSION_OBJ); 
        
        // LEDS
        setPurpleLED = new InstantCommand(() -> RobotContainer.LEDS_OBJ.setLEDsColor(60, 100, 100));
        setYellowLED = new InstantCommand(() -> RobotContainer.LEDS_OBJ.setLEDsColor(300, 100, 50));
        setNormalLED = new InstantCommand(() -> RobotContainer.LEDS_OBJ.setLEDsColor(247, 83, 88));
    }
}