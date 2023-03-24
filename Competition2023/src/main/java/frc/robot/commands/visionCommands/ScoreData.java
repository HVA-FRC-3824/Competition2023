package frc.robot.commands.visionCommands;

/* Store data used for scoring */
public class ScoreData {
    public float offset;
    AutoScore.heights height;

    ScoreData(AutoScore.heights height)
    {
        this.height = height;
    }
}