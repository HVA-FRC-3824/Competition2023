package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class Autobalance extends CommandBase {
    public Autobalance(){
        // Require chassis to takeover drive train input. This will end the driveWithJoystick command that will be recalled after this command ends.*/
        addRequirements(RobotContainer.SWERVE_DRIVE_OBJ);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize(){
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(){
        // float angle = RobotContainer.M_WEST_COAST_DRIVE.returnGyro().getRoll();
        // variable to hold the desired velocity of the drivetrain
        // double vel;
        // True is forward, false is backward
        // boolean forward;
        // true means were in 
        // boolean state;

        // if (Math.abs(angle) > 2.5){
        //     // vel = Constants.AUTOBALANCE_LOW_VEL
        // }else if(Math.abs(angle) > 5.0){
        //     // vel = Constants.AUTOBALANCE_MEDIUM_VEL
        // }else if(Math.abs(angle) > 10){
        //     // vel = Constants.AUTOBALANCE_HIGH_VEL
        // }

        // if(Math.abs(angle) > 0){
        //     forward = true;
        // }else{
        //     forward = false;
        // }

        // RobotContainer.M_WEST_COAST_DRIVE.driveWithVelocity(forward, vel);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished(){
        // Command will end when joystick button is released due to requirement of chassis class of stop command.
        return false;
    }
}