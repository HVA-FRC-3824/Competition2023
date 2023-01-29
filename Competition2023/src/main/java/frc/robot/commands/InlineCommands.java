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

    // Grabber
    public final Command m_togglePiston;

    public InlineCommands(){
        // WestCoast
        m_driveWithJoystick = new RunCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.drive(
                                                    RobotContainer.m_OI.getDriverJoystick().getY(),
                                                    RobotContainer.m_OI.getDriverJoystick().getZ()),
                                                    RobotContainer.M_WEST_COAST_DRIVE);
        m_shiftHighGear = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.shiftHighGear());
        m_shiftLowGear = new InstantCommand(() -> RobotContainer.M_WEST_COAST_DRIVE.shiftLowGear());

        // Grabber
        m_togglePiston = new InstantCommand(() -> RobotContainer.M_GRABBER.togglePiston());
    }
}