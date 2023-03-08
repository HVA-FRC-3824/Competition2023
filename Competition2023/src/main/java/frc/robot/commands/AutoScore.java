package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoScore {
    public static void initScoreValues()
    {
        /* Function should set all the values for SCORE_DATA_ARRAY */

        /* Ex. */

        /* Height! */
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                /* oogly */
                switch (i) {
                    case 0:
                        Constants.SCORE_DATA_ARRAY[i][j].height = heights.TOP;
                        break;
                    case 1:
                        Constants.SCORE_DATA_ARRAY[i][j].height = heights.MID;
                        break;
                    case 2:
                        Constants.SCORE_DATA_ARRAY[i][j].height = heights.BOT;
                        break;
                    default:
                        break;
                }
            }
        }

        /* Set hard angle and dist */
        Constants.SCORE_DATA_ARRAY[0][0].angle = 0;
        Constants.SCORE_DATA_ARRAY[0][0].dist = 5;

    }

    public static enum heights{BOT,MID,TOP};

    public void score(int ix, int iy)
    {
        /* do stuff idk based off the index and data 
         * Ex.
        */

        float angle, dist;
        heights height;

        /* SCORE_DATA_ARRAY is a 2D array of ScoreData objects
         * One ScoreData object has 3 attributes (angle, dist, height)
         * These attributes will be fed into the arm to move the physical arm to a scoring spot
         */

        angle = Constants.SCORE_DATA_ARRAY[iy][ix].angle;
        dist = Constants.SCORE_DATA_ARRAY[iy][ix].dist;
        height = Constants.SCORE_DATA_ARRAY[iy][ix].height;


        /* 
         * Sum of these functions may not work yet
         * But they exist so i'm gonna call em!
        */

        /* Height */ 
        switch(height)
        {
            case BOT:
                RobotContainer.M_ARM.extendArmBotton();
                break;
            case MID:
                RobotContainer.M_ARM.extendArmMiddle();
                break;
            case TOP:
                RobotContainer.M_ARM.extendArmTop();
                break;
            default:
                System.out.println("Hey, bad height used!");
                return;
        }

        /* Angle, make this work joey borrelli!! */
        RobotContainer.M_ARM.angleArm(angle);

        /* Dist */
        RobotContainer.M_ARM.extendArm();
        /* ^^ Wrap in a loop to extend a certain amount, REAL encoders would be really helpful here, or if we test the values */
    }
}
