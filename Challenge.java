package cwk4;
import java.io.Serializable;


/**
 * Challenge class  describes the specification for  the Challenge
 * as required for 5COM2007 Cwk 4
 *  * @author: Matthew Lay
 * @version
 */
public class Challenge implements Serializable
{
    private int no;
    //private String type;
    private String enemy;
    private int skillLevel;
    private int reward;
    private ChallengeType type;

    /** Constructor requires the challenge no, the type of challenge,
     * name of Enemy, skill level and reward
     * @param id
     * @param type
     * @param badMan
     * @param skill
     * @param reward
     */
    public Challenge(int id, ChallengeType type, String badMan, int skill, int reward)
    {
        this.no = id;
        this.type = type;
        skillLevel = skill;
        this.reward = reward;
        enemy = badMan;
    }

    /**
     * Returns the challenge no
     * @return returns Challenge no
     * as integer
     * */
    public int getNo() {
        return no;
    }

    /**Returns the challenge type
     * * @return returns Challenge type
     * as ChallengeType structure
     */
    public ChallengeType getType() {
        return type;
    }


    /**Returns the reward as an integer
     * @return returns the Challenge reward
     * as integer
     */
    public int getChallengeReward()
    {
        return reward;
    }

    /**Returns skill level of the challenge
     * as integer
     * @return Challenge skill level as integer
     */
    public int getChallengeSkill()
    {
        return skillLevel;
    }

    /**Returns a String representation of the challenge including
     * the challenge no, challenge type, name of enemy, skill level and reward
     * @return returns as shown above as string
     **/
    public String toString()
    {
        return "Challenge No: "+no+
                "\nType: "+type+
                "\nEnemy: "+enemy+
                "\nSkill Level: "+skillLevel+
                "\nReward: "+reward;
    }
}
