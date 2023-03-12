package frc.robot.commands;

/* Store data used for scoring */
public class ScoreData {
    public float offset;
    public float dist;
    AutoScore.heights height;

    ScoreData(float offset, float dist, AutoScore.heights height)
    {
        this.offset = offset;
        this.dist = dist; 
        this.height = height; /* Height would be only 3 values */
    }
}

