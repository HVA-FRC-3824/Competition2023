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
    //Joysticks (controllers)
    public static final int DRIVER_JOYSTICK_PORT                            = 0;
    // public static final int OPERATOR_JOYSTICK_PORT                          = 1;

    //Driver buttons
    public static final int DRIVER_GEAR_SHIFT_BTN_ID                        = 0;

    //Operator buttons

    //subsystems
        //West Coast Drive
        public static final int WCD_LEFT_MASTER_ID                          = 1;
        public static final int WCD_LEFT_SLAVE_ID                           = 2;
        public static final int WCD_RIGHT_MASTER_ID                         = 3;
        public static final int WCD_RIGHT_SLAVE_ID                          = 4;

        public static final double WCD_MAX_POWER                            = .6;

            // Pneumatic Ports for gearshifter
            public static final int WCD_GEARSHIFT_PORT_A                    = 1;
            public static final int WCD_GEARSHIFT_PORT_B                    = 0;

        // Turret
        public static final int TURRET_MOTOR_ID                             = 5;

        // Arm
        public static final int ARM_MOTOR_ID                                = 6;
            
    //PID
    public static final int    K_PID_LOOP_IDX                               = 0;
    public static final int    K_SLOT_IDX                                   = 0;
    public static final int    K_TIMEOUT_MS                                 = 30;
}
