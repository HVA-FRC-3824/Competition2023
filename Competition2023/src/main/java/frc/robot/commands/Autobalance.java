package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class Autobalance extends CommandBase {
    // variable to hold the desired power of the drivetrain
    double power;
    // True is forward, false is backward
    boolean forward;
    // true means were in 
    boolean state;

    public Autobalance(){
        /* Require chassis to takeover drive train input. This will end the driveWithJoystick command that 
        will be recalled after this command ends.*/
        addRequirements(RobotContainer.SWERVE_DRIVE_OBJ);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize(){
        System.out.println("AUTOBALANCE COMMAND RUNNING...");
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(){
        float pitchAngle = RobotContainer.SWERVE_DRIVE_OBJ.returnGyroPitch();
 
        // Determines power
        if (Math.abs(pitchAngle) > 10){
            power = 0.2;
        }else if(Math.abs(pitchAngle) > 5.0){
            power = 0.15;
        }else if(Math.abs(pitchAngle) > 2){
            power = 0.1;
        }else{
            power = 0;
        }

        // Determines if we need to go forward or backward
        forward = (pitchAngle > 0);
        if(forward){ // <-- sets us to actually do that
            power = -power;
        }

        RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, power, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished(){
        return false;
    }
}