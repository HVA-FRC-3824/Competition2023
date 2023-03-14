package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/* Inline commands allow the creation of new commands without a new CommandBase file.
 * Usage: single/double commands (Example: extending a piston)
 * Can be used in other files (other commands or OI.java for binding commands to buttons).
 * For chains of commands (Example: ten-ball autonomous command sequence), create a separate CommandBase/CommandGroup file. */
public class InlineCommands{
    // Swerve Drive
    public final Command driveWithJoystick;
    public final Command toggleDriveCentricity;

    // Grabber
    //public final Command toggleGrabber;
    public final Command grabberOpen;
    public final Command grabberClose;
    public final Command grabberHalt;
    public final Command grabberHold;

    // Arm
    public final Command angleArmWithController;
    public final Command armTopPos;
    public final Command armMiddlePos;
    public final Command armBottomPos;
    public final Command extendArm;
    public final Command retractArm;
    public final Command extenderHalt;

    public InlineCommands(){
        // SwerveDrive
        driveWithJoystick = new RunCommand(() -> 
            RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(
            RobotContainer.OI_OBJ.getDriverController().getRawAxis(0), 
            RobotContainer.OI_OBJ.getDriverController().getRawAxis(1), 
            RobotContainer.OI_OBJ.getDriverController().getRawAxis(4)), 
            RobotContainer.SWERVE_DRIVE_OBJ);
        toggleDriveCentricity = new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.toggleDriveCentricity());

        // Grabber
        //toggleGrabber = new InstantCommand(() -> RobotContainer.GRABBER.toggleGrabber());
        grabberOpen = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(Constants.GRABBER_VOLTAGE));
        grabberClose = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(-Constants.GRABBER_VOLTAGE));
        grabberHalt = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(0));
        grabberHold = new InstantCommand(() -> RobotContainer.GRABBER_OBJ.grabberSetVoltage(-1));

        // Arm
        angleArmWithController = new RunCommand(() -> 
            RobotContainer.ARM_OBJ.setDesiredArmPosition(
            RobotContainer.OI_OBJ.getOperatorController().getY()), 
            RobotContainer.ARM_OBJ);
        armTopPos = new InstantCommand(() -> RobotContainer.ARM_OBJ.extendArmTop());
        armMiddlePos = new InstantCommand(() -> RobotContainer.ARM_OBJ.extendArmMiddle());
        armBottomPos = new InstantCommand(() -> RobotContainer.ARM_OBJ.extendArmBotton());
        
        extendArm = new InstantCommand(() -> RobotContainer.ARM_OBJ.extendArm());
        retractArm = new InstantCommand(() -> RobotContainer.ARM_OBJ.retractArm());
        extenderHalt = new InstantCommand(() -> RobotContainer.ARM_OBJ.stopArm());
    }
}