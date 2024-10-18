package cwk4;

public class Tester
{
    public static void main(String args[])
    {
        /*Wizard Ganfrank = new Wizard("Ganfrank", 7, 400, true, "transmuatation", "Wizard");
        Warrior Elblond = new Warrior("Elblond", 1, 150, "sword", "Warrior");
        Dragon Drabina = new Dragon ("Drabina", 7, 200, false, "Dragon");

        System.out.println(Ganfrank.toString());
        System.out.println();
        System.out.println(Elblond.toString());
        System.out.println();
        System.out.println(Drabina.toString());
        System.out.println();

        //Challenge
        Challenge Magic = new Challenge(1, ChallengeType.MAGIC, "Borge", 3, 100);
        System.out.println(Magic.toString());*/

        /*Wizard Ganfrank = new Wizard("Ganfrank", 7, 400, true, "transmuatation", "Wizard");
        Tournament tr = new Tournament("xternal");
        System.out.println(tr.toString());
        tr.enterChampion("Ganfrank");
        tr.saveGame("gameFile.dat");
        System.out.println(tr.getChallenge(1));
        Tournament game = tr.loadGame("gameFile.dat");
        System.out.println(game.getTeam());
        System.out.println(game.toString());*/

        Tournament tr = new Tournament("xternal");
        Dragon Drabina = new Dragon ("Drabina", 7, 200, false, "Dragon");
        Dragon Golum = new Dragon ("Golum", 7, 500, true, "Dragon");
        tr.enterChampion("Golum");
        System.out.println(tr.meetChallenge(3)); // Drabina attempts a magic challenge and should fail
        System.out.println(tr.toString());
    }
}
