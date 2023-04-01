package frc.robot.subsystems.communication;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class TagStateHandler extends SubsystemBase{
    public static int t_Array[] = new int[Constants.MAX_TAGS];
    public static boolean tag_Available[] = new boolean[Constants.MAX_TAGS];
    public static final String name = "T_ID: "; /* Used for smart dashboardie */

    // Shuffleboard tab = Shuffleboard.getTab("bestTab");

    /* Setup array */
    public static void init_Array()
    {
        for(int i = 0; i < Constants.MAX_TAGS; i++)
        {
            t_Array[i] = 0;
            tag_Available[i] = false;
        }

        init_dashboard();
    }

    /* Setup smartdashboard */
    public static void init_dashboard()
    {
        for(int i = 0; i < Constants.MAX_TAGS; i++)
        {
            // SmartDashboard.putBoolean(name + (i+1), false);
            /* 
            Shuffleboard.getTab("bestTab")
                .add(name + (i+1), false);
            */
        }
    }

    public static void updateTagTimer(int id)
    {
        t_Array[id-1] = 3; /* ~300 ms */
        SmartDashboard.putBoolean(name + id, true);
        /*
        Shuffleboard.getTab("bestTab")
            .add(name + id, true);
         */
    }

    @Override
    public void periodic()
    {
        /* Periodically check if the timer for the tag to keep the operator notified of it's status */
        for(int i = 0; i < Constants.MAX_TAGS; i++)
        {
            if(t_Array[i] != 0)
            {
                SmartDashboard.putBoolean(name + (i+1), true);
                /*
                Shuffleboard.getTab("bestTab")
                    .add(name + (i+1), true);
                 */
                tag_Available[i] = true;
                t_Array[i]--;
            } else 
            {
                tag_Available[i] = false;
            }
        }
    }
}