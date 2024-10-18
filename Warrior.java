package cwk4;
import java.util.HashMap;

/**
 * Warrior child class  describes the specification for the Warrior
 * as required for 5COM2007 Cwk 4
 *  * @author: Udoette
 * @version
 */
public class Warrior extends Champion
{
    String weapon;
    private HashMap<String, Boolean>challenge = new HashMap<>();

    /** Constructor requires the Champion name,
     * the Champion skill level, the Champion fee,
     * the Champion, weapon and the Champion family
     * @param name
     * @param skill
     * @param fee
     * @param weapon
     * @param family
     * */
    public Warrior(String name, int skill, int fee, String weapon, String family)
    {
        super(name, fee/100, fee, family);
        this.weapon = weapon;

        challenge.put("Magic", false);
        challenge.put("Fight", true);
        challenge.put("Mystery", false );
    }
    /**Returns a String representation of the Warrior Champion including
     * the Warrior name, skill level, fee, family, state, weapon
     * and the challenge it fight.
     * @return returns the above as string
     **/
    public String toString()
    {
        String str = super.toString()+
                "\nWeapon: "+weapon+
                "\nChallenge: ";

        for(String key: challenge.keySet()){
            str += key + "[" +challenge.get(key) + "]. ";
        }
        return str;
    }

}
