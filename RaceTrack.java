import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author S. Thanush
 * Date: Nov.2023
 * Description: This class contains the frames that appear when the start button is clicked within the main menu.
 * 				It contains the frame of the race track where the characters run, and the frame of the results after 
 * 				the race has finished.
 * 
 * Method List: 
 * public class RaceTrack implements ActionListener - uses actionlistener
 * 
 * public RaceTrack(ImagePicture background, MarvelHero player11, MarvelHero player12, MarvelHero player21, 
 * 					MarvelHero player22, int laps, String team1Name, String team2Name) - Constructor that creates the Race Track frame where
 * 																						 each hero races each other
 * 
 * public void Move(MarvelHero p, MarvelHero p2, TextPicture stepsMoved,TextPicture roll, int playerRoll,
 * 					 String teamName) - Method that moves the character based of the sum of their dice rolls, and updates
 * 										displayable information accordingly. Skips turn if 4,6,24 is rolled.
 * 										Stops race when one player has reached the end and calls resultsFrame.
 * 
 * 	public void rollAndMove() - Method that rolls the dice, checks which team's turn it is, and calls move method
 * 								accordingly to the team. 
 * 
 * 	public void scores() - Calculates the points each player based of steps and number of wins using HighScore class and methods. 
 * 						   Stores that information into a string and than stores it into a readable txt file, to be used later.
 * 
 *  public void restart() - Resets the raceTrack frame, resulting in all characters to start from starting position, reseting all 
 *  						data related to each character to 0, and stopping the timer. 
 *  
 *  public RaceTrack(MarvelHero p1, MarvelHero p2, String tName) - Constructor that creates the frame where the winning team's information
 *  															   is displayed (CREATING FRAME WAS HAMZA, SETTING INFORMATION AND CREATING FUNCTIONALLITY WAS THANUSH)
 * 								
 *  public void actionPerformed(ActionEvent e) - Method that checks the action's recieved from buttons and timers, and uses if statements to perform
 *  											 neccessary procedures.
 *  
 *  public static void main(String[] args) - Self-Testing Main Method to make sure frames and actions created here works accoridngly
 * 
 */
public class RaceTrack implements ActionListener {

	/**
	 * Private Attributes/Instances
	 */
	private JPanel racePanel, resultsPanel;
	private JButton btnAuto,btnExit,btnStop,btnRoll, btnRestart, btnPlayAgain;
	private int width;
	private int height;
	private Timer timer;   // animation timer
	private int choice;
	private MarvelHero player11,player12,player21,player22;
	private Die d1,d2;
	private TextPicture player11Label,player12Label,player21Label,player22Label;
	private TextPicture player11Steps, player12Steps,player21Steps,player22Steps;
	private TextPicture player11Roll, player12Roll,player21Roll,player22Roll;
	private TextPicture teamName, turnLbl;
	private ImagePicture background,player11Image,player12Image,player21Image,player22Image, startLine, finishLine;
	private int player1Sum, player2Sum;
	private JFrame raceFrame, resultsFrame; 
	private int turn, totalLaps;
	private String results, data[], points[], wins[], team1,team2;
	
	// Is static as it is the constructor user uses to play game - Used in Self-Testing as well
	private static RaceTrack r;
	
