
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cwk4.CARE;
import cwk4.Tournament;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 *
 * @author comqaam
 */
public class T8PersistenceTest
{
    CARE game;
    public T8PersistenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        game = new Tournament("Xternal", "challengesAM.txt"); //loads the tournament challenges from the file

    }

    @After
    public void tearDown() {

    }

    /** Testing Strings is not pleasant. These are not very good tests as the
     * take no account of capital/lower case, spaces etc.You could use trim()
     * & toLowerCase().They also don't check the order of String components
     * (do we care?). But they are enough
     */

    //just a local method to check a String for contents
    private boolean containsText(String text, String[] s) {
        boolean check = true;
        for(int i=0; i < s.length; i++)
            check = check && text.contains(s[i]);
        return check;
    }

    @Test
    public void FromFilechallengeMagicDisplayed() {
        boolean actual = false;
        String result = game.getAllChallenges();
        String[] expectedText = {"1", "Magic", "Borg", "3", "100"};
        assertTrue("Magic challenge should be displayed correctly.", containsText(result, expectedText));
    }

    @Test
    public void FromFilechallengeFightDisplayed() {
        boolean actual = false;
        String result = game.getAllChallenges();
        String[] expectedText = {"2", "Fight", "Huns", "3", "120"};
        assertTrue("Fight challenge should be displayed correctly.", containsText(result, expectedText));
    }

    @Test
    public void FromFilechallengeMysteryDisplayed() {
        boolean actual = false;
        String result = game.getAllChallenges();
        String[] expectedText = {"3", "Mystery", "Ferengi", "3", "150"};
        assertTrue("Mystery challenge should be displayed correctly.", containsText(result, expectedText));
    }

    // You can add more but is it worth it ?

    @Test
    public void FromFileisChallengeValidTest() {
        assertTrue("Challenge 3 should exist.", game.isChallenge(3));
        assertTrue("Challenge 1 should exist.", game.isChallenge(1));
        assertTrue("Challenge 9 should exist.", game.isChallenge(9));
        assertFalse("Challenge 14 should not exist.", game.isChallenge(14));
        assertFalse("Challenge 0 should not exist.", game.isChallenge(0));
    }

    @Test
    public void getChallengeDetailsTest() {
        String result = game.getChallenge(3);
        assertTrue("Details for Challenge 3 should be correct.", result.contains("Mystery") && result.contains("Ferengi") && result.contains("150"));
    }

    @Test
    public void FromFileLoadGameSuccessful(){

        game.enterChampion("Ganfrank"); //enter champion to vizier team
        game.saveGame("gameFile.dat"); //Champion remains in vizier team after retrieving game
        CARE savedGame = game.loadGame("gameFile.dat");// champion remains in vizier after loading game
        String actual = savedGame.getTeam();
        String expectedText[] = { "Entry Fee", "400", "Ganfrank", "Wizard", "Necromancer",
                                "transmutation", "State", "Entered"};
        boolean result = containsText(actual, expectedText);
        assertTrue("Game data should be intact after saving and loading game from file", result);
    }

    @Test
    public void  saveGameToFileSuccessful(){
        game.enterChampion("Ganfrank");
        String actual = game.saveGame("gameFile.dat");//given a correct file name
        String expectedText[] = {"Game save to file successfully", "gameFile.dat"};
        boolean result = containsText(actual, expectedText);
        assertTrue("Appropriate feedback should be reported if game is save to file successfuly", result);
    }
    @Test
    public void  saveGameToFileUnSuccessful(){
        game.enterChampion("Ganfrank");
        String actual = game.saveGame("gameFile.d"); //given a wrong file name
        String expectedText[] = {"unable to open file for writing"};
        boolean result = containsText(actual, expectedText);
        assertFalse("Appropriate feedback should be reported if game cannot be saved to file", result);
    }
    @Test
    public void treasuryAfterLoadgame(){
        game.enterChampion("Ganfrank");
        game.saveGame("gameFile.dat");
        CARE savedGame = game.loadGame("gameFile.dat");
        int expectedInt = 600;
        int actualInt = savedGame.getMoney();
        assertEquals("The treasury amount was saved correctly.", expectedInt, actualInt );
    }

    @Test
    public void wizardFacingMagicLosesOnSkillaftersave() {
        int expected = 1;
        game.enterChampion("Neon");
        game.saveGame("gameFile.dat");
        CARE savedGame = game.loadGame("gameFile.dat");
        int actual = game.meetChallenge(1);
        assertEquals(expected, actual);
        boolean result = game.getChampionDetails("Neon").toLowerCase().contains("disqualified");
        assertTrue("Neon should remain disqualified after the game has been saved and loaded from. ", result);
    }

    @Test
    public void saveGameNotEmpty() {
        game.enterChampion("Ganfrank");
        game.saveGame("gameFile.dat");
        File notemptyCheck = new File("gameFile.dat");
        assertTrue("The file gameFile.dat isn't empty after having the game saved to it.", notemptyCheck.length() > 0);
    }

}
