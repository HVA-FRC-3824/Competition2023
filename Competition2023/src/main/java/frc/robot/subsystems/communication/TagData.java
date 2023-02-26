package frc.robot.subsystems.communication;
import frc.robot.Constants;
/* Simple object to hold last known data for tags
 * keep an array of all tags and their ids you want to manage
 * and change info accordingly
 * 
 * To access certain data on that tag simply access the array
 * ie. if the id = 1 then TAG_DATA[id-1];
*/

public class TagData {
    /* Values, keep public for easy access*/ 
    public int        id; 
    public float      dist;
    public float      angle;
    public static int last_known_id;

    /* Create an array of TAG_DATAs to store information about each tag */
    public static TagData[] TAG_DATA = new TagData[Constants.MAX_TAGS];

    /* Simple initializer */
    public TagData(int id, float defaultD, float defaultA)
    {
        this.id = id;
        this.dist = defaultD;
        this.angle = defaultA;
    }

    public float tag_returnDist(int id)
    {
        return TAG_DATA[id-1].dist;
    }

    public float tag_returnAngle(int id)
    {
        return TAG_DATA[id-1].angle;
    }
}