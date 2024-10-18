package cwk4;
import java.util.*;
import java.io.*;
/**
 * This interface specifies the behaviour expected from CARE
 * as required for 5COM2007 Cwk 4
 *
 *  * @author: Udoette
 *  *@author: Lay
 *
 * @version 
 */

public class Tournament implements CARE
{
    private String vizier;
    private ArrayList<Challenge>challenges = new ArrayList<>();
    private int treasury = 1000;
    private HashMap<String, Champion> vizTeam = new HashMap<>();
    private HashMap<String, Champion> reserved = new HashMap<>();


//**************** CARE ************************** 
    /** Constructor requires the name of the vizier
     * @param viz the name of the vizier
     */  
    public Tournament(String viz)
    {
       setupChampions();
       setupChallenges();
       vizier = viz;
    }
    
    /** Constructor requires the name of the vizier and the
     * name of the file storing challenges
     * @param viz the name of the vizier
     * @param filename name of file storing challenges
     */  
    public Tournament(String viz, String filename)  //Task 3.5
    {
       setupChampions();
       readChallenges(filename);

    }
    
    
    /**Returns a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     * 
     * @return a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     **/
    public String toString()
    {
        String s = "\nVizier: " + vizier +'\n'
                    + "Treasury: "+ treasury +" gulden"+ '\n'
                    + "Defeated? " + isDefeated() + '\n'
                    + getTeam()+'\n';
        return s;
    }
    
    
    /** returns true if Treasury <=0 and the vizier's team has no
     * champions which can be retired.
     * @returns true if Treasury <=0 and the vizier's team has no
     * champions which can be retired.
     */
    public boolean isDefeated()
    {
        Collection<Champion> col = vizTeam.values();
        if (treasury <= 0){
            for (Champion champ: col){
                if (champ.getChampionState() != ChampionState.DISQUALIFIED){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /** returns the amount of money in the Treasury
     * @returns the amount of money in the Treasury
     */
    public int getMoney()
    {
        return treasury;
    }
    
    
    /**Returns a String representation of all champions in the reserves
     * @return a String representation of all champions in the reserves
     **/
    public String getReserve()
    {   
        String s = "************ Champions available in reserves********\n";
        Collection<Champion>col = reserved.values();
        for(Champion champ: col){
            s += champ.toString()+"\n\n";
        }
        return s;
    }
    
        
    /** Returns details of the champion with the given name. 
     * Champion names are unique.
     * @return details of the champion with the given name
     **/
    public String getChampionDetails(String nme)
    {
        Champion champ = reserved.get(nme);
        if (champ != null){
            return champ.toString();
        }
        champ = vizTeam.get(nme);
        if (champ != null){
            return champ.toString();
        }
        return "\nNo such champion";
    }    
    
    /** returns whether champion is in reserve
    * @param nme champion's name
    * @return true if champion in reserve, false otherwise
    */
    public boolean isInReserve(String nme)
    {
        Champion champ = reserved.get(nme);
        if (champ != null) {
            return true;
        }
        return false;
    }

    /** returns a champion is in any list provided as argument
     * @param nme champion's name
     * @param list: arraylist to find champion from
     * @return a champion if found in list otherwise, return null
     */


    // ***************** Team champions ************************   
     /** Allows a champion to be entered for the vizier's team, if there 
     * is enough money in the Treasury for the entry fee.The champion's 
     * state is set to "active"
     * 0 if champion is entered in the vizier's team, 
     * 1 if champion is not in reserve, 
     * 2 if not enough money in the treasury, 
     * -1 if there is no such champion 
     * @param nme represents the name of the champion
     * @return as shown above
     **/
    public int enterChampion(String nme) {
        Champion champ = reserved.get(nme);
        if (champ != null) {
            if (champ.getFee() <= treasury) {
                treasury -= champ.getFee();
                champ.setChampionState(ChampionState.ENTERED);
                vizTeam.put(champ.getName(), champ);
                reserved.remove(nme);
                return 0;
            } else {return 2;}
        } else if  (isInViziersTeam(nme) == true){return 1;}

        return -1;
    }
        
     /** Returns true if the champion with the name is in 
     * the vizier's team, false otherwise.
     * @param nme is the name of the champion
     * @return returns true if the champion with the name
     * is in the vizier's team, false otherwise.
     **/
    public boolean isInViziersTeam(String nme)
    {
        Champion champ = vizTeam.get(nme);
        if (champ != null){
            return true;
        }
        return false;
    }
    
    /** Removes a champion from the team back to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because disqualified
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     * @param nme is the name of the champion
     * @return as shown above 
     **/
    public int retireChampion(String nme)
    {
        if(isInViziersTeam(nme)){
            Champion champ = vizTeam.get(nme);
            if(champ != null && !isDisqualified(nme)){
                treasury += (champ.getFee() * 0.5);
                champ.setChampionState(ChampionState.WAITING);
                vizTeam.remove(nme);
                reserved.put(nme, champ);
                return 0;
            }else {return 1;}
        }else if (isInReserve(nme)){return 2;}
        return -1;
    }

    /**Returns a String representation of the champions in the vizier's team
     * or the message "No champions entered"
     * @return a String representation of the champions in the vizier's team
     **/
    public String getTeam()
    {
        String s = "************ Vizier's Team of champions********";
        boolean find = false;
        Collection<Champion>col = vizTeam.values();
        for(Champion chp: col){
            find = true;
            s += "\n"+chp.toString()+"\n";
        }
        if (find) {return s;}
        return "No champions entered";
    }
    
     /**Returns a String representation of the disquakified champions in the vizier's team
     * or the message "No disqualified champions "
     * @return a String representation of the disqualified champions in the vizier's team
     **/
    public String getDisqualified()
    {
        String s = "************ Vizier's Disqualified champions********";
        boolean find = false;
        Collection<Champion>col = vizTeam.values();
        for(Champion chp: col){
            if(chp.getChampionState() == ChampionState.DISQUALIFIED){
                find = true;
                s += +'\n'+chp.toString()+"\n\n";
            }
        }
        if (find) {return s;}
        return "No disqulified champions";
    }
    
//**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the  number of the challenge
     * @return true if the  number represents a challenge
     **/
     public boolean isChallenge(int num)
     {
         if (num >= 1 && num <= 12){
             return true;
         }
         return (false);
     }
   
    /** Provides a String representation of an challenge given by 
     * the challenge number
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num)
    {
        if (isChallenge(num)){
            for(Challenge chal: challenges){
                if (chal.getNo() == num){
                    return chal.toString();
                }
            }
        }
        return "\nNo such challenge";
    }
    
    /** Provides a String representation of all challenges 
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges()
    {
        String s = "\n************ All Challenges ************\n";
        for(Challenge ch: challenges){
            s += ch.toString()+"\n\n";
        }
        return s;
    }

    /** Returns true if the champion with the name in
     * vizier's team is disqualified, false otherwise.
     * @param name is the champion object
     * @return returns true if the champion with the name
     * is in the vizier's team is disqualified, false otherwise.
     **/
    private boolean isDisqualified(String name)
    {
        Champion champ = vizTeam.get(name);
        if (champ != null && champ.getChampionState() == ChampionState.DISQUALIFIED){
            return true;
        }
        return false;
    }

    /** Returns true if all the champions in the vizier team are
     * disqualified, false otherwise.
     * @return returns true if all the champions in vizier  team
     * are disqualified, false otherwise.
     **/
    private boolean noChampionsAvailableInVizTeam() {
        Collection<String> col = vizTeam.keySet();
        for (String name : col) {
            if (!isDisqualified(name)) {
                return false;
            }
        }
        return true;
    }

    /** Provides a challenge given the challenge number, null otherwise
     * @param no the number of the challenge
     * @return the challenge object, null otherwise.
     **/

    private Challenge getAChallenge(int no)
    {
        for (Challenge challenge : challenges)
        {
            if (challenge.getNo() == no) {
                return challenge;
            }
        }
        return null;
    }

    /** returns a suitable Champion name in vizier team for a given challenge
     * null otherwise
     * @param chal chal object
     * @return returns the champion name as string, null otherwise.
     **/
    private String getChampionByType(Challenge chal) {
        Collection<Champion>col = vizTeam.values();
        for (Champion champion : col) {
            ChallengeType chalType = chal.getType();
            String name = champion.getName();
            if (champion instanceof Warrior && chalType == ChallengeType.FIGHT && !isDisqualified(name)){
                return name;
            }else if(champion instanceof Dragon && chalType == ChallengeType.FIGHT && !isDisqualified(name)){
                return name;
            } else if (champion instanceof Dragon && chalType == ChallengeType.MYSTERY && ((Dragon)champion).canTalk() == true ){
                if (!isDisqualified(name))
                    return name;
            }else if (champion instanceof Wizard && !isDisqualified(name)){return name;}
        }
        return null; // this will return null when the viz doesn't have a champ of the right type within their team.
    }
    
       /** Retrieves the challenge represented by the challenge 
     * number.Finds a champion from the team who can meet the 
     * challenge. The results of meeting a challenge will be 
     * one of the following:  
     * 0 - challenge won by champion, add reward to the treasury, 
     * 1 - challenge lost on skills  - deduct reward from
     * treasury and record champion as "disqualified"
     * 2 - challenge lost as no suitable champion is  available, deduct
     * the reward from treasury 
     * 3 - If a challenge is lost and vizier completely defeated (no money and 
     * no champions to withdraw) 
     * -1 - no such challenge 
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */ 
    public int meetChallenge(int chalNo) {
        Challenge challenge = getAChallenge(chalNo);
        int outcome = 0; // just initializing it
        if (challenge == null) {
            //There are no more available challenges to accept.
            return -1;
        }
        String name = getChampionByType(challenge);
        Champion champion;
        if (name != null ){
            champion = vizTeam.get(name);
        }else{
            champion = null;
        }
        if (champion == null) {
            treasury -= challenge.getChallengeReward();
            outcome = 2; // checking this first as there may be an error if we attempt to check without having a valid champion.
        }else if (champion.getSkillLevel() >= challenge.getChallengeSkill()) {
            treasury += challenge.getChallengeReward();
            outcome = 0;
        }else if (champion.getSkillLevel() < challenge.getChallengeSkill()) {
            treasury -= challenge.getChallengeReward();
            champion.setChampionState(ChampionState.DISQUALIFIED);
            outcome = 1;
        }
        if (outcome == 2 && treasury <= 0 && noChampionsAvailableInVizTeam()) {
            outcome = 3;
        }
        return outcome;
    }

    /**
     * Create and loads all Champions into the reseved list
    */
    //****************** private methods for Task 3 functionality*******************
    //*******************************************************************************
    private void setupChampions()
    {

        Wizard c1 = new Wizard("Ganfrank", 7, 400, true, "transmutation", "Wizard");
        Wizard c2 = new Wizard("Rudolf", 6, 400, true, "invisibility", "Wizard");
        Warrior c3 = new Warrior("Elblond", 1, 150, "sword", "Warrior");
        Warrior c4 = new Warrior("Flimsi", 2, 200, "bow", "Warrior");
        Dragon c5 = new Dragon ("Drabina", 7, 500, false, "Dragon");
        Dragon c6 = new Dragon ("Golum", 7, 500, true, "Dragon");
        Warrior c7 = new Warrior("Argon", 9, 900, "mace", "Warrior");
        Wizard c8 = new Wizard("Neon", 2, 300, false, "translocation", "Wizard");
        Dragon c9 = new Dragon ("Xenon", 7, 500, true, "Dragon");
        Warrior c10 = new Warrior("Atlanta", 9, 500, "bow", "Warrior");
        Wizard c11 = new Wizard("Krypton", 8, 300, true, "fireballs", "Wizard");
        Wizard c12 = new Wizard("Hedwig", 1, 400, true, "flying", "Wizard");

        reserved.put(c1.getName(), c1);
        reserved.put(c2.getName(), c2);
        reserved.put(c3.getName(), c3);
        reserved.put(c4.getName(), c4);
        reserved.put(c5.getName(), c5);
        reserved.put(c6.getName(), c6);
        reserved.put(c7.getName(), c7);
        reserved.put(c8.getName(), c8);
        reserved.put(c9.getName(), c9);
        reserved.put(c10.getName(), c10);
        reserved.put(c11.getName(), c11);
        reserved.put(c12.getName(), c12);

    }

    /**
     * Create and loads all challenges into the challenge list
     */
    private void setupChallenges()
    {
        challenges = new ArrayList<>(); //this will create a new array list for the challenges
        
        Challenge c1 = new Challenge(1, ChallengeType.MAGIC, "Borge", 3, 100);
        Challenge c2 = new Challenge(2, ChallengeType.FIGHT, "Huns", 3, 120);
        Challenge c3 = new Challenge(3, ChallengeType.MYSTERY, "Ferengi", 3, 150);
        Challenge c4 = new Challenge(4, ChallengeType.MAGIC, "Vandal", 9, 200);
        Challenge c5 = new Challenge(5, ChallengeType.MYSTERY, "Huns", 7, 90);
        Challenge c6 = new Challenge(6, ChallengeType.FIGHT, "Goth", 8, 45);
        Challenge c7 = new Challenge(7, ChallengeType.MAGIC, "Frank", 10, 200);
        Challenge c8 = new Challenge(8, ChallengeType.FIGHT, "Sith", 10, 170);
        Challenge c9 = new Challenge(9, ChallengeType.MYSTERY, "Cardashian", 9, 300);
        Challenge c10 = new Challenge(10, ChallengeType.FIGHT, "Jute", 2, 300);
        Challenge c11 = new Challenge(11, ChallengeType.MAGIC, "Celt", 2, 250);
        Challenge c12 = new Challenge(12, ChallengeType.MYSTERY, "Celt", 1, 250);

        challenges.add(c1);
        challenges.add(c2);
        challenges.add(c3);
        challenges.add(c4);
        challenges.add(c5);
        challenges.add(c6);
        challenges.add(c7);
        challenges.add(c8);
        challenges.add(c9);
        challenges.add(c10);
        challenges.add(c11);
        challenges.add(c12);
    }

    //*******************************************************************************
    //*******************************************************************************
    /************************ Task 3.5 ************************************************/  
    
    // ***************   file write/read  *********************
    /**
     * reads challenges from a comma-separated textfile and stores in the game
     * @param filename of the comma-separated textfile storing information about challenges
     */
    public void readChallenges(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line; int no = 1; ChallengeType type = ChallengeType.MAGIC;

            while ((line = reader.readLine()) != null) {
                String chalinfo[] = line.split(",");

                if(chalinfo[0].equals("Magic"))
                {
                    type = ChallengeType.MAGIC;
                }else if(chalinfo[0].equals("Mystery")){
                    type = ChallengeType.MYSTERY;
                }else if (chalinfo[0].equals("Fight")){
                    type = ChallengeType.FIGHT;
                }
                String name = chalinfo[1];
                int skill = Integer.parseInt(chalinfo[2]);
                int reward = Integer.parseInt(chalinfo[3]);
                Challenge challenge = new Challenge(no, type, name, skill, reward);
                no++;
                challenges.add(challenge);
            }
            reader.close();
            System.out.println("The new challenge has been added into challenges");
        } catch (IOException e) {
            System.out.println("Something has happened to prevent the challenges from being added " + e.getMessage());
        }
    }

    /**
     * reads all information about the game from the specified file
     * and returns a CARE reference to a Tournament object, or null
     *
     * @param fname name of file storing the game
     * @return the game (as a Tournament object)
     */
    public Tournament loadGame(String fname) {   // uses object serialisation
        Tournament tor = null;
        try{
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tor = (Tournament)ois.readObject();
            ois.close();
            System.out.println("Game read from file "+ fname);
        } catch(ClassNotFoundException var5){
            System.out.println("Cannot load data - class not available");
        }
        catch(IOException var6){
            System.out.println("Unable to open file for reading");
        }
        return tor;
    }
    /**
     * Writes whole game to the specified file
     *
     * @param fname name of file storing requests
     * @return
     */
    public String saveGame(String fname) {
        //String report = "Game written to file "+ fname;
        int outcome = 0;
        try{
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this);
            outcome = 1;
        }
        catch(IOException var4){
            //System.out.println("unable to open file for writing");
            outcome = 0;
        }
        if (outcome == 1){
            return "Game save to file successfully"+ fname;
        }else {
            return "unable to open file for writing";
        }
    }
}
