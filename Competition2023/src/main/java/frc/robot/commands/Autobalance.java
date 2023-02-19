package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.lang.Math;

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
        float angle = RobotContainer.M_WEST_COAST_DRIVE.returnGyro().getRoll();
        // variable to hold the desired velocity of the drivetrain
        double vel;
        // True is forward, false is backward
        boolean forward;
        // true means were in 
        boolean state;
        // if(angle > 1.0){
        //     if(angle > 2.5){
        //         if(angle > 5.0){
        //             // driveWithEncoders(go fast);
        //             System.out.println("go fast positive");
        //         }else{
        //             // driveWithEncoders(go medium speed);
        //             System.out.println("go medium speed positive");
        //         }
        //     }else{
        //         // driveWithEncoders(go slow);
        //         System.out.println("go slow positive");
        //     }
        // }else if(angle < -1.0){
        //     if(angle < -2.5){
        //         if(angle < -5.0){
        //             // driveWithEncoders(-move a lot);
        //             System.out.println("go fast negative");
        //         }else{
        //             // driveWithEncoders(-move a litte more);
        //             System.out.println("go medium speed negative");
        //         }
        //     }else{
        //         // driveWithEncoders(-move a litte);
        //         System.out.println("go slow negative");
        //     }
        // }

        if (Math.abs(angle) > 2.5){
            // vel = Low velocity
        }else if(Math.abs(angle) > 5.0){
            // vel = Medium velocity
        }else if(Math.abs(angle) > 10){
            // vel = Max velocity
        }

        if(Math.abs(angle) > 0){
            forward = true;
        }else{
            forward = false;
        }

        //RobotContainer.M_WEST_COAST_DRIVE.driveWithVelocity(forward, vel);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished(){
        // Command will end when joystick button is released due to requirement of chassis class of stop command.
        return false;
    }
}