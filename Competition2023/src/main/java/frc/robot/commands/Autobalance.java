package frc.robot.commands;

import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Autobalance extends CommandBase {
    public Autobalance(){
        // Require chassis to takeover drive train input. This will end the driveWithJoystick command that will be recalled after this command ends.*/
        addRequirements(RobotContainer.M_WEST_COAST_DRIVE);
    }

    // Called when the command is initially scheduled.
    @Override
        public void initialize(){
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(){

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished(){
        // Command will end when joystick button is released due to requirement of chassis class of stop command.
        return false;
    }
}