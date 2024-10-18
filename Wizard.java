package cwk4;
import java.util.HashMap;

/**
 * Warrior child class  describes the specification for the Warrior
 * as required for 5COM2007 Cwk 4
 *  * @author: Udoette
 * @version
 */
public class Wizard extends Champion
{
    // instance variables - replace the example below with your own
    private boolean necro;
    private String spell;
    private HashMap<String, Boolean>challenge = new HashMap<>();

    /** Constructor requires the Champion name,
     * the Champion skill level, the Champion fee,
     * the Champion, weapon and the Champion family
     * @param name
     * @param skill
     * @param fee
     * @param necro
     * @param spell
     * @param family
     * */
    public Wizard(String name, int skill, int fee, boolean necro, String spell, String family)
    {
        super(name, skill, fee, family);
        this.necro = necro;
        this. spell = spell;

        challenge.put("Magic", true);
        challenge.put("Fight", true);
        challenge.put("Mystery", true);
    }

    /**Returns a String representation of the Wizard Champion including
     * the Warrior name, skill level, fee, family, state, spell
     * and the challenge it fight.
     * @return returns the above as string
     **/
    public String toString()
    {
        String str = super.toString()+
                "\nNecromancer: "+necro+
                "\nSpeciality: "+spell+
                "\nChallenge: ";

        for(String key: challenge.keySet()){
            str += key + "[" +challenge.get(key) + "]. ";
        }
        return str;
    }
}
