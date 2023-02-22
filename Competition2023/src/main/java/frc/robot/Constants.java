package frc.robot;

import java.lang.Math;

/* The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity. */
public final class Constants{
    // #region Joysticks/controller IDs
    public static final int DRIVER_JOYSTICK_PORT                                                  = 0;
    public static final int OPERATOR_JOYSTICK_PORT                                                = 1;
    public static final int TEST_JOYSTICK_PORT                                                    = 2;
    // #endregion

    // #region Driver button IDs
    public static final int DRIVER_GEAR_SHIFT_BTN_ID                                              = 1;
    // #endregion

    // #region Operator button IDs
    public static final int OPERATOR_GRABBER_TOGGLE_BTN_ID                                        = 2;
    public static final int OPERATOR_CHANGE_STATE_TOGGLE_BTN_ID                                   = 3;
    // #endregion

    // #region subsystems
        // #region WEST_COAST_DRIVE
        public static final int WCD_LEFT_MASTER_ID                                                = 2; // 2 front left
        public static final int WCD_LEFT_SLAVE_ID                                                 = 3; // 3 back left good
        public static final int WCD_RIGHT_MASTER_ID                                               = 1; // 1 front right
        public static final int WCD_RIGHT_SLAVE_ID                                                = 4; // 4 back right

        // odemetry constants
        private static final double DIAMETER = 8;
        public static final double CIRCUMFERENCE = (Math.PI * DIAMETER);

        // Control related constants
        public static final double WCD_MAX_POWER                                                  = 1.0;
        public static final double WCD_TURN_SENS                                                  = 1.5;

        // Pneumatic Ports for gearshifter
        public static final int WCD_LEFT_SHIFTER_CHANNEL                                          = 1;
        public static final int WCD_RIGHT_SHIFTER_CHANNEL                                         = 2;                
        // #endregion

        // #region ARM
        public static final int ARM_MOTOR_ID_POST                                                 = 0;
        public static final int ARM_MOTOR_ID_REACH                                                = 1;
        // #endregion

        // #region GRABBER
        public static final int PNEUMATIC_HUB_ID                                                  = 6;
        public static final int GRABBER_CHANNEL_ID                                                = 0;
        public static final int GRABBER_LEFT_CHANNEL_ID                                           = 9;
        public static final int PNEUMATIC_HUB_ANALOG_ID                                           = 0;
        // #endregion

        // #region LEDs
        public static final int LEDS_ID                                                           = 0;
        public static final int TOTAL_LEDS_COUNT                                                  = 150;
        // #endregion

        // server info
        public static final int PORT                                                              = 5805;
        public static final int MAX_TAGS                                                          = 8;
    // #endregion

    // #region PIDs
    public static final int K_PID_LOOP_IDX                                                        = 0;
    public static final int K_SLOT_IDX                                                            = 0;
    public static final int K_TIMEOUT_MS                                                          = 30;
    // #endregion
}