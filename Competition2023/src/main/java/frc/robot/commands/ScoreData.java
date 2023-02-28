package frc.robot.commands;

/* Store data used for scoring */
public class ScoreData {
    public float angle;
    public float dist;
    public float height;

    ScoreData(float angle, float dist, float height)
    {
        this.angle = angle;
        this.dist = dist;
        this.height = height;
    }
}
