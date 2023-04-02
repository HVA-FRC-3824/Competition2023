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
    double pitchAngleX;
    double powerY;

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
        pitchAngleX = RobotContainer.SWERVE_DRIVE_OBJ.getGyroPitch();
 
        // Determines power
        if (Math.abs(pitchAngleX) > 10){
            power = 0.15;
        }else if(Math.abs(pitchAngleX) > 5.0){
            power = 0.08;
        }else if(Math.abs(pitchAngleX) > 3.2){
            power = 0.032;
        }else{
            power = 0;
        }

        // Determines if we need to go forward or backward
        forward = (pitchAngleX > 0);
        if(forward){ // <-- sets us to actually do that
            power = -power;
        }

        RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, power, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    public void autoBalanceLinear() {
        pitchAngleX = RobotContainer.SWERVE_DRIVE_OBJ.getGyroPitch();

        // deadzone so that it stops moving when balanced
        if(Math.abs(pitchAngleX) < 2){
            powerY = 0;
        }else{
            powerY = ((-0.015 * pitchAngleX) + .02);
        }
        // we are basically plugging in the pitchAngleX into this equation:
        // y=-0.015x+.02        x is pitch         y is power
        RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, powerY, 0);
    }

    public void autoBalanceQuadratic() {
        pitchAngleX = RobotContainer.SWERVE_DRIVE_OBJ.getGyroPitch();

        // deadzone so that it stops moving when balanced
        if(Math.abs(pitchAngleX) < 2){
            powerY = 0;
        }else{
            powerY = (0.0028205128 * (Math.pow(pitchAngleX, 2)) + (-0.0094871795 * pitchAngleX) + 0.1076923077);
        }
        // we are basically plugging in the pitchAngleX into this equation:
        // y=x^2 + 2        x is pitch         y is power

        RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0, powerY, 0);
    }
}