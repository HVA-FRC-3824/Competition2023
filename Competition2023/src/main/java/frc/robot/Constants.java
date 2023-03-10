package frc.robot;

import java.lang.Math;

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
    public static final int TEST_JOYSTICK_PORT                                                      = 2;
    // #endregion

    // #region Driver button IDs
    public static final int DRIVER_GEAR_SHIFT_BTN_ID                                                = 1;
    // #endregion

    // #region Operator button IDs
    // Grabber
    public static final int OPERATOR_GRABBER_TOGGLE_BTN_ID                                          = 1;
    // WestCoast
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
        // #region WEST_COAST_DRIVE
        public static final int WCD_LEFT_MASTER_ID                                                  = 5;
        public static final int WCD_LEFT_SLAVE_ID                                                   = 2;
        public static final int WCD_RIGHT_MASTER_ID                                                 = 4;
        public static final int WCD_RIGHT_SLAVE_ID                                                  = 3;
        // #region Autonomous Constants
            public static final int K_ENCODER_TICKS_PER_REVOLUTION                                  = 28300;
            public static final double K_WHEEL_DIAMETER_METERS                                      = 0.2;
            public static final double K_ENCODER_DISTANCE_PER_PULSE                                 = (K_WHEEL_DIAMETER_METERS * Math.PI) / (double) K_ENCODER_TICKS_PER_REVOLUTION;
            public static final boolean K_GYRO_REVERSED                                             = true;

            // Use robot characterization tool for these values.
            public static final double K_S_VOLTS                                                    = 0.372;
            public static final double K_V_VOLT_SECONDS_PER_METER                                   = 3.09;
            public static final double K_A_VOLT_SECONDS_SQUARED_PER_METER                           = 0.154;
            public static final double K_P_DRIVE_VEL                                                = 0.00425;
            public static final double K_TRACK_WIDTH_METERS                                         = 0.4556125;
            public static final DifferentialDriveKinematics K_DRIVE_KINEMATICS                      = new DifferentialDriveKinematics(K_TRACK_WIDTH_METERS);

            // Maximum voltage is 10V rather than nominal battery voltage of 12V for "headroom" in dealing with "voltage sag."
            public static final int K_MAX_VOLTAGE                                                   = 10;
            
            public static final double K_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED                 = 1.0; //3.0

            public static final double K_RAMSETE_B                                                  = 2;
            public static final double K_RAMSETE_ZETA                                               = 0.7;

            public static final double WHEEL_CIRCUMFERENCE                                          = (Math.PI * K_WHEEL_DIAMETER_METERS);
        //#endregion

        // Control related constants
        public static final double WCD_MAX_POWER                                                    = 1.0;
        public static final double WCD_TURN_SENS                                                    = 1.5;

        // Pneumatics
        public static final int PNEUMATIC_HUB_ID                                                    = 6;
        public static final int ANALOG_PRESSURE_SENSOR_PH_ID                                        = 0;
        public static final int WCD_LEFT_SHIFTER_CHANNEL                                            = 1;
        public static final int WCD_RIGHT_SHIFTER_CHANNEL                                           = 2;                
        // #endregion

        // #region ARM
        public static final int ARM_ANGLE_MOTOR_ID                                                  = 8;
        public static final int ARM_EXTEND_MOTOR_ID                                                 = 7;
        public static final double MAX_ARM_ANGLE                                                    = 3824; //TODO figure out encoder number
        public static final double MIN_ARM_ANGLE                                                    = 3824; //TODO figure out encoder number
        public static final double MAX_ARM_EXTENSION                                                = 3824; //TODO figure out num
        public static final double MIN_ARM_EXTENSION                                                = 0;
        public static final double ARM_EXTENSION_VOLTAGE                                            = 3; // TODO figure out num
        public static final double ARM_ANGLE_MOTOR_SENS                                             = .8; // DON't PUT ABOVE 1
        public static final double ARM_BOTTOM_EXTENSION_VALUE                                       = 3824; // TODO figure out num
        public static final double ARM_MIDDLE_EXTENSION_VALUE                                       = 3824; // TODO figure out num
        public static final double ARM_TOP_EXTENSION_VALUE                                          = 3824; // TODO figure out num
        // #endregion

        // #region GRABBER
        public static final int GRABBER_MOTOR_ID                                                    = 9;
        public static final double GRABBER_OPEN_VOLTAGE                                             = -0.1; 
        public static final double GRABBER_CLOSE_VOLTAGE                                            = 0.0; //TODO decide volage
        // #endregion

        // #region LEDs
        public static final int LEDS_ID                                                             = 0;
        public static final int TOTAL_LEDS_COUNT                                                    = 150;
        // #endregion

        // server info
        public static final int PORT                                                                = 5805;
        public static final int MAX_TAGS                                                            = 8;
        public static ScoreData[][] SCORE_DATA_ARRAY                                                = new ScoreData[3][3];
   
        // #endregion

    // #region PIDs
    public static final int K_PID_LOOP_IDX                                                          = 0;
    public static final int K_SLOT_IDX                                                              = 0;
    public static final int K_TIMEOUT_MS                                                            = 30;
    // #endregion
}