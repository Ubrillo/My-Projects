package cwk4;
import java.io.Serializable;

/**
 * Champion class  describes the specification for the Champion
 * as required for 5COM2007 Cwk 4
 *  * @author: Udoette
 * @version
 */
public class Champion implements Serializable
{
    private String name;
    private int skillLevel;
    private int fee;
    private String family;
    private ChampionState state;


    /** Constructor requires the Champion name,
     * the Champion skill level, the Champion fee
     * and Champion family
     * @param name
     * @param skill
     * @param fee
     * @param family
     * */
    public Champion(String name, int skill, int fee, String family)
    {
        this.name = name;
        this.skillLevel = skill;
        this.fee = fee;
        this.family = family;
        state = state.WAITING;
    }

    /**Returns the name of the Champion
     * @return returns the Champion name as string
     */
    public String getName()
    {
        return name;
    }

    /**Returns the state of the Champion
     * @return returns the state of the Champion
     * as enum ChampionState structure
     */
    public ChampionState getChampionState(){
        return state;
    }

    /**Returns the skill level of the Champion
     * @return returns the Champion skill level
     * as integer
     */
    public int getSkillLevel()
    {
        return skillLevel;
    }

    /**Sets the Champion current state to the provided state
     * @param newState
     */
    public void setChampionState(ChampionState newState)
    {
        state = newState;
    }

    /**Returns the Champion fee
     * @return returns the Champion fee
     * as integer
     */
    public int getFee(){
        return fee;
    }

    /**Returns a String representation of the Champion including
     * the Champion name, skill level, fee, family and state
     * @return returns the above as string
     **/
    public String toString()
    {
        return "Champion Name: "+name+
                "\nSkill Level: "+skillLevel+
                "\nEntry Fee: "+fee+
                "\nClass: "+family+
                "\nState: "+state;
    }
}
