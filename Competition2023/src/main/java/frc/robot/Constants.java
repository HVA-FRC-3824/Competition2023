package frc.robot;

import java.lang.Math;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import frc.robot.commands.ScoreData;

/* The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity. */
public final class Constants{
    // #region Joysticks/controller IDs
    public static final int DRIVER_JOYSTICK_PORT                                                    = 0;
    public static final int OPERATOR_JOYSTICK_PORT                                                  = 1;
    public static final int OPERATOR_CONTROLLER_PORT                                                = 2;
    // #endregion

    // #region Driver button IDs
    public static final int DRIVER_GEAR_SHIFT_BTN_ID                                                = 1;
    // #endregion

    // #region Operator button IDs
    // Grabber
    public static final int OPERATOR_GRABBER_TOGGLE_BTN_ID                                          = 1;
    // Chassis
    public static final int OPERATOR_AUTOBALANCE_BTN_ID                                             = 6;
    public static final int OPERATOR_HOLD_POSE_TOGLE_BTN_ID                                         = 5;
    // Arm
    public static final int OPERATOR_SET_ARM_TOP_POSE_BTN_ID                                        = 7;
    public static final int OPERATOR_SET_ARM_MIDDLE_POSE_RIGHT_BTN_ID                               = 8;
    public static final int OPERATOR_SET_ARM_MIDDLE_POSE_LEFT_BTN_ID                                = 10;
    public static final int OPERATOR_SET_ARM_BOTTOM_POSE_BTN_ID                                     = 9;
    public static final int OPERATOR_EXTEND_ARM_BTN_ID                                              = 11;
    public static final int OPERATOR_RETRACT_ARM_BTN_ID                                             = 13;
    // #endregion

    // #region subsystems
        // Pneumatics
        public static final int PNEUMATIC_HUB_ID                                                    = 6;
        public static final int ANALOG_PRESSURE_SENSOR_PH_ID                                        = 0;
        public static final int WCD_LEFT_SHIFTER_CHANNEL                                            = 1;
        public static final int WCD_RIGHT_SHIFTER_CHANNEL                                           = 2;                
        // #endregion

        //#region Swerve Drive
        public static final int FRONT_RIGHT_ANGLE_MOTOR_ID                                          = 2; 
        public static final int FRONT_RIGHT_SPEED_MOTOR_ID                                          = 3;

        public static final int FRONT_LEFT_ANGLE_MOTOR_ID                                           = 15; 
        public static final int FRONT_LEFT_SPEED_MOTOR_ID                                           = 12; 

        public static final int BACK_LEFT_ANGLE_MOTOR_ID                                            = 14; 
        public static final int BACK_LEFT_SPEED_MOTOR_ID                                            = 13; 

        public static final int BACK_RIGHT_ANGLE_MOTOR_ID                                           = 0; 
        public static final int BACK_RIGHT_SPEED_MOTOR_ID                                           = 1; 
        
        public static final int ABS_ENCODER_FR_ID                                                   = 1;
        public static final int ABS_ENCODER_FL_ID                                                   = 4;
        public static final int ABS_ENCODER_BL_ID                                                   = 3;
        public static final int ABS_ENCODER_BR_ID                                                   = 2;
        
        public static final double K_CHASSIS_TURN_P                                                 = 0;
        public static final double K_CHASSIS_TURN_I                                                 = 0;
        public static final double K_CHASSIS_TURN_D                                                 = 0;

        public static final double K_TURN_TOLERANCE_DEG                                             = 0;
        public static final double K_TURN_RATE_TOLERANCE_DEG_PER_SEC                                = 0;

        public static final double K_CHASSIS_TURN_VISION_P                                          = 0.025;
        public static final double K_CHASSIS_TURN_VISION_MIN                                        = 0.05;
        public static final double CHASSIS_TURN_ERROR_THRESHOLD                                     = 0.5;
        
        public static final double K_CHASSIS_LEFT_ANGLE_P                                           = 0.2245;     //previous: 0.225
        public static final double K_CHASSIS_LEFT_ANGLE_I                                           = 0.0000185;  //previous: 0.0002
        public static final double K_CHASSIS_LEFT_ANGLE_D                                           = 0.000001;   //previous: 0.0000001

        public static final double K_CHASSIS_RIGHT_ANGLE_P                                          = 0.2245;    //previous: 0.225
        public static final double K_CHASSIS_RIGHT_ANGLE_I                                          = 0.0000185; //previous: 0.0002
        public static final double K_CHASSIS_RIGHT_ANGLE_D                                          = 0.000003;  //previous: 0.000005