	//Constructor that creates the Race Track frame where each hero races each other
	public RaceTrack(ImagePicture background, MarvelHero player11, MarvelHero player12, MarvelHero player21, MarvelHero player22, int laps, String team1Name, String team2Name) {
		
		//Initializes private variables and instances
		this.width = 1000;
		this.height = 650;
		this.turn = 1;
		this.totalLaps = laps;
		this.team1 = team1Name;
		this.team2 = team2Name;
		
		//Creates frame, sets size of frame, creates JPanel and adds it to the 
		//frame, sets layout of the panel to null (No Flow Layout)
		raceFrame = new JFrame("Universe Race!"); // New frame
		raceFrame.setSize(width,height);
		raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raceFrame.setLocationRelativeTo(null);
		raceFrame.setResizable(false);
		racePanel = new JPanel(); // New panel
		raceFrame.add(racePanel);
		racePanel.setLayout(null);
		
		//Creates buttons, sets it positions, and adds it to the frame.
		btnAuto = new JButton("Auto");
		btnAuto.setBounds(350,580,80,25);
		racePanel.add(btnAuto);
		btnExit = new JButton("Exit");
		btnExit.setBounds(620,580,80,25);
		racePanel.add(btnExit);
		btnStop = new JButton("Stop");
		btnStop.setBounds(350,580,80,25);
		racePanel.add(btnStop);
		btnStop.setVisible(false); //Set visibility false as it is only shown only if auto button is hit 
		btnRoll = new JButton("Roll");
		btnRoll.setBounds(440,580,80,25);
		racePanel.add(btnRoll);
		btnRestart = new JButton("Restart");
		btnRestart.setBounds(530,580,80,25);
		racePanel.add(btnRestart);
		
		//Initializes the MarvelHero objects, sets the x,y,width and height of the object, and adds
		//it to the Frame for all 4 characters.
		//Team 1 Player 1
		this.player11 = player11;
		this.player11.setxPos(0);
		this.player11.setyPos(0);
		this.player11.setBounds(50, 75, background.getMyWidth(), background.getMyHeight());
		racePanel.add(this.player11);
		// team 1 player 2  
		this.player12 = player12;
		this.player12.setxPos(0);
		this.player12.setyPos(0);
		this.player12.setBounds(50, 75, background.getMyWidth(), background.getMyHeight());
		racePanel.add(this.player12);
		// team 2 player 1 
		this.player21 = player21;
		this.player21.setxPos(0);
		this.player21.setyPos(0);
		this.player21.setBounds(50, 75, background.getMyWidth(), background.getMyHeight());
		racePanel.add(this.player21);
		// team 2 player 2 
		this.player22 = player22;
		this.player22.setxPos(0);
		this.player22.setyPos(0);
		this.player22.setBounds(50, 75, background.getMyWidth(), background.getMyHeight());
		racePanel.add(this.player22); 
		
		//Intializes and creates the images that correspond to each MarvelHero Object.
		///sets its positions and adds to frame.
		//team 1 player 1
		player11Image = new ImagePicture(this.player11.getImage(),135,0);
		player11Image.setBounds(0,0,width,height);
		racePanel.add(player11Image);
		//team 1 player 2
		player12Image = new ImagePicture(this.player12.getImage(),355,0);
		player12Image.setBounds(0,0,width,height);
		racePanel.add(player12Image);
		//team 2 player 1
		player21Image = new ImagePicture(this.player21.getImage(),565,0);
		player21Image.setBounds(0,0,width,height);
		racePanel.add(player21Image);
		//team 2 player 2
		player22Image = new ImagePicture(this.player22.getImage(),755,0);
		player22Image.setBounds(0,0,width,height);
		racePanel.add(player22Image);

		//Intializes and creates the names that correspond to each MarvelHero Object.
		///sets its positions and adds to frame.
		//team 1 player 1
		player11Label = new TextPicture(this.player11.getName(),new Font ("Times New Roman",Font.BOLD, 26));
		player11Label.setxPos(220);
		player11Label.setyPos(30);
		player11Label.setC(Color.white);
		player11Label.setBounds(0,0,width,height);
		racePanel.add(player11Label);
		//team 1 player 2
		player12Label = new TextPicture(this.player12.getName(),new Font ("Times New Roman",Font.BOLD, 26) );
		player12Label.setxPos(435);
		player12Label.setyPos(30);
		player12Label.setC(Color.white);
		player12Label.setBounds(0,0,width,height);
		racePanel.add(player12Label);
		//team 2 player 1
		player21Label = new TextPicture(this.player21.getName(),new Font ("Times New Roman",Font.BOLD, 26) );
		player21Label.setxPos(630);
		player21Label.setyPos(30);
		player21Label.setC(Color.white);
		player21Label.setBounds(0,0,width,height);
		racePanel.add(player21Label);
		//team 2 player 2
		player22Label = new TextPicture(this.player22.getName(),new Font ("Times New Roman",Font.BOLD, 26));
		player22Label.setxPos(830);
		player22Label.setyPos(30);
		player22Label.setC(Color.white);
		player22Label.setBounds(0,0,width,height);
		racePanel.add(player22Label);
		
		//Intializes and creates the stesp taken labels that correspond to each MarvelHero Object.
		///sets its positions and adds to frame.
		//team 1 player 1
		player11Steps = new TextPicture("Steps Moved: 0",new Font ("Times New Roman",Font.BOLD, 12) );
		player11Steps.setxPos(220);
		player11Steps.setyPos(50);
		player11Steps.setC(Color.white);
		player11Steps.setBounds(0,0,width,height);
		racePanel.add(player11Steps);
		//team 1 player 2
		player12Steps = new TextPicture("Steps Moved: 0",new Font ("Times New Roman",Font.BOLD, 12));
		player12Steps.setxPos(435);
		player12Steps.setyPos(50);
		player12Steps.setC(Color.white);
		player12Steps.setBounds(0,0,width,height);
		racePanel.add(player12Steps);
		//team 2 player 1
		player21Steps = new TextPicture("Steps Moved: 0",new Font ("Times New Roman",Font.BOLD, 12));
		player21Steps.setxPos(630);
		player21Steps.setyPos(50);
		player21Steps.setC(Color.white);
		player21Steps.setBounds(0,0,width,height);
		racePanel.add(player21Steps);
		//team 2 player 2
		player22Steps = new TextPicture("Steps Moved: 0",new Font ("Times New Roman",Font.BOLD, 12));
		player22Steps.setxPos(830);
		player22Steps.setyPos(50);
		player22Steps.setC(Color.white);
		player22Steps.setBounds(0,0,width,height);
		racePanel.add(player22Steps);

		
		//Intializes and creates the dice rolled labels that correspond to each MarvelHero Object.
		///sets its positions and adds to frame.
		//team 1 player 1
		player11Roll = new TextPicture("Roll: 0",new Font("Times New Roman",Font.BOLD, 12));
		player11Roll.setxPos(220);
		player11Roll.setyPos(70);
		player11Roll.setC(Color.white);
		player11Roll.setC(Color.white);
		player11Roll.setBounds(0,0,width,height);
		racePanel.add(player11Roll);
		//team 1 player 2
		player12Roll = new TextPicture("Roll: 0",new Font("Times New Roman",Font.BOLD, 12) );
		player12Roll.setxPos(435);
		player12Roll.setyPos(70);
		player12Roll.setC(Color.white);
		player12Roll.setBounds(0,0,width,height);
		racePanel.add(player12Roll);
		//team 2 player 1
		player21Roll = new TextPicture("Roll: 0", new Font("Times New Roman",Font.BOLD, 12));
		player21Roll.setxPos(630);
		player21Roll.setyPos(70);
		player21Roll.setC(Color.white);
		player21Roll.setBounds(0,0,width,height);
		racePanel.add(player21Roll);
		//team 2 player 2
		player22Roll = new TextPicture("Roll: 0",new Font("Times New Roman",Font.BOLD, 12));
		player22Roll.setxPos(830);
		player22Roll.setyPos(70);
		player22Roll.setC(Color.white);
		player22Roll.setBounds(0,0,width,height);
		racePanel.add(player22Roll);
		
		//Initializes and creates the label that shows which team's turn it is, sets the position
		//and adds it to the frame
		teamName = new TextPicture(this.team1,new Font("Times New Roman",Font.BOLD, 16));
		teamName.setxPos(10);
		teamName.setyPos(40);
		teamName.setC(Color.white);
		teamName.setBounds(0,0,width,height);
		racePanel.add(teamName);
		
		//initializes and creates the label that just displays the word "Turn", sets the position
		//and adds it to the frame
		turnLbl = new TextPicture("Turn:", new Font("Times New Roman",Font.BOLD, 20));
		turnLbl.setxPos(10);
		turnLbl.setyPos(20);
		turnLbl.setC(Color.white);
		turnLbl.setBounds(0,0,width,height);
		racePanel.add(turnLbl);
		
		//initializes and creates the image that shows the start line of the race, sets the position
		//and adds it to the frame
		startLine = new ImagePicture(new ImageIcon("finishline.jpg"));
		startLine.setBounds(140,75,20,75);
		racePanel.add(startLine);
		
		//initializes and creates the iamge that shows the finish line of the race, sets the position
		//and adds it to the frame
		finishLine = new ImagePicture(new ImageIcon("finishLine.jpg"));
		finishLine.setBounds(50,150,110,20);
		racePanel.add(finishLine);

		// create the dice for them to roll
		d1 = new Die(12);  
		d2 = new Die(12);

		timer = new Timer (75, this);  //creates a timer and this class as the listener. set to fire every 40ms
		timer.setInitialDelay (10); //set the initial delay to 10 ms before it starts
		
		//Makes it where an action is returned when the buttons are hit
		timer.addActionListener(this);
		btnStop.addActionListener(this);
		btnAuto.addActionListener(this);
		btnExit.addActionListener(this);	
		btnRoll.addActionListener(this);
		btnRestart.addActionListener(this);
		
		//Sets the visibility of the frame to true 
		raceFrame.setVisible(true);

		// Initialize steps to 0
		this.player11.setStepsLeft(0);
		this.player12.setStepsLeft(0);
		this.player21.setStepsLeft(0);
		this.player22.setStepsLeft(0);
		// Initialize steps moved to 0
		this.player11.setStepsTaken(0);
		this.player12.setStepsTaken(0);
		this.player21.setStepsTaken(0);
		this.player22.setStepsTaken(0);

		
		//Initializes and creates the background of the race,sets the size and position, and adds
		//it to the frame
		this.background = background;
		this.background.setBounds(50,75,background.getMyWidth(),background.getMyHeight());
		racePanel.add(this.background);
		//Sets rest of the background of the frame that is not filled with background image to black
		racePanel.setBackground(Color.black);
	}

