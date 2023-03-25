package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
// import frc.robot.commands.ResetExtensionMotorEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class ArmExtension extends SubsystemBase {

  private WPI_TalonFX armExtendMotor;
  private double actualArmExtensionPos;
  private final SendableChooser<Boolean> extensionEncoderReset = new SendableChooser<>();
  private boolean extensionLimiter = true; // We want to start with the extension limit on, so true
  
  private double maxArmExtension; // Var for changing max are extension based on the angle of the arm

  public ArmExtension() {
    // TODO: tune PIDs
    armExtendMotor = new WPI_TalonFX(Constants.ARM_EXTEND_MOTOR_CAN_ID);
    RobotContainer.configureTalonFX(armExtendMotor, true, FeedbackDevice.IntegratedSensor, true, 
                                    0.0, 0.5, 0.0, 0.0);
    armExtendMotor.setNeutralMode(NeutralMode.Brake);

    // Extension reset smartdashboard chooser
    extensionEncoderReset.setDefaultOption("False: ", false);
    extensionEncoderReset.addOption("True: ", true);
    SmartDashboard.putData("Arm Extension Encoder Reset: ", extensionEncoderReset);
    // Shuffleboard.getTag("bestTag").add("Arm Extension Encoder Reset: ", extensionEncoderReset);

    // SmartDashboard.putData("RESET ARM EXTENSION ENCODER", new ResetExtensionMotorEncoder());
  }

  @Override
  public void periodic() {
    // set actual arm extension and output encoder position for extendMotor
    actualArmExtensionPos = (armExtendMotor.getSelectedSensorPosition() / Constants.ARM_EXTENSION_GEAR_RATIO) / Constants.FALCON_500_ENCODER_COUNTS_PER_REV;
    SmartDashboard.putNumber("Actual Arm Extension Position: ", actualArmExtensionPos);
    /* Shuffleboard.getTag("bestTag").add("Actual Arm Extension Position: ", actualArmExtensionPos); */

    maxArmExtension = ((Constants.MAX_ARM_EXTENSION_IN_RSU / Constants.ARM_EXTENSION_GEAR_RATIO) / Constants.FALCON_500_ENCODER_COUNTS_PER_REV); // / Math.cos(RobotContainer.ARM_ANGLE_OBJ.getArmAngle());

    // If reset encoder is selected, it runs encoder reset method
    if(extensionEncoderReset.getSelected()){
      resetExtensionMotorEncoder();
    }

    SmartDashboard.putBoolean("ARM EXTENSION LIMITER", extensionLimiter);
    // Shuffleboard.getTag("bestTag").add("ARM EXTENSION LIMITER", extesionLimiter);
  }

  // reset arm angle motor encoder
  public void resetExtensionMotorEncoder(){
    armExtendMotor.setSelectedSensorPosition(0);
  }

  // Idle mode setter methods
  public void setArmExtensionMotorCoast(){
    armExtendMotor.setNeutralMode(NeutralMode.Coast);
  }
  public void setArmExtensionMotorBrake(){
    armExtendMotor.setNeutralMode(NeutralMode.Brake);
  }

  // sets arm to top gird scoring position
  public void extendArmTop(){
    armExtendMotor.set(ControlMode.Position, Constants.ARM_EXTENSION_TOP_SCORE_POS);
  }
  // sets arm to middle grid score length
  public void extendArmMiddle(){
    armExtendMotor.set(ControlMode.Position, Constants.ARM_EXTENSION_MIDDLE_SCORE_POS);
  }
  // sets arm to picking up extension position
  public void extendArmPickUp(){
    armExtendMotor.set(ControlMode.Position, Constants.ARM_EXTENSION_PICK_UP_POS);
  }
  public void armExtendCustom(double pos){
    armExtendMotor.set(ControlMode.Position, pos);
  }

  public void extendAndRetractArm(double joystickInput){
    // DEADZONE
    if(Math.abs(joystickInput) > .1){
      // EXTENSION LIMTER CONDITIONAL
      if(extensionLimiter){
        // EXTENSION LIMITER
        if((actualArmExtensionPos <= maxArmExtension) && (actualArmExtensionPos >= Constants.MIN_ARM_EXTENSION_IN_ROTATIONS)){
          armExtendMotor.setVoltage(-joystickInput * Constants.ARM_EXTENSION_VOLTAGE);
        // WHAT TO DO IF OUT OF BOUNDS
        }else{
          System.out.println("WARNING: Arm Extension Position is out of bounds!!! ");
          // LETS US MOVE IF WE ARE TRYING TO GET THE ARM BACK UNDER
          if((actualArmExtensionPos >= maxArmExtension) && (joystickInput > 0)){ 
            armExtendMotor.setVoltage(-joystickInput * Constants.ARM_EXTENSION_VOLTAGE);
          // LETS US MOVE IF WE ARE TRYING TO GET THE ARM BACK OVER
          }else if((actualArmExtensionPos <= Constants.MIN_ARM_EXTENSION_IN_ROTATIONS) && (joystickInput < 0)){
            armExtendMotor.setVoltage(-joystickInput * Constants.ARM_EXTENSION_VOLTAGE);
          }else{
            // SETS US TO ZERO IF WE ARE TRYING TO GO THE WRONG WAY
            armExtendMotor.setVoltage(0);
          }
        }
      // IF EXTENSION LIMITER NOT ON, FREELY MOVE JOYSTICK
      }else{
        armExtendMotor.setVoltage(-joystickInput * Constants.ARM_EXTENSION_VOLTAGE);
      }
    // DEADZONE
    }else{
      armExtendMotor.setVoltage(0);
    }
  }

  public void stopArmExtension(){
    armExtendMotor.setVoltage(0);
  }

  public double getArmExtensionMotorEncoderPosition(){
    return(actualArmExtensionPos);
  }

  public WPI_TalonFX getArmExtensionMotor(){
    return(armExtendMotor);
  }

  public void toggleExtensionLimiter(){
    if(extensionLimiter){
      System.out.println("DISENGAGING EXTENSION LIMITER");
      extensionLimiter = false;
    }else if(!extensionLimiter){
      System.out.println("ENGAGING EXTENSION LIMITER");
      extensionLimiter = true;
    }
  }

  public boolean getExtensionLimiter(){
    return(extensionLimiter);
  }
}