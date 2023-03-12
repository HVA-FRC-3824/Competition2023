package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/* Inline commands allow the creation of new commands without a new CommandBase file.
 * Usage: single/double commands (Example: extending a piston)
 * Can be used in other files (other commands or OI.java for binding commands to buttons).
 * For chains of commands (Example: ten-ball autonomous command sequence), create a separate CommandBase/CommandGroup file. */
public class InlineCommands{
    // WestCoast drive
    public final Command m_driveWithJoystick;
    public final Command m_shiftHighGear;
    public final Command m_shiftLowGear;
    public final Command m_setAndHoldPose;

    // Grabber
    public final Command m_toggleGrabber;
    public final Command m_grabberOpen;
    public final Command m_grabberClose;
    public final Command m_grabberHalt;

    // Arm
    public final Command m_armTopPos;
    public final Command m_armMiddlePos;
    public final Command m_armBottomPos;
    public final Command m_extendArm;
    public final Command m_retractArm;
    public final Command m_extenderHalt;


    public InlineCommands(){
        // WestCoast
        m_driveWithJoystick = new RunCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.drive(RobotContainer.m_OI.getDriverJoystick().getY(), 
                                                   RobotContainer.m_OI.getDriverJoystick().getZ()), RobotContainer.M_WEST_COAST_DRIVE);
        m_shiftHighGear = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.shiftHighGear());
        m_shiftLowGear = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.shiftLowGear());
        m_setAndHoldPose = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.setAndHoldPose());

        // Grabber
        m_toggleGrabber = new InstantCommand(() -> RobotContainer.M_GRABBER.toggleGrabber());
        m_grabberOpen = new InstantCommand(() -> RobotContainer.M_GRABBER.grabberSetVoltage(3.75));
        m_grabberClose = new InstantCommand(() -> RobotContainer.M_GRABBER.grabberSetVoltage(-3.75));
        m_grabberHalt = new InstantCommand(() -> RobotContainer.M_GRABBER.grabberSetVoltage(0));

        // Arm
        m_armTopPos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmTop());
        m_armMiddlePos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmMiddle());
        m_armBottomPos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmBotton());
        m_extendArm = new InstantCommand(() -> RobotContainer.M_ARM.extendArm());
        m_retractArm = new InstantCommand(() -> RobotContainer.M_ARM.retractArm());
        m_extenderHalt = new InstantCommand(() -> RobotContainer.M_ARM.haltArm());
    }
}