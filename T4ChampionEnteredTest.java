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
 * @author jp14adn
 */
public class T4ChampionEnteredTest {
    CARE game;
    
    public T4ChampionEnteredTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("Olenka");
    }
    
    @After
    public void tearDown() {
    }

    
    // TODO add test methods here.
    
     @Test
    public void oneChampionEnteredResult0() {
        int expected = 0;
        int actual= game.enterChampion("Flimsi");
        assertEquals("Champion should be entered successfully.", expected, actual);
    }
    
    @Test
    public void oneChampionEnteredTreasuryDeducted() {
        int expected = 800;
        game.enterChampion("Flimsi"); //don't store return
        int actual= game.getMoney();
        assertEquals("Treasury should be deducted by the entry fee of Flimsi.", expected, actual);
    }
    
    @Test
    public void oneChampionEnteredInTeam() {
        game.enterChampion("Flimsi"); 
        boolean actual= game.isInViziersTeam("Flimsi");
        assertTrue("Flimsi should be in the vizier's team after being entered.", actual);
    }
    
    @Test
    public void oneChampionNotInReserve() {
        game.enterChampion("Flimsi");
        boolean actual = game.isInReserve("Flimsi");
        assertFalse("Flimsi should not be in reserve after being entered.", actual);
    }
    
    @Test
    public void oneChampionNotEnteredTwice() {
        int expected = 1;
        game.enterChampion("Flimsi");
        int actual = game.enterChampion("Flimsi");
        assertEquals("Champion should not be entered twice.", expected, actual);
    }
    
    @Test
    public void notEnoughMoney() {
        int expected = 400;
        game.enterChampion("Flimsi");
        game.enterChampion("Ganfrank");
        game.enterChampion("Argon");
        assertEquals("Treasury should be less than Argon entrance fee",expected, game.getMoney());
    }
    
    @Test
    public void notEnoughMoneyResult2() {
        int expected = 2;
        game.enterChampion("Flimsi");
        game.enterChampion("Ganfrank");
        int actual = game.enterChampion("Argon");
    }

    @Test
    public void notEnoughMoneySoNotInTeam() {
        game.enterChampion("Flimsi");
        game.enterChampion("Ganfrank");
        game.enterChampion("Argon");
        boolean actual = !game.isInViziersTeam("Argon should not be in team, no enough meony");
        assertTrue(actual);
    }
    
    @Test
    public void notEnoughMoneySoStaysInReserve() {
        game.enterChampion("Flimsi");
        game.enterChampion("Ganfrank");
        game.enterChampion("Argon");
        boolean actual = (game.getReserve()).contains("Argon");
        assertTrue(actual);
    }
    
    @Test
    public void noSuchChampionEntered() {
        int expected = -1;
        int actual= game.enterChampion("Boggle");
        assertEquals(expected, actual);
    }
    @Test
    public void noSuchChampionEnteredNoDeduction() {
        int expected = 1000;
        game.enterChampion("Boggle");
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void notEnoughMoneySoStaysInreserve() {
        game.enterChampion("Flimsi");
        game.enterChampion("Ganfrank");
        game.enterChampion("Argon");
        boolean actual = (game.getReserve()).contains("Argon");
        assertTrue("Argon should remain in the reserved,", actual);
    }

    @Test
    public void notEnoughMoneySoStaysInreserve2() {
        int expected = 1000; // No money should be deducted
        game.enterChampion("Boggle");
        int actual = game.getMoney();
        assertEquals("No money should be deducted for a non-existent champion.", expected, actual);
    }
    
}
