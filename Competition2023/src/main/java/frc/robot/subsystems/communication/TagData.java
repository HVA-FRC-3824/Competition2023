package frc.robot.subsystems.communication;

/* Simple object to hold last known data for tags
 * keep an array of all tags and their ids you want to manage
 * and change info accordingly
 * 
 * To access certain data on that tag simply access the array in constants
 * ie. if the id = 1 then TAG_DATA[id-1];
*/

public class TagData {
    /* Values, keep public for easy access*/ 
    int   id; /* 1 byte of data, 1-128 */
    float dist;
    float angle;

    /* Simple initializer */
    public TagData(int id)
    {
        this.id = id;
    }
}