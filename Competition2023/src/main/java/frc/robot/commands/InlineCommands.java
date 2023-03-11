package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
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

    // Arm
    public final Command m_angleArmWithJoystick;
    public final Command m_armTopPos;
    public final Command m_armMiddlePos;
    public final Command m_armBottomPos;
    public final Command m_extendArm;
    public final Command m_retractArm;

    public InlineCommands(){
        // WestCoast
        m_driveWithJoystick = new RunCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.drive(RobotContainer.m_OI.getDriverJoystick().getY(), 
                                                   RobotContainer.m_OI.getDriverJoystick().getZ()), RobotContainer.M_WEST_COAST_DRIVE);
        m_shiftHighGear = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.shiftHighGear());
        m_shiftLowGear = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.shiftLowGear());
        m_setAndHoldPose = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.setAndHoldPose());

        // Grabber
        m_toggleGrabber = new InstantCommand(() -> RobotContainer.M_GRABBER.toggleGrabber());

        // Arm
        // if (RobotContainer.isController()){
            m_angleArmWithJoystick = new RunCommand(() -> RobotContainer.M_ARM.angleArm(RobotContainer.m_OI.getOperatorController().getY()), RobotContainer.M_ARM); //TODO figure out if this is the right axis
        // }else{
        //     m_angleArmWithJoystick = new RunCommand(() -> RobotContainer.M_ARM.angleArm(RobotContainer.m_OI.getOperatorJoystick().getY()), RobotContainer.M_ARM); //TODO figure out if this is the right axis
        // }
        m_armTopPos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmTop());
        m_armMiddlePos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmMiddle());
        m_armBottomPos = new InstantCommand(() -> RobotContainer.M_ARM.extendArmBotton());
        m_extendArm = new InstantCommand(() -> RobotContainer.M_ARM.extendArm());
        m_retractArm = new InstantCommand(() -> RobotContainer.M_ARM.retractArm());
    }
}