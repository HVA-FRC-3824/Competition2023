package frc.robot.commands;

import frc.robot.Constants;

public class AutoScore {
    public static void initScoreValues()
    {
        /* Function should set all the values for SCORE_DATA_ARRAY */

        /* Ex. */

        

        Constants.SCORE_DATA_ARRAY[0][0].angle = 0;
        Constants.SCORE_DATA_ARRAY[0][0].dist = 5;
        Constants.SCORE_DATA_ARRAY[0][0].height = 0;

    }

    public void score(int ix, int iy)
    {
        /* do stuff idk based off the index and data 
         * Ex.
        */

        float angle, dist, height;

        /* SCORE_DATA_ARRAY is a 2D array of ScoreData objects
         * One ScoreData object has 3 attributes (angle, dist, height)
         * These attributes will be fed into the arm to move the physical arm to a scoring spot
         */

        angle = Constants.SCORE_DATA_ARRAY[iy][ix].angle;
        dist = Constants.SCORE_DATA_ARRAY[iy][ix].dist;
        height = Constants.SCORE_DATA_ARRAY[iy][ix].height;
    }
}