	//Method that moves the character based of the sum of their dice rolls, and updates	displayable information accordingly. 
	//Skips turn if 4,6,24 is rolled. Stops race when one player has reached the end and calls resultsFrame.
	public void Move(MarvelHero p, MarvelHero p2, TextPicture stepsMoved,TextPicture roll, int playerRoll, String teamName) { 
		
		//Checks if player has rolled 4, 6, or 24. If true, player turn is skipped by setting steps to 0 and
		//updates displayed information
		if (playerRoll == 4 ||playerRoll == 6 || playerRoll == 24 ) { 
			p.setStepsLeft(0);
			roll.setTitleText("Roll: " + String.valueOf(playerRoll) + " (TURN SKIPPED)");
		}
		
		//If fals, updates displayable information, and goes through a while loop that will move the
		//player image, check player image position and set its velocities accordingly. While loop continuously goes
		//until steps is 0. If player reaches finish line and finishes all laps, it will add 1 to their current wins,
		//set their team wins to true, set visiblilty of the race track to false, call the scores method to calculate all 
		//player points, and calls the results frame to display the statistics of the winning team. 
		else {
			roll.setTitleText("Roll: " + String.valueOf(playerRoll));
			while (p.getStepsLeft() > 0) { //goes until steps reaches 0
				p.move();
				stepsMoved.setTitleText("Steps Moved: " + String.valueOf(p.getStepsTaken()));
				//Checks first player position and changes velocity accordingly
				if ((p.getxPos() == (this.background.getMyWidth() - p.getMyWidth()))) {
					//If position of player image is bottom right 
					if (p.getyPos() == (this.background.getMyHeight() - p.getMyHeight())) { 
						//makes image go left
						p.setyVel(0);
						p.setxVel(-1);
					}
					//if position of player image is top right 
					else if (p.getyPos() == 0) { 
						//makes image go down
						p.setyVel(1);
						p.setxVel(0);
					}
				}
				else if (p.getxPos() == 0){
					//if position  of player is bottom left
					if (p.getyPos() == (this.background.getMyHeight() - p.getMyHeight())) { 
						//makes image go back up
						p.setyVel(-1);
						p.setxVel(0);
					}	
					//if position is at the start/finish line
					else if (p.getyPos() == 0) { 
						//adds 1 to their current lao
						p.setLaps(p.getLaps()+1);
						
						//If laps player object has down is less or equal to total laps,
						//makes player image continue race and go right.
						if (p.getLaps() <= this.totalLaps) { 
							p.setyVel(0);
							p.setxVel(1);
						}
						//If player finishes, stops timer, it will add 1 to their current wins,
						//set their team wins to true, set visiblilty of the race track to false, call the scores method to calculate all 
						//player points, and calls the results frame to display the statistics of the winning team. 
						else { 
							timer.stop();
							p.setWins(p.getWins()+1);
							p2.setWins(p2.getWins()+1);
							p.setTeamWin(true);
							p2.setTeamWin(true);
							raceFrame.setVisible(false);
							scores();
							new RaceTrack(p,p2,teamName);
						}
					}
				}
				//repaints frame
				raceFrame.repaint();
			}
		}
	}
	
	
	//Method that rolls the dice, checks which team's turn it is, and calls move method accordingly to the team. 
	public void rollAndMove() { 
		//Rolls dice and initializes their sum to player1Sum and player2Sum
		d1.rollDie();
		d2.rollDie();
		player1Sum = (d1.getValue()+d2.getValue());
		d1.rollDie();
		d2.rollDie();
		player2Sum = (d1.getValue()+d2.getValue());
		
		// If Statement to Check player turn
		if(turn == 1) { 
			//Sets the steps of players to rolled dice value sums
			player11.setStepsLeft(player1Sum);
			player12.setStepsLeft(player2Sum);
			//Calls move method to move characters 
			Move(this.player11, this.player12,this.player11Steps, this.player11Roll, this.player1Sum, this.team1); 
			Move(this.player12, this.player11, this.player12Steps, this.player12Roll, this.player2Sum, this.team1);
			// Change turn
			turn = 2;
			//update which teams turn it is
			this.teamName.setTitleText(this.team2);
			this.teamName.repaint();
		}
		else {
			//Sets the steps of players to rolled dice value sums
			player21.setStepsLeft(player1Sum);
			player22.setStepsLeft(player2Sum);
			//Calls move method to move characters 
			Move(this.player21, this.player22, this.player21Steps, this.player21Roll, this.player1Sum, this.team2); 
			Move(this.player22, this.player21,this.player22Steps, this.player22Roll, this.player2Sum, this.team2);
			// Change turn
			turn = 1;
			//update which teams turn it is
			this.teamName.setTitleText(this.team1);
			this.teamName.repaint();


		}
		raceFrame.repaint(); // Repaint frame

	}
	//Calculates the points each player based of steps and number of wins using HighScore class and methods. 	   
	//Stores that information into a string and than stores it into a readable txt file, to be used later.
	public void scores() { 
		//Sets points of all 4 players by adding points returned from calculatePoints method from HighScore class to current points
		this.player11.setPoints(this.player11.getPoints()+HighScore.calculatePoints(this.player11.getStepsTaken(),this.player11.getWins(),this.player11.isTeamWin()));
		this.player12.setPoints(this.player12.getPoints()+HighScore.calculatePoints(this.player12.getStepsTaken(),this.player12.getWins(),this.player12.isTeamWin()));
		this.player21.setPoints(this.player21.getPoints()+HighScore.calculatePoints(this.player21.getStepsTaken(),this.player21.getWins(),this.player21.isTeamWin()));
		this.player22.setPoints(this.player22.getPoints()+HighScore.calculatePoints(this.player22.getStepsTaken(),this.player22.getWins(),this.player22.isTeamWin()));
		
		//Resets results variable, adds information to string variable so it can be put into a readable txt file.
		this.results = "";
		this.results += this.player11.getPoints() + "," + this.player12.getPoints() + "," + this.player21.getPoints() + "," + this.player22.getPoints() + "\n";
		this.results += this.player11.getWins() + "," + this.player12.getWins() + "," + this.player21.getWins() + "," + this.player22.getWins();
		HighScore.newFile(results);
	}

