package cwk4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the CARE game, allowing user to interact
 *
 * @author Saad
 * @version 20/04/24
 */
public class GameGUI
{
    private CARE gp = new Tournament("Fred");
    private JFrame myFrame = new JFrame("Game GUI"); // Main window frame
    private JTextArea listing = new JTextArea();         // Text area for displaying game information
    private JLabel codeLabel = new JLabel ();            // Label for potential future use
    // Buttons for user actions
    private JButton meetBtn = new JButton("Meet Challenge");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");

    // Panel to hold buttons
    private JPanel eastPanel = new JPanel();

    // Entry point for the GUI
    public static void main(String[] args)
    {
        new GameGUI();
    }

    // Constructor initializes the GUI
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }


    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.add(meetBtn);
        eastPanel.add(viewBtn);
        eastPanel.add(clearBtn);
        eastPanel.add(quitBtn);

        // Add action listeners to buttons
        clearBtn.addActionListener(new ClearBtnHandler());
        meetBtn.addActionListener(new MeetBtnHandler());
        quitBtn.addActionListener(new QuitBtnHandler());
        viewBtn.addActionListener(new ViewBtnHandler());

        // Set visibility for buttons
        meetBtn.setVisible(true);
        viewBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        // building is done - arrange the components and show
        myFrame.pack();
        myFrame.setVisible(true);
    }

    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the File menu
        // Champions menu for operations related to champions
        JMenu championMenu = new JMenu("Champions");
        menubar.add(championMenu);

        // Challenges menu for operations related to challenges
        JMenu challengeMenu = new JMenu("Challenges");
        menubar.add(challengeMenu);

        // Add menu items under champions menu and their handlers
        JMenuItem listChampionItem = new JMenuItem("List Champions in reserve");
        listChampionItem.addActionListener(new ListReserveHandler());
        championMenu.add(listChampionItem);

        JMenuItem listTeamItem = new JMenuItem("List the Team");
        listTeamItem.addActionListener(new ListTeamHandler());
        championMenu.add(listTeamItem);

        JMenuItem viewChampion = new JMenuItem("View a Champion");
        viewChampion.addActionListener(new ViewHandler());
        championMenu.add(viewChampion);

        JMenuItem EnterChampionItem = new JMenuItem("Enter a Champion");
        EnterChampionItem.addActionListener(new EnterChampHandler());
        championMenu.add(EnterChampionItem);

        // Add menu item under challenges menu and its handler
        JMenuItem listchallenges = new JMenuItem("List all Challenges");
        listchallenges.addActionListener(new ListChallengeHandler());
        challengeMenu.add(listchallenges);


    }

    // Handlers for menu items and buttons are defined below

    // Handler to list all champions in reserve
    private class ListReserveHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.getReserve();
            listing.setText(xx);
        }
    }

    // Handler to list all champions in the team
    private class ListTeamHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String mm = gp.getTeam();
            listing.setText(mm);
        }
    }

    // Handler to list all challenges in the game
    private class ListChallengeHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String tt = gp.getAllChallenges();
            listing.setText(tt);
        }
    }

    // Handler to clear the listing area
    private class ClearBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setText(" ");
        }
    }

    // Handler to initiate to meeting challenge
    private class MeetBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int result = -1;
            String answer = "no such challenge";
            String inputValue = JOptionPane.showInputDialog("Challenge number ?: ");
            int num = Integer.parseInt(inputValue);
            result = gp.meetChallenge(num);
            switch (result)
            {
                // Map challenge results to user-friendly messages
                case 0:answer = "challenge won by champion"; break;
                case 1:answer = "challenge lost on skills, champion disqualified";break;
                case 2:answer = "challenge lost as no suitable champion is available";break;
                case 3:answer = "challenge lost and vizier completely defeated";break;
            }

            JOptionPane.showMessageDialog(myFrame,answer);
        }
    }

    // Handler to confirm and process quitting the game
    private class QuitBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int answer = JOptionPane.showConfirmDialog(myFrame,
                    "Are you sure you want to quit?","Finish",
                    JOptionPane.YES_NO_OPTION);
            // closes the application
            if (answer == JOptionPane.YES_OPTION)
            {
                System.exit(0); //closes the application
            }
        }
    }

    // Handler to view the state of the game
    private class ViewBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String uu = gp.toString();
            listing.setText(uu);
        }
    }

    // Handler to view details of a specific champion
    private class ViewHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String userInput = JOptionPane.showInputDialog(myFrame, "Enter Champion's Name:");
            if (userInput != null) {
                JOptionPane.showMessageDialog(myFrame, userInput + gp.getChampionDetails(userInput));
            } else {
                JOptionPane.showMessageDialog(myFrame, "No Champion's name enter");
            }
        }
    }

    // Handler to enter a champion into the game
    private class EnterChampHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String userInput = JOptionPane.showInputDialog(myFrame, "Enter Champion's name:");
            if (userInput != null) {
                gp.isInReserve(userInput);
            } else {
                JOptionPane.showMessageDialog(myFrame, "No such Champion");
            }
        }
    }

}