/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cwk4.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aam
 */
public class T5MeetChallengeTest {
    CARE game;
    
    public T5MeetChallengeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("Jean");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
//Wizards    
    // Wizard facing magic
    @Test
    public void wizardFacingMagicWins() {
        int expected = 0;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(1);
        assertEquals("return 0 meaning you won",expected, actual);
    }
    
    @Test
    public void wizardFacingMagicWinsMoney() {
        int expected = 1000-400+100;
        game.enterChampion("Ganfrank");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals("treasury increases to 700", expected, actual);
    }
    
    @Test
    public void wizardFacingMagicLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Neon");
        int actual = game.meetChallenge(1);
        assertEquals( expected, actual);
    }
    
    @Test
    public void wizardFacingMagicLosesOnSkillMoneyDeducted() {
        int expected = 1000-300-100;
        game.enterChampion("Neon");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals("treasury decreases to 600", expected, actual);
    }
      
    @Test
    public void wizardLosingIsDisqualified() {
        game.enterChampion("Neon");
        game.meetChallenge(1);
        boolean actual = game.getChampionDetails("Neon").toLowerCase().contains("disqualified");
        assertTrue("Neon to remain in vizier team, but champion state change to disqualifed", actual);
    }
    
    //checking withdrawal of disqualified champion
    @Test
    public void cantWithdrawDead() {
        int expected = 1;
        game.enterChampion("Neon");
        game.meetChallenge(1);
        int actual= game.retireChampion("Neon");
        assertEquals("Neon to remain in vizier team as with Champion state disqualified", actual,expected);
    }
    
    @Test
    public void cantWithdrawDisqualifiedMoneyNotAffected() {
        int expected = 1000-300-100;
        game.enterChampion("Neon");
        game.meetChallenge(1); //assuming Neon looses
        game.retireChampion("Neon");
        int actual= game.getMoney();
        assertEquals("treasury remains 600", actual,expected);
    }

    @Test
    public void wizardFacingNoSuchMagic() {
        int expected = -1;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(14);
        assertEquals("Ganfrank should successfully win a fight challenge.", expected, actual);
    } 
    
    // Wizard facing fight
    @Test
    public void wizardFacingFightWins() {
        int expected = 0;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(2);
        assertEquals("Ganfrank should successfully win a fight challenge.", expected, actual);
    }
    
   @Test
    public void wizardFacingFightWinsMoneyAdded() {
        int expected = 1000-400+120;
        game.enterChampion("Ganfrank");
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals("Treasury should reflect the reward after Ganfrank wins the fight.",expected, actual);
    } 
    
    @Test
    public void wizardFacingFightLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Neon");
        int actual = game.meetChallenge(2);
        assertEquals("Neon should fail the fight challenge due to insufficient skills.", expected, actual);
    }
    
    @Test
    public void wizardFacingFightLosesOnSkillMoneyDeducted() {
        int expected = 1000-300-100;
        game.enterChampion("Neon");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals("Treasury should decrease by 100 after Neon fails a challenge.", expected, actual);
    }
    
    // Wizard facing mystery
    @Test
    public void wizardFacingMysteryWins() {
        int expected = 0;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(3);
        assertEquals("Ganfrank should successfully win a mystery challenge.", expected, actual);
    }
    
   @Test
    public void wizardFacingMysteryWinsMoneyAdded() {
        int expected = 1000-400+150;
        game.enterChampion("Ganfrank");
        game.meetChallenge(3);
        int actual = game.getMoney();
        assertEquals("Treasury should reflect the reward after Ganfrank wins the mystery.", expected, actual);
    } 
    
    @Test
    public void wizardFacingMysteryLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Neon");
        int actual = game.meetChallenge(3);
        assertEquals("Neon should fail the mystery challenge due to insufficient skills.", expected, actual);
    }
    
    @Test
    public void wizardFacingMysteryLosesOnSkillMoneyDeducted() {
        int expected = 1000-300-150;
        game.enterChampion("Neon");
        game.meetChallenge(3);
        int actual = game.getMoney();
        assertEquals("Treasury should decrease by 150 after Neon fails a mystery challenge.", expected, actual);
    }
   