	//Resets the raceTrack frame, resulting in all characters to start from starting position, reseting all 
	//data related to each character to 0, and stopping the timer. 
	public void restart() {
		this.player11.setxPos(0);
		this.player11.setyPos(0);
		this.player12.setxPos(0);
		this.player12.setyPos(0);
		this.player21.setxPos(0);
		this.player21.setyPos(0);
		this.player22.setxPos(0);
		this.player22.setyPos(0);
		this.player11.setxVel(0);
		this.player11.setyVel(0);
		this.player12.setxVel(0);
		this.player12.setyVel(0);
		this.player21.setxVel(0);
		this.player21.setyVel(0);
		this.player22.setxVel(0);
		this.player22.setyVel(0);
		teamName.setTitleText(this.team1);
		teamName.repaint();
		turn = 1;
		this.player11.setPoints(0);
		this.player12.setPoints(0);
		this.player21.setPoints(0);
		this.player22.setPoints(0);
		this.player11.setWins(0);
		this.player12.setWins(0);
		this.player21.setWins(0);
		this.player22.setWins(0);
		this.player11.setTeamWin(false);
		this.player12.setTeamWin(false);
		this.player21.setTeamWin(false);
		this.player22.setTeamWin(false);
		this.player11.setLaps(0);
		this.player12.setLaps(0);
		this.player21.setLaps(0);
		this.player22.setLaps(0);
		this.player11.setStepsLeft(0);
		this.player12.setStepsLeft(0);
		this.player21.setStepsLeft(0);
		this.player22.setStepsLeft(0);
		this.player11.setStepsTaken(0);
		this.player12.setStepsTaken(0);
		this.player21.setStepsTaken(0);
		this.player22.setStepsTaken(0);
		this.player11Roll.setTitleText("Roll: 0");
		this.player12Roll.setTitleText("Roll: 0");
		this.player21Roll.setTitleText("Roll: 0");
		this.player22Roll.setTitleText("Roll: 0");
		this.player11Steps.setTitleText("Steps Moved: " + String.valueOf(this.player11.getStepsTaken()));
		this.player12Steps.setTitleText("Steps Moved: " + String.valueOf(this.player12.getStepsTaken()));
		this.player21Steps.setTitleText("Steps Moved: " + String.valueOf(this.player21.getStepsTaken()));
		this.player22Steps.setTitleText("Steps Moved: " + String.valueOf(this.player22.getStepsTaken()));
		this.btnAuto.setVisible(true);
		this.btnStop.setVisible(false);
		raceFrame.repaint();
	}

