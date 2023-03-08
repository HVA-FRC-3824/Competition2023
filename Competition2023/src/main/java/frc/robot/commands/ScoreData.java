package frc.robot.commands;

/* Store data used for scoring */
public class ScoreData {
    public float angle;
    public float dist;
    AutoScore.heights height;

    ScoreData(float angle, float dist, AutoScore.heights height)
    {
        this.angle = angle;
        this.dist = dist; 
        this.height = height; /* Height would be only 3 values */
    }
}
