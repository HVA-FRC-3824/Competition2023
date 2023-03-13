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
    public final Command m_driveWithJoystick;
    public final Command m_toggleDriveCentricity;
    public final Command m_toggleDefenseMode;

    // Grabber
    //public final Command m_toggleGrabber;
    public final Command m_grabberOpen;
    public final Command m_grabberClose;
    public final Command m_grabberHalt;

    // Arm
    public final Command m_angleArmWithController;
    public final Command m_armTopPos;
    public final Command m_armMiddlePos;
    public final Command m_armBottomPos;
    public final Command m_extendArm;
    public final Command m_retractArm;
    public final Command m_extenderHalt;

    public InlineCommands(){
        // SwerveDrive
        m_driveWithJoystick = new RunCommand(() -> 
            RobotContainer.M_SWERVE_DRIVE.convertSwerveValues(
            RobotContainer.m_OI.getDriverController().getRawAxis(0), 
            RobotContainer.m_OI.getDriverController().getRawAxis(1), 
            RobotContainer.m_OI.getDriverController().getRawAxis(4)), 
            RobotContainer.M_SWERVE_DRIVE);
        m_toggleDriveCentricity = new InstantCommand(() -> RobotContainer.M_SWERVE_DRIVE.toggleDriveCentricity());
        m_toggleDefenseMode = new InstantCommand(() -> RobotContainer.M_SWERVE_DRIVE.toggleDefenseMode());

        // Grabber
        //m_toggleGrabber = new InstantCommand(() -> RobotContainer.M_GRABBER.toggleGrabber());
        m_grabberOpen = new InstantCommand(() -> RobotContainer.M_GRABBER.grabberSetVoltage(Constants.GRABBER_VOLTAGE));
        m_grabberClose = new InstantCommand(() -> RobotContainer.M_GRABBER.grabberSetVoltage(-Constants.GRABBER_VOLTAGE));
        m_grabberHalt = new InstantCommand(() -> RobotContainer.M_GRABBER.grabberSetVoltage(0));

        // Arm
        m_angleArmWithController = new RunCommand(() -> 
            RobotContainer.M_ARM.setDesiredArmPosition(
            RobotContainer.m_OI.getOperatorController().getY()), 
            RobotContainer.M_ARM);
        m_armTopPos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmTop());
        m_armMiddlePos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmMiddle());
        m_armBottomPos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmBotton());
        
        m_extendArm = new InstantCommand(() -> RobotContainer.M_ARM.extendArm());
        m_retractArm = new InstantCommand(() -> RobotContainer.M_ARM.retractArm());
        m_extenderHalt = new InstantCommand(() -> RobotContainer.M_ARM.stopArm());
    }
}