	 //Constructor that creates the frame where the winning team's information is displayed.
	public RaceTrack(MarvelHero p1, MarvelHero p2, String tName) { 
		// Gets RaceTrack object from UniverseGUI class
		if(r == null) {
			r = UniverseGUI.r;
		}
		
		//Creates frame, sets size of frame, creates JPanel and adds it to the 
		//frame, sets layout of the panel to null (No Flow Layout)
		resultsFrame = new JFrame("Results!");
		resultsFrame.setSize(800,500);
		resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultsFrame.setLocationRelativeTo(null);
		resultsFrame.setResizable(false);
		resultsPanel = new JPanel();
		resultsFrame.add(resultsPanel);
		resultsPanel.setLayout(null);


		//Creates image of trophy, sets position, adds to frame
		ImagePicture trophyImage = new ImagePicture(new ImageIcon("trophyRS.png"));
		trophyImage.setyPos(200);
		trophyImage.setxPos(325);
		trophyImage.setBounds(0, 0, 900, 600);
		resultsPanel.add(trophyImage);

		//Creates image of winner logo, sets position, adds to frame
		ImagePicture winnerLogo = new ImagePicture(new ImageIcon("WinnerRS.png"));
		winnerLogo.setyPos(10);
		winnerLogo.setxPos(290);
		winnerLogo.setBounds(0, 0, 900, 600);
		resultsPanel.add(winnerLogo); 


		//Creates winning team name to be displayed, sets position, adds to frame
		TextPicture winningTeamName = new TextPicture(tName, new Font("Arial", Font.BOLD, 20));
		winningTeamName.setyPos(150);
		winningTeamName.setxPos(300);
		winningTeamName.setBounds(0, 0, 900, 600);
		winningTeamName.setC(Color.RED);
		resultsPanel.add(winningTeamName); 
		
		//creates wins to be displayed, sets position, adds to frame
		TextPicture wins = new TextPicture("Wins: " + String.valueOf(p1.getWins()), new Font("Arial", Font.BOLD, 20));
		wins.setyPos(400);
		wins.setxPos(350);
		wins.setBounds(0, 0, 900, 600);
		wins.setC(Color.RED);
		resultsPanel.add(wins); 

		//creates player text to be displayed, sets position, adds to frame
		TextPicture character1Below = new TextPicture("Player 1 Below:", new Font("Arial", Font.BOLD, 15));
		character1Below.setyPos(150);
		character1Below.setxPos(50);
		character1Below.setBounds(0, 0, 900, 600);
		character1Below.setC(Color.RED);
		resultsPanel.add(character1Below); 

		//creates player text to be displayed, sets position, adds to frame
		TextPicture character2Below = new TextPicture("Player 2 Below:", new Font("Arial", Font.BOLD, 15));
		character2Below.setyPos(145);
		character2Below.setxPos(580);
		character2Below.setBounds(0, 0, 900, 600);
		character2Below.setC(Color.RED);
		resultsPanel.add(character2Below); 

		//creates image of winning player 1, sets position, adds to frame
		ImagePicture character1Image = new ImagePicture(p1.getImage());
		character1Image.setyPos(165);
		character1Image.setxPos(75);
		character1Image.setBounds(0, 0, 900, 600);
		resultsPanel.add(character1Image); 

		//creates image of winning player 2, sets position, adds to frame
		ImagePicture character2Image = new ImagePicture(p2.getImage());
		character2Image.setyPos(165);
		character2Image.setxPos(600);
		character2Image.setBounds(0, 0, 900, 600);
		resultsPanel.add(character2Image); 
		
		//creates name for winning player 1, sets position, adds to frame
		TextPicture character1Name = new TextPicture("Player Name: " + p1.getName(), new Font("Arial", Font.BOLD, 14));
		character1Name.setyPos(350);
		character1Name.setxPos(60);
		character1Name.setBounds(0, 0, 900, 600);
		character1Name.setC(Color.RED);
		resultsPanel.add(character1Name); 

		//creates name for winning player 2, sets position, adds to frame
		TextPicture character2Name = new TextPicture("Player Name: " + p2.getName(), new Font("Arial", Font.BOLD, 14));
		character2Name.setyPos(350);
		character2Name.setxPos(595);
		character2Name.setBounds(0, 0, 900, 600);
		character2Name.setC(Color.RED);
		resultsPanel.add(character2Name); 

		//creates points for winning player 1, sets position, adds to frame
		TextPicture points1Text = new TextPicture("Points: " + String.valueOf(p1.getPoints()), new Font("Arial", Font.BOLD, 14));
		points1Text.setyPos(300);
		points1Text.setxPos(60);
		points1Text.setBounds(0, 0, 900, 600);
		points1Text.setC(Color.RED);
		resultsPanel.add(points1Text); 

		//creates points for winning player 2, sets position, adds to frame
		TextPicture points2Text = new TextPicture("Points: " + String.valueOf(p2.getPoints()), new Font("Arial", Font.BOLD, 14));
		points2Text.setyPos(300);
		points2Text.setxPos(595);
		points2Text.setBounds(0, 0, 900, 600);
		points2Text.setC(Color.RED);
		resultsPanel.add(points2Text); // Add the background to the bottom layer

		//Creates buttons, sets it positions, and adds it to the frame.
		btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.setBounds(275,425,100,30);
		btnExit = new JButton("Exit");
		btnExit.setBounds(375,425,100,30);
		resultsPanel.add(btnPlayAgain);
		resultsPanel.add(btnExit);
		btnPlayAgain.addActionListener(this);
		btnExit.addActionListener(this);

		//Creastes background,sets its size and position, adds to frame
		ImagePicture rulesBackground = new ImagePicture(new ImageIcon("yellowback.jpg"));
		rulesBackground.setBounds(0, 0, 800, 500);
		resultsPanel.add(rulesBackground); // Add the background to the bottom layer

		//sets visibility of frame to true
		resultsFrame.setVisible(true);
	}

