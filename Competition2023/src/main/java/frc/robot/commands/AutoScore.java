package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.communication.TagData;
import frc.robot.subsystems.communication.TagStateHandler;

public class AutoScore extends CommandBase{

    private int followed_Tag = 0;

    private int timeout = 0; /* counter for timeout */

    public static void initScoreValues(){
        /* Function should set all the values for SCORE_DATA_ARRAY */

        /* Ex. */

        /* Height! */
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
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

        /* Set hard offset and dist */
        Constants.SCORE_DATA_ARRAY[0][0].offset = 0;
        Constants.SCORE_DATA_ARRAY[0][0].dist = 5;
    }

    public static enum heights{BOT,MID,TOP};

    /* Returns -1 for error */
    public int follow_Tag(int id)
    {
        this.followed_Tag = id;
        followed_Tag--; /* For index */
        while(true)
        {    
            /* The crazy insane ultra long if statement */
            if(TagStateHandler.tag_Available[followed_Tag] && TagData.TAG_DATA[followed_Tag].tag_returnDist() != Constants.MIN_DIST_TO_TAG)
            {   
                timeout = 0;
                /* Crazy wizard magic to round up the number */
                int rounded_Angle = (int)(TagData.TAG_DATA[followed_Tag].tag_returnAngle()+.5);
                if(rounded_Angle != 0)
                { 
                    /* TODO: figure out how the angle changes based on turn off the robot */
                    if(rounded_Angle > 0)
                    {/* Move either right or left. */}
                    else
                    {/* Move either right or left. */}

                    continue;
                }

                /* Move forward some amount 
                 * TODO: Find amount
                */
            }
            else
            { 
                if(TagData.TAG_DATA[followed_Tag].tag_returnDist() == Constants.MIN_DIST_TO_TAG){return 1;} 

                /* Update timeout if the tag cannot be seen */
                System.out.println("Tag timing out! Uh-oh!"); timeout++;
                if(timeout == 10){ System.out.println("Tag fully lost, timeout reached. End of command! :("); return -1; }}
        }
    }

    public void score(int ix, int iy){
        float offset, dist;
        heights height;

        /* SCORE_DATA_ARRAY is a 2D array of ScoreData objects One ScoreData object has 3 attributes (angle, dist, height)
         * These attributes will be fed into the arm to move the physical arm to a scoring spot */

        offset = Constants.SCORE_DATA_ARRAY[iy][ix].offset;
        dist = Constants.SCORE_DATA_ARRAY[iy][ix].dist;
        height = Constants.SCORE_DATA_ARRAY[iy][ix].height;
 
        // Height 
        switch(height){
            case BOT:
                RobotContainer.ARM_OBJ.extendArmBotton();
                break;
            case MID:
                RobotContainer.ARM_OBJ.extendArmMiddle();
                break;
            case TOP:
                RobotContainer.ARM_OBJ.extendArmTop();
                break;
            default:
                System.out.println("ERROR: HEIGHT SWITCH CASE FAIL");
                return;
        }

        /* Move robot by an offset, -/+ : left/right */

        // Dist
        RobotContainer.ARM_OBJ.extendArm();
        /* ^^ Wrap in a loop to extend a certain amount, REAL encoders would be really helpful here, or if we test the values */
    }
}