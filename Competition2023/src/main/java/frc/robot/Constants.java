package frc.robot;

// import frc.robot.commands.visionCommands.ScoreData;

/* The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity. */
public final class Constants{
    // #region Joysticks/controller IDs
    public static final int DRIVER_CONTROLLER_PORT                                                  = 0;
    public static final int OPERATOR_CONTROLLER_PORT                                                = 1;
    // #endregion

    // #region Driver button IDs
    public static final int TOGGLE_DRIVE_CENTRICITY_BTN_ID                                          = 7; // Select, left middle button
    public static final int JUKE_SPEED_MODE_BTN_ID                                                  = 6; // RB
    public static final int TOGGLE_DRIVE_POWER_BTN_ID                                               = 1; // A
    public static final int XLOCK_WHEELS_BTN_ID                                                     = 2; // B

    public static final int TOGGLE_ARM_EXTENSION_LIMITER_BTN_ID                                     = 3; // X
    public static final int SET_ARM_TOP_POS_BTN_ID                                                  = 4; // Y
    public static final int SET_ARM_MIDDLE_POS_BTN_ID                                               = 2; // B
    public static final int SET_ARM_BOTTOM_POS_BTN_ID                                               = 1; // A
    public static final int PURPLE_CUBE_BTN_ID                                                      = 270;
    public static final int YELLOW_CONE_BTN_ID                                                      = 90;
    public static final int NORMAL_LED_BTN_ID                                                       = 0;
    
    // #endregion

    // #region subsystems
        //#region Swerve Drive
        public static final int FRONT_RIGHT_ANGLE_MOTOR_CAN_ID                                      = 5; 
        public static final int FRONT_RIGHT_DRIVE_MOTOR_CAN_ID                                      = 6;
        public static final int FRONT_LEFT_ANGLE_MOTOR_CAN_ID                                       = 7; 
        public static final int FRONT_LEFT_DRIVE_MOTOR_CAN_ID                                       = 8; 
        public static final int BACK_LEFT_ANGLE_MOTOR_CAN_ID                                        = 9; 
        public static final int BACK_LEFT_DRIVE_MOTOR_CAN_ID                                        = 10; 
        public static final int BACK_RIGHT_ANGLE_MOTOR_CAN_ID                                       = 11; 
        public static final int BACK_RIGHT_DRIVE_MOTOR_CAN_ID                                       = 12; 
        public static final double K_CHASSIS_LEFT_ANGLE_P                                           = 0.2245;     //previous: 0.225
        public static final double K_CHASSIS_LEFT_ANGLE_I                                           = 0.0000185;  //previous: 0.0002
        public static final double K_CHASSIS_LEFT_ANGLE_D                                           = 0.000001;   //previous: 0.0000001
        public static final double K_CHASSIS_RIGHT_ANGLE_P                                          = 0.2245;    //previous: 0.225
        public static final double K_CHASSIS_RIGHT_ANGLE_I                                          = 0.0000185; //previous: 0.0002
        public static final double K_CHASSIS_RIGHT_ANGLE_D                                          = 0.000003;  //previous: 0.000005
        public static final double SWERVE_POWER                                                     = 1;
        public static final double SWERVE_SCORE_POWER                                               = .5;
        public static final double SWERVE_JUKE_POWER                                                = .3;
        public static final boolean K_SWERVE_GYRO_REVERSED                                          = true;
        public static final int FALCON_500_ENCODER_COUNTS_PER_REV                                   = 2048;
        public static final double SWERVE_WHEEL_COUNTS_PER_REVOLUTION                               = FALCON_500_ENCODER_COUNTS_PER_REV * 12; // kSensorUnitsPerRotation / kGearRatio;

        // #region ARM ANGLE
        public static final int ARM_ANGLE_MOTOR_CAN_ID                                              = 2;
        public static final double ARM_ANGLE_MOTOR_KF                                               = 0;
        public static final double ARM_ANGLE_MOTOR_KP                                               = .25;
        public static final double ARM_ANGLE_MOTOR_KI                                               = 0.000015;
        public static final double ARM_ANGLE_MOTOR_KD                                               = 0;
        public static final int ARM_ANGLE_MOTOR_K_CRUISE_VELOCITY                                   = 0;
        public static final int ARM_ANGLE_MOTOR_K_ACCELERATION                                      = 0;
        public static final double ARM_ANGLE_GEAR_RATIO                                             = 49;
        public static final double MAX_ARM_POSITION                                                 = 390872;
        public static final double MIN_ARM_POSITION                                                 = -10000;
        // public static final double ARM_ANGLE_MOTOR_SENS                                             = .8; // DON't PUT ABOVE 1
        public static final double ARM_BOTTOM_ANGLE_VALUE                                           = 3824;
        public static final double ARM_MIDDLE_ANGLE_VALUE                                           = 3824;
        public static final double ARM_TOP_ANGLE_VALUE                                              = 3824;
        // #endregion

        // #region ARM EXTENSION
        public static final int ARM_EXTEND_MOTOR_CAN_ID                                             = 3;
        public static final int ARM_EXTENSION_GEAR_RATIO                                            = 16;
        public static final int ARM_EXTENSION_COUNTS_PER_REV                                        = FALCON_500_ENCODER_COUNTS_PER_REV * ARM_EXTENSION_GEAR_RATIO;
        public static final double MAX_ARM_EXTENSION                                                = (450887 / ARM_EXTENSION_GEAR_RATIO) / 2048;
        public static final double MIN_ARM_EXTENSION                                                = (-800 / ARM_EXTENSION_GEAR_RATIO) / 2048;
        public static final double ARM_EXTENSION_VOLTAGE                                            = 12;
        public static final double ARM_BOTTOM_EXTENSION_VALUE                                       = 3824; // TODO figure out num
        public static final double ARM_MIDDLE_EXTENSION_VALUE                                       = 3824; // TODO figure out num
        public static final double ARM_TOP_EXTENSION_VALUE                                          = 3824; // TODO figure out num

        // #region GRABBER
        // #endregion

        // #region LEDs
        public static final int LEDS_ID                                                             = 0;
        public static final int TOTAL_LEDS_COUNT                                                    = 150;
        // #endregion

        // server info / tag information
        public static final int PORT                                                                = 5805;
        public static final int MAX_TAGS                                                            = 8;
        // public static ScoreData[][] SCORE_DATA_ARRAY                                                = new ScoreData[3][3];
        public static final float MIN_DIST_TO_TAG                                                   = 8; /* In inches*/
        // #endregion

    // #region PIDs
    public static final int K_PID_LOOP_IDX                                                          = 0;
    public static final int K_SLOT_IDX                                                              = 0;
    public static final int K_TIMEOUT_MS                                                            = 30;
    // #endregion
}