package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // #region Joysticks (controllers)
    public static final int DRIVER_JOYSTICK_PORT                            = 0;
    public static final int OPERATOR_JOYSTICK_PORT                          = 1;
    // #endregion
    // #region Driver buttons
    public static final int DRIVER_GEAR_SHIFT_BTN_ID                        = 1;
    // #endregion
    // #region Operator buttons
    public static final int DRIVER_GRABBER_TOGGLE_BTN_ID                    = 2;
    // #endregion
    // #region Test buttons
    public static final int TEST_GEAR_SHIFT_BTN_ID                        = 1;
    public static final int TEST_GRABBER_TOGGLE_BTN_ID                    = 2;
    // #endregion
    // #region subsystems
        // #region West Coast Drive
        // front compressor forward, front one is master
        public static final int WCD_LEFT_MASTER_ID                          = 2; // 2 front left
        public static final int WCD_LEFT_SLAVE_ID                           = 3; // 3 back left good
        public static final int WCD_RIGHT_MASTER_ID                         = 1; // 1 front right
        public static final int WCD_RIGHT_SLAVE_ID                          = 4; // 4 back right

        public static final double WCD_MAX_POWER                            = 1.0;
        public static final double WCD_TURN_SENS                            = 1.25;

            // #region Pneumatic Ports for gearshifter
            public static final int WCD_GEARSHIFT_PORT_A                    = 1;
            public static final int WCD_GEARSHIFT_PORT_B                    = 0;
            // #endregion                 
        // #endregion
        // #region Turret
        public static final int TURRET_MOTOR_ID                             = 5;
        // #endregion
        // #region Arm
        public static final int ARM_MOTOR_ID                                = 0;
        // #endregion
        // #region Grabber
        public static final int GRABBER_PNEUMATIC_HUB_ID                    = 6;
        // #endregion   
    // #endregion
    // #region PID
    public static final int    K_PID_LOOP_IDX                               = 0;
    public static final int    K_SLOT_IDX                                   = 0;
    public static final int    K_TIMEOUT_MS                                 = 30;
    // #endregion
}