	//Method that checks the action's recieved from buttons and timers, and uses if statements to perform
	//neccessary procedures.
	public void actionPerformed(ActionEvent e) {
		
		//If roll button is hit, the according team is moved based of their roll by
		//calling rollAndMove method.
		if (e.getSource() == btnRoll) { 
			rollAndMove();
		}
		
		//if auto button is hit, timer will start, auto butotn is not visible,
		//and stop button will be visible.
		if (e.getSource() == btnAuto) { 
			timer.start();
			btnAuto.setVisible(false);
			btnStop.setVisible(true);
		}
		
		//If timer has started, it will do what btnRoll procedure is meant to do
		//but at every given interval the timer gives, so automatically in short.
		if (e.getSource() == timer) {
			rollAndMove();
		}
		
		//If stop is hit, timer will stop, auto button is visible, and stop button
		//it not visible.
		if (e.getSource() == btnStop) { 
			timer.stop();
			btnAuto.setVisible(true);
			btnStop.setVisible(false);
		}
		
		//If restart is hit, timer will stop, auto button is visible, stop button is not visible,
		//and user will be given a warning message to make sure they know what will happen if restart
		//is hit.
		if(e.getSource() == btnRestart) { 
			timer.stop();
			btnAuto.setVisible(true);
			btnStop.setVisible(false);
			choice = JOptionPane.showOptionDialog(null, "Are you sure you want to restart? \n(RESTARTING WILL RESET ALL YOUR PREVIOUS DATA)", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
			if (choice == 0) { 
				restart();
			}
		}
		
		//If exit button is hit, timer will stop for raceTrack frame only, auto button is visible for raceTrack frame only,
		//stop button is not visible for raceTrack frame only, and user will be given a warning message asking are they sure
		//they want to exit
		if (e.getSource() == btnExit) { 
			r.timer.stop();
			r.btnAuto.setVisible(true);
			r.btnStop.setVisible(false);
			choice = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);

			if (choice == 0) { 
				System.exit(0);
			}
		}
		
		//If play again is hit, it will read the data from the RaceResults.txt file, restart the raceTrack frame, put it into array,
		//set it to all 4 players wins and points. It will set the visibility of the results frame to false, and raceFrame to true.
		if (e.getSource() == btnPlayAgain)  { 
			//try and catch to prevent errors
			try {
				data = HighScore.loadFile("RaceResults.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//uses split to seperate data and put into each according index of aray.
			String info = null; 
			info = this.data[0];
			points = info.split(",");
			info = this.data[1];
			wins = info.split(",");
			//For programmer to check if correct data is being displayed
			//for (int i = 0; i < points.length; i++) { 
			//	System.out.println("Points: " + points[i] 
			//			+ " \nWins: " + wins[i]);
			//}
			//Recalls raceFrame and updates information
			resultsFrame.setVisible(false);	
			r.raceFrame.setVisible(true);
			r.restart();
			r.player11.setPoints(Integer.valueOf(points[0]));
			r.player12.setPoints(Integer.valueOf(points[1]));
			r.player21.setPoints(Integer.valueOf(points[2]));			
			r.player22.setPoints(Integer.valueOf(points[3]));
			r.player11.setWins(Integer.valueOf(wins[0]));
			r.player12.setWins(Integer.valueOf(wins[1]));
			r.player21.setWins(Integer.valueOf(wins[2]));
			r.player22.setWins(Integer.valueOf(wins[3]));
		}
	}

	/**
	 * Self-Testing Main Method
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Creates marvelHero objects for testing
		MarvelHero p1 = new MarvelHero(new ImageIcon("HawkEyeRS.png"));
		p1.setName("Thanush");
		MarvelHero p2 = new MarvelHero(new ImageIcon("FalconRS.png"));
		p2.setName("Rudra");
		MarvelHero p3 = new MarvelHero(new ImageIcon("CapMarvelRS.png"));
		p3.setName("Hamza");
		MarvelHero p4 = new MarvelHero(new ImageIcon("Iron_ManRS.png"));
		p4.setName("Bob");
		
		//calls marvelHero constructor to be run for testing.
		r = new RaceTrack(new ImagePicture(new ImageIcon("marvelSpace.png")), p1,p2,p3,p4,1, "Thanush's Team" ,"Hamza's Team");
	}


}
