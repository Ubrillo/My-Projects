package cwk4;
import java.util.HashMap;

/**
 * Dragon child class  describes the specification features for the Dragon Champion
 * as required for 5COM2007 Cwk 4
 *  * @author: Udoette
 * @version
 */
public class Dragon extends Champion
{
    private  boolean talk;
    private HashMap<String, Boolean>challenge = new HashMap<>();

    /** Constructor requires the Champion name,
     * the Champion skill level, the Champion fee,
     * Champion talking ability and Champion family
     * @param name
     * @param skill
     * @param talk
     * @param fee
     * @param family
     * */

    public Dragon(String name, int skill, int fee, boolean talk, String family)
    {
        super(name, skill, fee, family);
        this.talk = talk;
        challenge.put("Magic", false);
        challenge.put("Fight", true);

        if (talk)
            challenge.put("Mystery", true);
        else
            challenge.put("Mystery", false);
    }

    /**Returns true if the Champion can talk, false otherwise
     * @retun returns true if Champion can talk, false otherwise
     * */
    public boolean canTalk()
    {
        if (talk){ return true; }
        return false;
    }

    /*public HashMap getChallenge()
    {
        return challenge;
    }*/

    /**Returns a String representation of the Dragon Champion
     *  including Champion name, skill level, fee, family, state,
     *  if it can talk, and challenge it can fight
     * @return returns the shown above as string
     **/
    public String toString()
    {
        String str = super.toString()+
                "\nCan talk?: "+talk+
                "\nChallenge: ";
        for(String key: challenge.keySet()){
            str += key + "[" +challenge.get(key) + "]. ";
        }
        return str;
    }
}