//Warriors
    //Warrior facing magic - not allowed
    @Test
    public void warriorFacingMagicNotAllowed() {
        int expected = 2;
        game.enterChampion("Argon");
        game.meetChallenge(1);
        int actual = game.meetChallenge(1);
        assertEquals("Argon should not be able to meet a magic challenge.", expected, actual);
    }
    
    @Test
    public void warriorFacingMagicNotAllowedMoneyDeducted() {
        int expected = 0;
        game.enterChampion("Argon");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals("Treasury should not change because the challenge was not met.", expected, actual);
    }
  
    //Warrior facing fight
    @Test
    public void warriorFacingFightAllowedWins() {
        int expected = 0;
        game.enterChampion("Argon");
        int actual = game.meetChallenge(2);
        assertEquals("Argon should successfully meet a fight challenge.", expected, actual);
    }
      
    @Test
    public void warriorFacingFightAllowedWinsMoneyAdded() {
        int expected = 220;
        game.enterChampion("Argon");
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals("Treasury should be increased by the reward after Argon wins the fight.", expected, actual);
    }
    
    @Test
    public void warriorFacingFightAllowedLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Flimsi");
        int actual = game.meetChallenge(2);
        assertEquals("Flimsi should fail the fight challenge due to insufficient skills.", expected, actual);
    }
    
    @Test
    public void warriorFacingFightAllowedLosesMoneyDeducted() {
        int expected = 680;
        game.enterChampion("Flimsi");
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals("Treasury should decrease by 20 after Flimsi fails a fight challenge.", expected, actual);
    }
    
    //Warrior facing mystery - not allowed
    @Test
    public void warriorFacingMysteryNotAllowed() {
        int expected = 2;
        game.enterChampion("Argon");
        game.meetChallenge(3);
        int actual = game.meetChallenge(1);
        assertEquals("Treasury should not change because the challenge was not met.", expected, actual);
    }
    
    @Test
    public void warriorFacingMysteryNotAllowedMoneyDeducted() {
        int expected = -50;
        game.enterChampion("Argon");
        game.meetChallenge(3);
        int actual = game.getMoney();
        assertEquals("Treasury should not change because the challenge was not met.", expected, actual);
    }
    public void dragonFacingMagicNotAllowed() {
        game.enterChampion("Drabina"); // Drabina is a dragon, dragons cannot meet Magic challenges
        int expected = 2; // Magic challenges are not suitable for dragons
        int actual = game.meetChallenge(4); // Magic challenge
        assertEquals("Drabina should not be able to meet a magic challenge.", expected, actual);
    }

    @Test
    public void dragonFacingMagicNotAllowedMoneyDeducted() {
        game.enterChampion("Drabina");
        game.meetChallenge(4); // Drabina attempts a magic challenge and should fail
        int expected = 300; // money should be deducted because the challenge is not met
        int actual = game.getMoney();
        assertEquals("Treasury should  change because the challenge was not met by Drabina.", expected, actual);
    }

    // Dragon facing fight - allowed
    @Test
    public void dragonFacingFightLose() {
        game.enterChampion("Golum"); // Golnum is a dragon and can participate in Fight challenges
        int expected = 1; // Golum should lose the Fight challenge
        int actual = game.meetChallenge(6); // Fight challenge requires skill level 8, Golnum has 7
        assertEquals("Golum should lose the fight challenge.", expected, actual);
    }

    @Test
    public void dragonFacingFightLoseMoneyDeducted() {
        game.enterChampion("Golum");
        game.meetChallenge(6); // Golum loses the fight base on low skill level
        int expected = 455; // 1000 initial - 500 entry - 45 reward
        int actual = game.getMoney();
        assertEquals("Treasury should decrease after Golum loses the fight challenge.", expected, actual);
    }
    // Dragon facing mystery - allowed only if they talk
    @Test
    public void dragonFacingMysteryAllowedIfTalks() {
        game.enterChampion("Golum"); // Golnum is a talking dragon, can meet Mystery challenges
        int expected = 0; // Golnum can win because he talks
        int actual = game.meetChallenge(3); // Mystery challenge requires skill level 3, Golnum has 7 and can talk
        assertEquals("Golum should be able to meet a mystery challenge because he talks.", expected, actual);
    }

    @Test
    public void dragonFacingMysteryAllowedIfTalksMoneyAdded() {
        game.enterChampion("Golum");
        game.meetChallenge(3); // Golum wins the mystery, earns reward
        int expected = 650; // 1000 initial - 500 entry + 150 reward
        int actual = game.getMoney();
        assertEquals("Treasury should increase after Golum wins the mystery challenge.", expected, actual);
    }

    @Test
    public void dragonFacingMysteryNotAllowedIfNotTalks() {
        game.enterChampion("Drabina"); // Drabina is a non-talking dragon, cannot meet Mystery challenges
        int expected = 2; // Drabina should not be allowed to participate
        int actual = game.meetChallenge(9); // Mystery challenge requires skill level 9, Drabina has 7 and cannot talk
        assertEquals("Drabina should not be able to meet a mystery challenge because she doesn't talk.", expected, actual);
    }

    @Test
    public void dragonFacingMysteryNotAllowedIfNotTalksMoneyDeducted() {
        game.enterChampion("Drabina");
        game.meetChallenge(9); // Drabina attempts a mystery challenge and should fail
        int expected = 200; // No money should be deducted because the challenge is not met
        int actual = game.getMoney();
        assertEquals("Treasury should not change because the mystery challenge was not met by Drabina.", expected, actual);
    }


}
