// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ArmExtension extends SubsystemBase {
  private WPI_TalonFX armExtendMotor;
  private double actualArmExtensionPos;
  private final SendableChooser<Boolean> extensionEncoderReset = new SendableChooser<>();
  /** Creates a new ArmExtension. */
  public ArmExtension() {
    //TODO PIDS
    armExtendMotor = new WPI_TalonFX(Constants.ARM_EXTEND_MOTOR_CAN_ID);
    RobotContainer.configureTalonFX(armExtendMotor, true, true, 0.0, 0, 0.0, 0.0);
    armExtendMotor.setNeutralMode(NeutralMode.Brake);

    // Extension reset smartdashboard chooser
    extensionEncoderReset.setDefaultOption("False: ", false);
    extensionEncoderReset.addOption("True: ", true);
    SmartDashboard.putData("Reset Arm Angle Encoder: ", extensionEncoderReset);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // set actual arm extension and output encoder position for extendMotor
    actualArmExtensionPos = (armExtendMotor.getSelectedSensorPosition() / Constants.ARM_EXTENSION_GEAR_RATIO) / 2048;
    SmartDashboard.putNumber("Actual Arm Extension Position: ", actualArmExtensionPos);

    // If reset encoder is selected, it runs encoder reset method
    if(extensionEncoderReset.getSelected()){
      resetExtensionMotorEncoder();
    }
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

  // sets arm to bottom grid score length
  public void extendArmBotton(){
    armExtendMotor.set(ControlMode.Position, Constants.ARM_BOTTOM_EXTENSION_VALUE);
  }
  // sets arm to middle grid score length
  public void extendArmMiddle(){
    armExtendMotor.set(ControlMode.Position, Constants.ARM_MIDDLE_EXTENSION_VALUE);
  }
  // sets arm to top grid score length
  public void extendArmTop(){
    armExtendMotor.set(ControlMode.Position, Constants.ARM_TOP_EXTENSION_VALUE);
  }
  public void armExtendCustom(double pos){
    armExtendMotor.set(ControlMode.Position, pos);
  }

  // extends arm for fine tuning
  /* FUNCTIONALITY REPLACED BY JOYSTICK CAPABILITY */
  public void extendArm(){
    if(actualArmExtensionPos < Constants.MAX_ARM_EXTENSION){
      armExtendMotor.setVoltage(Constants.ARM_EXTENSION_VOLTAGE);
    }else{
      System.out.println("WARNING: Arm Extension Position is greater than Max extension!!! ");
      armExtendMotor.setVoltage(0);
    }
  }

  // retracts arm for fine tuning 
  /* FUNCTIONALITY REPLACED BY JOYSTICK CAPABILITY */
  public void retractArm(){
      if(actualArmExtensionPos > Constants.MIN_ARM_EXTENSION){
          armExtendMotor.setVoltage(-Constants.ARM_EXTENSION_VOLTAGE);
      }else{
          System.out.println("WARNING: Arm Extension Position is less than Minimum extension!!! ");
          armExtendMotor.setVoltage(0);
      }
  }

  public void extendAndRetractArm(double joystickInput){
    // deadzone
    if(joystickInput > .1){
      if((actualArmExtensionPos <= Constants.MAX_ARM_EXTENSION) && (actualArmExtensionPos >= Constants.MIN_ARM_EXTENSION)){
        armExtendMotor.setVoltage(-joystickInput * Constants.ARM_EXTENSION_VOLTAGE);
      }else{
        System.out.println("WARNING: Arm Extension Position is out of bounds!!! ");
        stopArm();
      }
    }
  }

  public void stopArm(){
    armExtendMotor.setVoltage(0);
  }
}
