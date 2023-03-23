package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.SwerveDrive;

public class Autobalance extends CommandBase {
    // variable to hold the desired power of the drivetrain
    double power;
    // True is forward, false is backward
    boolean forward;
    // true means were in 
    boolean state;

    // used in execute. Made a object variable so we don't have to make a new variable every time func is called
    double pitchAngle;

    public Autobalance(SwerveDrive driveTrain){
        /* Require chassis to takeover drive train input. This will end the driveWithJoystick command that 
        will be recalled after this command ends.*/
        addRequirements(driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize(){}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(){
        System.out.println("AUTOBALANCE COMMAND RUNNING...");
        pitchAngle = RobotContainer.SWERVE_DRIVE_OBJ.getGyroPitch();
 
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

    public void auBa() {
        // this function is a proposed solution to the AutoBalance
        pitchAngle = RobotContainer.SWERVE_DRIVE_OBJ.getGyroPitch();

        // adjust this variable through testing
        double amplifier = 0.8;

        // we are basically plugging in the pitchAngle into this equation:
        // y = -2x        x is pitch         y is power
        RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, (pitchAngle * -amplifier), 0);
    }
}