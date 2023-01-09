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
    public static final int DRIVER_JOYSTICK_PORT                                = 0;
    public static final int OPERATOR_JOYSTICK_PORT                              = 1;

    //Driver buttons

    //Operator buttons

    //subsystems
        //West Coast Drive
        public static final int CHASSIS_LEFT_MASTER_ID                          = 1;
        public static final int CHASSIS_LEFT_SLAVE_ID                           = 2;
        public static final int CHASSIS_RIGHT_MASTER_ID                         = 3;
        public static final int CHASSIS_RIGHT_SLAVE_ID                          = 4;
    //PID
    public static final int    K_PID_LOOP_IDX                                   = 0;
    public static final int    K_SLOT_IDX                                       = 0;
    public static final int    K_TIMEOUT_MS                                     = 30;
}