        public static final double SWERVE_DRIVE_MAX_VOLTAGE                                         = 4.95;
        public static final double SWERVE_GEAR_RATIO                                                = 0.0833333; //wheel spins per angle motor spin    
        public static final double SWERVE_TPR                                                       = 2048 / SWERVE_GEAR_RATIO; //motors ticks per revolution of wheel
        public static final double SWERVE_POWER                                                     = 0.81; //0.83

        public static final double K_MAX_VELOCITY                                                   = 3.0; // m/s
        public static final double K_MODULE_MAX_ANGULAR_VELOCITY                                    = Math.PI; // 0.5 rotations/sec
        public static final double K_MODULE_MAX_ANGULAR_ACCELERATION                                = 2 * Math.PI; //radians/sec^2
        public static final double K_SWERVE_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED              = 3.0; 
        
        public static final int K_ABSOLUTE_ENCODER_UPR                                              = 4096; //units per rotation

        // Chassis Specs
        public static final Translation2d BACK_LEFT_WHEEL_LOCATION                                  = new Translation2d(-12.5 , 10.75);
        public static final Translation2d BACK_RIGHT_WHEEL_LOCATION                                 = new Translation2d(-12.5 , -10.75);
        public static final Translation2d FRONT_LEFT_WHEEL_LOCATION                                 = new Translation2d(12.5 , 10.75);        
        public static final Translation2d FRONT_RIGHT_WHEEL_LOCATION                                = new Translation2d(12.5 , -10.75);

        public static final double K_SWERVE_WHEEL_DIAMETER_METERS                                   = 0.1524;
        public static final double SWERVE_DRIVE_WHEEL_AXLE_LENGTH                                   = 36;
        public static final double SWERVE_DRIVE_WHEEL_AXLE_WIDTH                                    = 48;
        public static final double SWERVE_DRIVE_WHEEL_AXLE_DIAGONAL                                 = 60;

        public static final boolean K_SWERVE_GYRO_REVERSED                                          = true;

        public static final double WHEEL_MOTOR_TICKS_PER_REVOLUTION                                 = 2048 * 12; //kSensorUnitsPerRotation / kGearRatio;

        public static final int K_SWERVE_ENCODER_TICKS_PER_REVOLUTION                               = 28300;
        public static final double K_SWERVE_ENCODER_DISTANCE_PER_PULSE                              = (K_SWERVE_WHEEL_DIAMETER_METERS * Math.PI) / (double) K_SWERVE_ENCODER_TICKS_PER_REVOLUTION;

        // #region ARM
        public static final int ARM_ANGLE_MOTOR_ID                                                  = 8;
        public static final int ARM_EXTEND_MOTOR_ID                                                 = 7;
        public static final double MAX_ARM_POSITION                                                 = 10000; //TODO figure out encoder number
        public static final double MIN_ARM_POSITION                                                 = -1000; //TODO figure out encoder number
        public static final double MAX_ARM_EXTENSION                                                = 3824; //TODO figure out num
        public static final double MIN_ARM_EXTENSION                                                = 0;
        public static final double ARM_EXTENSION_VOLTAGE                                            = 5; // TODO figure out num
        public static final double ARM_ANGLE_MOTOR_SENS                                             = .8; // DON't PUT ABOVE 1
        public static final double ARM_BOTTOM_EXTENSION_VALUE                                       = 3824; // TODO figure out num
        public static final double ARM_MIDDLE_EXTENSION_VALUE                                       = 3824; // TODO figure out num
        public static final double ARM_TOP_EXTENSION_VALUE                                          = 3824; // TODO figure out num
        // #endregion

        // #region GRABBER
        public static final int GRABBER_MOTOR_ID                                                    = 9;
        public static final double GRABBER_CLOSE_VOLTAGE                                            = 2; 
        // #endregion

        // #region LEDs
        public static final int LEDS_ID                                                             = 0;
        public static final int TOTAL_LEDS_COUNT                                                    = 150;
        // #endregion

        // server info / tag information
        public static final int PORT                                                                = 5805;
        public static final int MAX_TAGS                                                            = 8;
        public static ScoreData[][] SCORE_DATA_ARRAY                                                = new ScoreData[3][3];
        public static final int MIN_DIST_TO_TAG                                                     = 8; /* In inches*/
   
        // #endregion

    // #region PIDs
    public static final int K_PID_LOOP_IDX                                                          = 0;
    public static final int K_SLOT_IDX                                                              = 0;
    public static final int K_TIMEOUT_MS                                                            = 30;
    // #endregion
}