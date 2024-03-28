/**
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Authour; Hamza Khan 
 * Date; November, 12, 2023
 * Description; When button clicked in the menu JFrame the rules jframe will pop up displaying menu help and rules
 * 
 * METHODS LIST;
 * 
 * public class RulesFrame extends JFrame implements ActionListener{ //extends JFrame and uses ActionListener
 * 
 * 
 * public RulesFrame() { //method to display the Frame
 * 
 * 
 * 
 * 
 * public static void main(String[] args) { //main method 
 * 
 * 
 * 
 * NEW SKILLS AND PROCESSES LEARNED;
 * 
 * .dispose();
 * 
 * Why Used?
 * 
 * To close the just the frame without closing the program 
 * as before using sytem.exit(0); would close the entire 
 * program but this just disposes the frame
 * 
 * LINK: https://stackoverflow.com/questions/13360430/jframe-dispose-vs-system-exit
 * 
 */
public class RulesFrame extends JFrame implements ActionListener{

	//variables 
	private ImagePicture rulesBackground;
	private JButton btnMenu;
	private JPanel controlPanel;
	private TextPicture gameHelpText, menuHelpText;

	//string array to display the game rules 
	private String[] gameRules = {
			"Each player will get to choose a character.",
			"Each team has 2 players.",
			"The goal of the game is to reach the finish line first!",
			"Either player from the team can reach the finish line to win the game.",
			"The points will be added by the amount of steps moved by each player." ,
			"The steps will be multiplied by the player win to the power of 5.",
			"If the player won the current lap, then the points will be multiplied by an additional 10.",
			"If a player loses,  the points will be just the amount of points achieved without the win bonus.",
			"The winner will be determined by the most points by the end of the game."

	};

	//string array to display the menu help text 
	private String[] menuHelp = {
			"JTextFields --> To Choose Names for the Team and The Teams Characters.",
			"Select Character Pull Down --> Allows You Choose the Characters that will Run Race.",
			"Select Background Pull Down --> To Choose the Background that the Race will Occur On.",
			"Select Laps / Select Track --> To Number of Laps Raced and Type of Track/Circuit to Race On.",
			"Music On/Off --> Buttons Help Turn Music On and Off.",
			"High Scores --> Displays the Latest and Fastest Time the Race is Completed.",
			"Start Race --> After All Options Choosen You Can Finally Start the Race!!!.",
	};



	public RulesFrame() {
		super("Rules and Help Page");


		// Create a layered pane
		JLayeredPane rulesLayeredPane = getLayeredPane();

		// Display the background image of the menu 
		rulesBackground = new ImagePicture(new ImageIcon("greyWallpaperRS.png"));
		rulesBackground.setBounds(0, 0, 600, 450);
		rulesLayeredPane.add(rulesBackground, Integer.valueOf(0)); // Add the background to the bottom layer

		//Text that will create a header for the menu help
		menuHelpText = new TextPicture("Menu Help?", new Font("MonoSpaced", Font.BOLD, 20));
		menuHelpText.setyPos(45);
		menuHelpText.setxPos(20);
		menuHelpText.setBounds(0, 0, 900, 600);
		menuHelpText.setFont(new Font("MonoSpaced", Font.BOLD, 20));
		menuHelpText.setC(Color.GREEN);
		rulesLayeredPane.add(menuHelpText, Integer.valueOf(1)); // Add the background to the bottom layer

		//Text that will create a header for the game rules  
		gameHelpText = new TextPicture("Game Rules!", new Font("MonoSpaced", Font.BOLD, 20));
		gameHelpText.setyPos(195);
		gameHelpText.setxPos(20);
		gameHelpText.setBounds(0, 0, 900, 600);
		gameHelpText.setC(Color.RED);
		rulesLayeredPane.add(gameHelpText, Integer.valueOf(1)); // Add the background to the bottom layer





		/*
		 * NOTE; Since the text needed to be displayed is a textpicture, it is not able to be split up as needed
		   to avoid this issue an array was created to display the array at different postions in order to 
	       display the text
		 */


		//displaying the text for the Menu Help 

		int startingYposMenu = 70; // start displaying the text at y postion of 70 

		for (int i = 0; i < menuHelp.length; i++) { //for loop to go through the array of menu help text 
			String line = menuHelp[i]; //assign String line text value of the whatever is in the array 

			//display the text using a text picture with the font and size of TimesRoman and 11 
			TextPicture lineText = new TextPicture(line, new Font("TimesRoman", Font.BOLD, 11));

			//set y starting point of text at int startingYposMenu = 70
			lineText.setyPos(startingYposMenu);

			//set x starting point of text at 15
			lineText.setxPos(15);

			//set bounds 
			lineText.setBounds(0, 0, 900, 600);

			//set text colour to black
			lineText.setC(Color.BLACK);

			//add text one layer above background 
			rulesLayeredPane.add(lineText, Integer.valueOf(1));

			// Adjust the vertical position for the next line by adding 15 to y pos 
			startingYposMenu = startingYposMenu + 15;
		}		


		//displaying the text for the game rules 

		int startingYposGame = 220; // start displaying the text at y postion of 70 


		for (int i = 0; i < gameRules.length; i++) { //for loop to go through the array of game rules text 
			String line = gameRules[i]; //assign String line text value of the whatever is in the array

			//display the text using a text picture with the font and size of TimesRoman and 11 
			TextPicture lineText = new TextPicture(line, new Font("TimesRoman", Font.BOLD, 11));

			//set y starting point of text at int startingYposMGame = 220 
			lineText.setyPos(startingYposGame);

			//set x starting point of text at 15
			lineText.setxPos(15);

			//set bounds 
			lineText.setBounds(0, 0, 900, 600);

			//set text colour to black
			lineText.setC(Color.BLACK);

			//add text one layer above background 
			rulesLayeredPane.add(lineText, Integer.valueOf(1));


			// Adjust the vertical position for the next line by adding 15 to y pos 
			startingYposGame = startingYposGame + 15;
		}


		//create button to go back to the menu screen
		btnMenu = new JButton("Back to Menu");

		//actionlistener for the button
		btnMenu.addActionListener(this);

		//create new panel for the button
		controlPanel = new JPanel();
		controlPanel.setOpaque(false); // Make the control panel transparent
		controlPanel.add(btnMenu); //add button
		controlPanel.setBounds(0, 375, 600, 450); //bounds
		rulesLayeredPane.add(controlPanel, Integer.valueOf(1)); // add above background 


		setSize(600, 450); // set size
		setLocation(100, 100); //set location 
		setVisible(true); //set true
		setResizable(false); //set false 

	}
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == btnMenu) { //when btnMenu clicked 
			//close the frame
			this.dispose();
		} 

	}









	/**
	 * @param args
	 */

	//Main Method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RulesFrame rules = new RulesFrame();


	}

}
