package frc.robot;

import frc.robot.commands.visionCommands.AutoScore;
import frc.robot.subsystems.communication.*;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/* The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project. */
public class Robot extends TimedRobot {
  private Command autonomousCommand;
  private RobotContainer robotContainer;
  
  @Override
  public void robotInit(){
    System.out.println("AVE CHRISTUS REX");
    System.out.println("SANCTA MARIA, MATER DEI, ORA PRO NOBIS PECCATORIBUS ET NOBIS VICTORIAM REDDE");

    // Init TagData objects
    for(int i = 0; i < Constants.MAX_TAGS; i++)
    {
      // Type cast to float because default goes to double.
      TagData.TAG_DATA[i] = new TagData(i+1,(float)0.0,(float)0.0);
    }

    /* SERVER INITALIZATION */
    CommClient client = new CommClient(Constants.PORT);
    
    // Start client thread
    client.start("ClientThread");

    // Start the tagstatehandler
    TagStateHandler.init_Array(); 

    // Init score data 
    AutoScore.initScoreValues();
    
    // starts camera
    CameraServer.startAutomaticCapture();

    // Instantiate RobotContainer. 
    robotContainer = new RobotContainer();

    // set brake modes to motors
    RobotContainer.ARM_EXTENSION_OBJ.setArmExtensionMotorBrake();
    RobotContainer.ARM_ANGLE_OBJ.setArmAngleMotorBreak();

    RobotContainer.SWERVE_DRIVE_OBJ.zeroGyro();
  }

  /* This method is called every robot packet, no matter the mode. Use this for items like diagnostics that you want ran during 
   * disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic methods, but before LiveWindow and SmartDashboard integrated updating.*/
  @Override
  public void robotPeriodic(){
    /* Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled commands, running already-scheduled 
     * commands, removing finished or interrupted commands, and running subsystem periodic() methods.  This must be called from 
     * the robot's periodic block in order for anything in the Command-based framework to work. */
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit(){
    // set brake modes to motors
    RobotContainer.ARM_ANGLE_OBJ.setArmAngleMotorBreak();
    RobotContainer.ARM_EXTENSION_OBJ.setArmExtensionMotorBrake();
  }

  @Override
  public void disabledPeriodic(){}

  @Override
  public void autonomousInit(){
    // Command sets autocommand to the command based on the smartdashboard
    autonomousCommand = robotContainer.getAutonomousCommand();

    // schedule the autonomous command if one is selected.
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }else{
      System.out.println("ERROR: autoCommand is null, error with getAutonomousCommand method most likely. ");
    }
  }

  @Override
  public void autonomousPeriodic(){}

  @Override
  public void teleopInit(){
    /* This makes sure that the autonomous stops running when teleop starts running. If you want the autonomous to
     * continue until interrupted by another command, remove this line or comment it out. */
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    
    // Sets default commands of driving and moving arm
    RobotContainer.initializeTeleopDefaultCommands();
  }

  @Override
  public void teleopPeriodic(){
    // The method in the arm that makes the arm move to desired angle
    RobotContainer.ARM_ANGLE_OBJ.setArmActualPosToDesiredPos();
  }

  @Override
  public void testInit(){
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic(){}
}