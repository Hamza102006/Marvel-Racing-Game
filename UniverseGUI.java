import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;



/**
 * Authour; Hamza Khan 
 * Date; Novemeber 11, 2023
 * Description; The Main Menu GUI Which is the class which will inherit and use other class
 *              to make the user experience as friendly as possible. The Menu class will have 
 *              many buttons, drop downs which will help the user play the game, without any confusion
 *              
 *              
 * METHODS LIST;
 * 
 * public class UniverseGUI extends JFrame implements ActionListener { //extends JFrame and uses Actionlistener
 * 
 * 
 * public UniverseGUI() { //method for the actual GUI to be displayed
 * 
 * 
 * private void loadBackgroundMusic(String song) { //method to run the music in the background 
 * 
 * 
 * public void actionPerformed(ActionEvent e) { //action performer which listens to buttons, comboboxes, etc
 * 
 * 
 * public static void main(String[] args) { //calls and runs the GUI
 * 
 * 
 * 
 * 
 * NEW SKILLS AND PROCESSES LEARNED;
 * 
 * [JComboBoxes]
 * 
 * Why Used? 
 * 
 * Instead of Making the User self enter text for the wanted character 
 * or track I thought the most user friendly way to ask the user for
 * these choices were a Drop down method or aka a JComoboBox
 * 
 * LINKS; https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
 *        https://stackoverflow.com/questions/17887927/adding-items-to-a-jcombobox
 * 
 * 
 * 
 * 
 * [JLayeredPane] 
 * 
 * Why Used?
 * 
 * When displaying the components and images into my frame I came across
 * the issue of displaying the contents as some would get covered 
 * to overcome this issue I learned JLayered to layer my components on the frame
 * 
 * LINKS; https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html
 * 		  https://www.javatpoint.com/java-jlayeredpane#:~:text=The%20JLayeredPane%20class%20is%20used,range%20into%20several%20different%20layers.
 * 
 * 
 * 
 * 
 * Many Music Functions Used Within Code Explanation Provided Beside all Related Code
 * 
 * Why Used?
 * 
 * Make more enjoyable GUI
 * 
 * LINKS; https://stackoverflow.com/questions/16867976/how-do-you-add-music-to-a-jframe
 *        https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
 * 	      https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
 * 		  https://www.youtube.com/watch?v=wJO_cq5XeSA&t=14s (YOUTUBE VIDEO)
 *        https://www.youtube.com/watch?v=3q4f6I5zi2w (YOUTUBE VIDEO)
 */
public class UniverseGUI extends JFrame implements ActionListener {

	//variables 
	private ImagePicture menuBackground, rulesBackground, raceTrackBackround,sendBackground;
	private MarvelHero Image1,Image2,Image3,Image4;
	private JPanel leftPanel, middlePanel, rightPanel, controlPanel;
	private JButton btnStart, btnExit, btnRules, btnStopMusic, btnPlayMusic;
	private JTextField textInput, textInput2, team1Character1Name, team1Character2Name, team2Character1Name, team2Character2Name;
	private TextPicture titleText, bgText, characterText, team1Player1, team1Player2, team2Player1, team2Player2, lapsText,characterName1Team1,characterName2Team1,characterName1Team2,characterName2Team2;
	private JComboBox cbOption, cbOption1, cbOption2, cbOption3, cbOptionRaceTrack, cbOptionLaps;
	private int numberOfLaps = 0;
	private JLayeredPane layeredPane;

	//String content for the pull down methods 
	String [] teamSpiderManeoption = {"Spider-Man", "Iron Man", "Hulk", "CAP"};
	String [] teamCaptainMarveloption = {"CapMarvel", "Nick Fury", "HawkEye", "Falcon"};
	String [] tracks = {"HeadQuarters", "Space", "New-York"};
	String [] laps = {"1", "2", "3", "4" , "5"}; 


	//Represents a Clip object for playing background music.
	//The Clip class is part of the Java Sound API and is used for playing short audio clips.
	private Clip backgroundMusicClip;

	public static RaceTrack r;//Instance of RaceTrack, should be accessible by MarvelRace so frames can work 
						      //and be displayed accordingly.
	
	public UniverseGUI() {
		super("Racing Games");

		// Load and start playing the background music.
		loadBackgroundMusic("BackgroundMusic.wav");

		// Start playing the background music Clip.
		backgroundMusicClip.start();


		// Create a layered pane
		layeredPane = getLayeredPane();


		// Display the background image of the menu 
		menuBackground = new ImagePicture(new ImageIcon("LightBlueRS.png"));
		menuBackground.setBounds(0, 0, 900, 600);
		layeredPane.add(menuBackground, Integer.valueOf(0)); // Add the background to the bottom layer

		//image of background for the race track being displayed for user to choose which one they prefer 
		raceTrackBackround  = new ImagePicture(new ImageIcon("HQMARVELRS.png"));
		raceTrackBackround.setyPos(140);
		raceTrackBackround.setxPos(360);
		raceTrackBackround.setBounds(0, 0, 900, 600);
		layeredPane.add(raceTrackBackround, Integer.valueOf(1)); // Add to the layer 1

		//image of Character 1 for Team 1 for the race being displayed for user to choose which one they prefer 
		Image1 = new MarvelHero(new ImageIcon("spider_manRS.png"));
		Image1.setyPos(300);
		Image1.setxPos(50);
		Image1.setBounds(0, 0, 900, 500);
		layeredPane.add(Image1, Integer.valueOf(1)); // Add to the layer 1

		//image of Character 2 for Team 1 for the race being displayed for user to choose which one they prefer 
		Image2 = new MarvelHero(new ImageIcon("spider_manRS.png"));
		Image2.setyPos(300);
		Image2.setxPos(185);
		Image2.setBounds(0, 0, 900, 500);
		layeredPane.add(Image2, Integer.valueOf(1)); // Add to the layer 1

		//image of Character 1 for Team 2 for the race being displayed for user to choose which one they prefer 
		Image3 = new MarvelHero(new ImageIcon("CapMarvelRS.png"));
		Image3.setyPos(300);
		Image3.setxPos(665);
		Image3.setBounds(0, 0, 900, 500);
		layeredPane.add(Image3, Integer.valueOf(1)); // Add to the layer 1

		//image of Character 2 for Team 2 for the race being displayed for user to choose which one they prefer 
		Image4 = new MarvelHero(new ImageIcon("CapMarvelRS.png"));
		Image4.setyPos(300);
		Image4.setxPos(795);
		Image4.setBounds(0, 0, 900, 500);
		layeredPane.add(Image4, Integer.valueOf(1)); // Add to the layer 1

		//text telling user to choose character 1 for team 1
		team1Player1 = new TextPicture("Select Character 1", new Font("MonoSpaced", Font.BOLD, 10));
		team1Player1.setyPos(200);
		team1Player1.setxPos(40);
		team1Player1.setBounds(0, 0, 900, 600);
		team1Player1.setC(Color.PINK);
		layeredPane.add(team1Player1, Integer.valueOf(1)); // Add to the layer 1

		//text telling user to choose character 2 for team 1
		team1Player2 = new TextPicture("Select Character 2", new Font("MonoSpaced", Font.BOLD, 10));
		team1Player2.setyPos(200);
		team1Player2.setxPos(165);
		team1Player2.setBounds(0, 0, 900, 600);
		team1Player2.setC(Color.PINK);
		layeredPane.add(team1Player2, Integer.valueOf(1)); // Add to the layer 1

		//text telling user to choose character 1 for team 2
		team2Player1 = new TextPicture("Select Character 1", new Font("MonoSpaced", Font.BOLD, 10));
		team2Player1.setyPos(200);
		team2Player1.setxPos(640);
		team2Player1.setBounds(0, 0, 900, 600);
		team2Player1.setC(Color.PINK);
		layeredPane.add(team2Player1, Integer.valueOf(1)); // Add to the layer 1

		//text telling user to choose character 2 for team 2
		team2Player2 = new TextPicture("Select Character 2", new Font("MonoSpaced", Font.BOLD, 10));
		team2Player2.setyPos(200);
		team2Player2.setxPos(765);
		team2Player2.setBounds(0, 0, 900, 600);
		team2Player2.setC(Color.PINK);
		layeredPane.add(team2Player2, Integer.valueOf(1)); // Add to the layer 1

		//text asking the user to choose a background for the racetrack style they prefer 
		bgText = new TextPicture("Select Background!", new Font("MonoSpaced", Font.BOLD, 20));
		bgText.setyPos(130);
		bgText.setxPos(350);   
		bgText.setBounds(0, 0, 900, 600);
		bgText.setC(Color.RED);
		layeredPane.add(bgText, Integer.valueOf(1)); // Add to the layer 1

		//text asking the user of number of laps the race will take 
		lapsText = new TextPicture("Select Number of Laps!", new Font("MonoSpaced", Font.BOLD, 15));
		lapsText.setyPos(310);
		lapsText.setxPos(350);   
		lapsText.setBounds(0, 0, 900, 600);
		lapsText.setC(Color.RED);
		layeredPane.add(lapsText, Integer.valueOf(1)); // Add to the layer 1

		//text asking the user to choose a name for character 1 team 1
		characterName1Team1 = new TextPicture("Character 1 Name!", new Font("MonoSpaced", Font.BOLD, 10));
		characterName1Team1.setyPos(265);
		characterName1Team1.setxPos(50);   
		characterName1Team1.setBounds(0, 0, 900, 600);
		characterName1Team1.setC(Color.PINK);
		layeredPane.add(characterName1Team1, Integer.valueOf(1)); // Add to the layer 1

		//text asking the user to choose a name for character 2 team 1
		characterName2Team1 = new TextPicture("Character 2 Name!", new Font("MonoSpaced", Font.BOLD, 10));
		characterName2Team1.setyPos(265);
		characterName2Team1.setxPos(170);   
		characterName2Team1.setBounds(0, 0, 900, 600);
		characterName2Team1.setC(Color.PINK);
		layeredPane.add(characterName2Team1, Integer.valueOf(1)); // Add to the layer 1

		//text asking the user to choose a name for character 1 team 2
		characterName1Team2 = new TextPicture("Character 1 Name!", new Font("MonoSpaced", Font.BOLD, 10));
		characterName1Team2.setyPos(260);
		characterName1Team2.setxPos(650);   
		characterName1Team2.setBounds(0, 0, 900, 600);
		characterName1Team2.setC(Color.PINK);
		layeredPane.add(characterName1Team2, Integer.valueOf(1)); // Add to the layer 1

		//text asking the user to choose a name for character 2 team 2
		characterName2Team2 = new TextPicture("Character 2 Name!", new Font("MonoSpaced", Font.BOLD, 10));
		characterName2Team2.setyPos(260);
		characterName2Team2.setxPos(770);   
		characterName2Team2.setBounds(0, 0, 900, 600);
		characterName2Team2.setC(Color.PINK);
		layeredPane.add(characterName2Team2, Integer.valueOf(1)); // Add to the layer 1


		// Left Panel out of 1/3 panels
		leftPanel = new JPanel();
		leftPanel.setOpaque(false); // Make the left panel transparent
		leftPanel.setLayout(new GridLayout(2, 1)); //Grid Layout to neatly organize contents
		leftPanel.setBounds(50, 0, 220, 295); //set Bounds

		//add the text below within the left Panel to be centered within the panel
		//which asks the user to enter spiderman team name 
		characterText = new TextPicture("Select Spider-Man Team Name!", new Font("MonoSpaced", Font.PLAIN, 20)); 
		characterText.setyPos(140);
		characterText.setxPos(30);
		characterText.setFont(new Font("MonoSpaced", Font.BOLD, 10));
		characterText.setC(Color.PINK);


		// Create an additional panel for the JTextField and JComboBoxes
		JPanel leftTextPanel = new JPanel();
		leftTextPanel.setOpaque(false); //make panel transparent 
		leftTextPanel.setLayout(null); // Use null layout

		//drop down methods for character 1 and 2 for team 1 
		cbOption = new JComboBox(teamSpiderManeoption);  		
		cbOption1 = new JComboBox(teamSpiderManeoption);  		

		//textField to enter team name 
		textInput = new JTextField("", 20);
		textInput.setPreferredSize(new Dimension(185, 20)); // Set the preferred size of the textfield

		//textField to enter team 1 character 1 name  
		team1Character1Name = new JTextField("", 20);
		team1Character1Name.setPreferredSize(new Dimension(185, 20)); // Set the preferred size of the textfield

		//textField to enter team 1 character 2 name  
		team1Character2Name = new JTextField("", 20);
		team1Character2Name.setPreferredSize(new Dimension(185, 20)); // Set the preferred size of the textfield


		// Manually set the bounds for each component within the panel 
		textInput.setBounds(25, 0, 185, 20);
		team1Character1Name.setBounds(0, 125, 100, 20);
		team1Character2Name.setBounds(120, 125, 100, 20);
		cbOption.setBounds(0, 60 , 100, 20);
		cbOption1.setBounds(120, 60 , 100, 20);


		//add to all data to the main left panel for it to all be displayed 
		leftTextPanel.add(textInput);
		leftTextPanel.add(team1Character1Name);
		leftTextPanel.add(team1Character2Name);
		leftTextPanel.add(cbOption);
		leftTextPanel.add(cbOption1);
		leftPanel.add(characterText);
		leftPanel.add(leftTextPanel);

		layeredPane.add(leftPanel, Integer.valueOf(1)); // Add to layer 1 helping it to be above the blue background

		// Middle Panel of 1/3 panel
		middlePanel = new JPanel();
		middlePanel.setOpaque(false); // Make the middle panel transparent
		middlePanel.setLayout(new GridLayout(2, 1)); //grid layout 
		middlePanel.setBounds(300, 50, 300, 400); //set bounds 

		//add the title of the game called "Super Hero Race" Within the Middle Grid Layout Frame
		titleText = new TextPicture("Super-Hero Race", new Font("MonoSpaced", Font.BOLD, 20)); //text for Title 
		titleText.setyPos(25);
		titleText.setxPos(0);
		titleText.setFont(new Font("MonoSpaced", Font.BOLD + Font.ITALIC, 32));
		titleText.setC(Color.RED);

		// Create an additional panel for the JTextField and JComboBoxes
		JPanel middleTextPanel = new JPanel ();
		middleTextPanel.setOpaque(false); //make panel transparent 
		middleTextPanel.setLayout(null); //use null layout 


		//middle panel drop downs for laps, track backround 
		cbOptionRaceTrack = new JComboBox(tracks);
		cbOptionLaps = new JComboBox(laps);

		//middle panel drop down bounds 
		cbOptionRaceTrack.setBounds(90, 0 , 120, 20);
		cbOptionLaps.setBounds(90, 75, 120, 20);


		//add to all data to the main middle panel for it to all be displayed 
		middlePanel.add(titleText);
		middleTextPanel.add(cbOptionRaceTrack);
		middleTextPanel.add(cbOptionLaps);
		middlePanel.add(middleTextPanel);

		layeredPane.add(middlePanel, Integer.valueOf(1)); // Add to layer 1 helping it to be above the blue background



		// Right Panel of 1/3 panels
		rightPanel = new JPanel();
		rightPanel.setOpaque(false); // Make the right panel transparent
		rightPanel.setLayout(new GridLayout(2, 1)); //grid layout 
		rightPanel.setBounds(650, 0, 220, 295); //set bounds 

		//add the text below within the right Panel to be centered within the panel
		//which asks the user to enter captain marvel team name 
		characterText = new TextPicture("Select Captain Marvel Team Name!", new Font("MonoSpaced", Font.PLAIN, 20)); //text for team name
		characterText.setyPos(140);
		characterText.setxPos(15);
		characterText.setFont(new Font("MonoSpaced", Font.BOLD, 10));
		characterText.setC(Color.PINK);



		// Create an additional panel for the JTextField and JComboBoxes
		JPanel rightTextPanel = new JPanel();
		rightTextPanel.setOpaque(false); //set right panel transparent 
		rightTextPanel.setLayout(null); // Use null layout

		//drop down methods for character 1 and 2 for team 2
		cbOption2 = new JComboBox(teamCaptainMarveloption);  		
		cbOption3 = new JComboBox(teamCaptainMarveloption); 

		//textField asking the user for the name of the second team 
		textInput2 = new JTextField("", 20);
		textInput2.setPreferredSize(new Dimension(185, 20)); // Set the preferred size

		//textField to enter team 2 character 1 name  
		team2Character1Name = new JTextField("", 20);
		team2Character1Name.setPreferredSize(new Dimension(185, 20)); // Set the preferred size

		//textField to enter team 1 character 1 name  
		team2Character2Name = new JTextField("", 20);
		team2Character2Name.setPreferredSize(new Dimension(185, 20)); // Set the preferred size


		// Manually set the bounds for each component
		textInput2.setBounds(25, 0, 185, 20);
		team2Character1Name.setBounds(0, 120, 100, 20);
		team2Character2Name.setBounds(120, 120, 100, 20);
		cbOption2.setBounds(0, 60 , 100, 20);
		cbOption3.setBounds(120, 60 , 100, 20);

		//add to all data to the main right panel for it to all be displayed 
		rightTextPanel.add(textInput2);
		rightTextPanel.add(team2Character1Name);
		rightTextPanel.add(team2Character2Name);
		rightTextPanel.add(cbOption2);
		rightTextPanel.add(cbOption3);
		rightPanel.add(characterText);
		rightPanel.add(rightTextPanel);

		layeredPane.add(rightPanel, Integer.valueOf(1)); // Add to layer 1 helping it to be above the blue background



		// Create All the buttons for the Menu Frame 
		btnStart = new JButton("Start Race");
		btnExit = new JButton("Exit");
		btnRules = new JButton("Game Rules");  
		btnPlayMusic = new JButton("");          // use a icon to replace the button
		btnPlayMusic.setIcon(new ImageIcon("MusicOnRS.png"));
		btnStopMusic = new JButton("");          //use a icon to replace the button
		btnStopMusic.setIcon(new ImageIcon("MusicOffRS.png"));



		// Add action listeners to buttons
		btnStart.addActionListener(this);
		btnExit.addActionListener(this);
		btnPlayMusic.addActionListener(this);
		btnStopMusic.addActionListener(this);
		btnRules.addActionListener(this);
		cbOption.addActionListener(this);
		cbOption1.addActionListener(this);
		cbOption2.addActionListener(this);
		cbOption3.addActionListener(this);
		cbOptionRaceTrack.addActionListener(this);




		// Control Panel (all buttons)
		controlPanel = new JPanel();
		controlPanel.setOpaque(false); // Make the control panel transparent
		controlPanel.add(btnStart);
		controlPanel.add(btnRules);
		controlPanel.add(btnExit);
		controlPanel.add(btnPlayMusic);
		controlPanel.add(btnStopMusic);	
		controlPanel.setBounds(10, 500, 900, 600); //set bounds
		layeredPane.add(controlPanel, Integer.valueOf(1)); // Add to layered pane with a higher layer to be above background

		setSize(900, 600);  //set frame size
		setLocation(100, 100); //set start location
		setVisible(true); //make visible
		setResizable(false); //not resizeable
		setDefaultCloseOperation(EXIT_ON_CLOSE); //allow to be closed with x 
	}



	//MUSIC FOR THE MENU 
	//CREDITS LEARNED IN DESCRIPTION
	private void loadBackgroundMusic(String song) {
		try {

			// Create a File object for the audio file
			File audioFile = new File(song);

			// Create an AudioInputStream for the audio file 
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			//Create a Clip to play the audio
			//The Clip function allows audio data to be loaded 
			//Because the data is pre downloaded and has a known length,
			//it allows me to set a clip to start playing at any position in its
			//audio data. Since I know that the file is around 1 mins 
			//long the clip function also allows me to create a loop, 
			//so that when the clip is played it will repeat forever
			//to specify the amount of times I want to play my file I 
			//used the function Clip.LOOP.CONTINUOUSLY
			backgroundMusicClip = AudioSystem.getClip();

			// Open the Clip with the audio stream
			backgroundMusicClip.open(audioStream);

			// Loop the background music continuously
			backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// Handle exceptions if there are issues loading or playing the audio
			// since the program uses the Clip function and I am using my custom
			// music file the first error exception (UnsupportedAudioFileException)
			// makes sure that the file type I am using is supported by Java for 
			// instance I used a .wav file and file types such as AIFF, AU, SND are 
			// not supported file. While LineUnavailbleException
			// makes sure that the audio line or file in this case which was the Clip 
			// is available and can be used within the program 
			// all these exception handlers are required to identify the error
			// as well as making the program run smoothly without errors
			e.printStackTrace();
		}
	}	





	// Action event handler
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) { //start button

			//this whole if statement makes sure that none of the JTextFields in the program 
			//are left empty by the user, if there are a JOption Error pops up 
			if (textInput.getText().isEmpty() || textInput2.getText().isEmpty() ||
					team1Character1Name.getText().isEmpty() || team1Character2Name.getText().isEmpty() ||
					team2Character1Name.getText().isEmpty() || team2Character2Name.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Fill in All The Required Team Names and Character Names!", "Error", JOptionPane.ERROR_MESSAGE);
			} 

			//this whole if statement makes sure that none of the JComboBoxes (Dropdowns) in the program 
			//are the same making sure all characters are different , if there are a JOption Error pops up 
			else if (cbOption.getSelectedItem().equals(cbOption1.getSelectedItem()) ||
					cbOption2.getSelectedItem().equals(cbOption3.getSelectedItem())) {
				JOptionPane.showMessageDialog(null, "Please ensure that selections are unique for characters!", "Error", JOptionPane.ERROR_MESSAGE);
			}

			//this whole if statement makes sure that none of the JTextFields which ask for the team name
			//are the same making sure team names are different, if there are a JOption Error pops up 
			else if (textInput.getText().equals(textInput2.getText())){
				JOptionPane.showMessageDialog(null, "Please ensure that the team names unique!", "Error", JOptionPane.ERROR_MESSAGE);
			}

			//this whole if statement makes sure the JTextFields that ask for the character names are the same
			//however the code below makes sure that team 1 characters can not have the same names, but a character 
			//from team 1 and team 2 can have the same name. This is not a issue as the Character images 
			//can be use to differentiate the two team characters, if there are a JOption Error pops up
			else if (team1Character1Name.getText().equals(team1Character2Name.getText()) 
					|| team2Character1Name.getText().equals(team2Character2Name.getText())){
				JOptionPane.showMessageDialog(null, "Please ensure that character names are not repeated in thier respective teams!", "Error", JOptionPane.ERROR_MESSAGE);
			}


			
			else {
				//Checks to see which background is chosen, and initializes the ImagePicture variable accordingly 
				//for the background to be used in raceTrack frame.
				if (((String) cbOptionRaceTrack.getSelectedItem()).equals("HeadQuarters")) {
					this.sendBackground = new ImagePicture(new ImageIcon("HQMARVEL.png"));
				} 
				else if (((String) cbOptionRaceTrack.getSelectedItem()).equals("New-York")) {
					this.sendBackground = new ImagePicture(new ImageIcon("NewYork.png"));
				}
				else if (((String) cbOptionRaceTrack.getSelectedItem()).equals("Space")) {
					this.sendBackground = new ImagePicture(new ImageIcon("marvelSpace.png"));
				}
				
				//Sets the name typed within main menu into the marvelHero objects
				//to be sued in raceTrack frame
				this.Image1.setName(team1Character1Name.getText());
				this.Image2.setName(team1Character2Name.getText());
				this.Image3.setName(team2Character1Name.getText());
				this.Image4.setName(team2Character2Name.getText());
				
				//stops music
				backgroundMusicClip.stop();
				//Disposes main menu frame 
				this.dispose();
				//Calls in raceTrack constructor from RaceTrack class with all needed information.
				r = new RaceTrack(this.sendBackground,this.Image1, this.Image2, this.Image3,this.Image4, Integer.valueOf((String) this.cbOptionLaps.getSelectedItem()), this.textInput.getText(), this.textInput2.getText());

			}

		} else if (e.getSource() == btnExit) { //exit button
			System.exit(0); // Close the application
		} 


		else if (e.getSource() == btnRules) { //rules button
			new RulesFrame(); //call the rules class which will display the rules frame when btn clicked
		} 


		else if (e.getSource() == btnPlayMusic) { //play music button
			//play the music again 
			backgroundMusicClip.start();
		} 

		else if (e.getSource() == btnStopMusic) { //stop music button
			//stop the music from playing 
			backgroundMusicClip.stop();
		} 




		//PULL DOWN FOR TEAM 1 CHARACTER 1 CHOICE 
		//////////////////////////////////////////////////

		if (e.getSource() == cbOption) {
			String selectedHero = (String) cbOption.getSelectedItem(); //get the item selected from ComboBox

			if (selectedHero.equals("Iron Man")) { //if Iron man selected change character to iron man
				Image1.setImage(new ImageIcon("Iron_ManRS.png")); //image 
				Image1.repaint(); //repaint
			} 

			else if (selectedHero.equals("Spider-Man")) { //if spiderman selected change character to spiderman
				Image1.setImage(new ImageIcon("spider_manRS.png"));
				Image1.repaint();	
			}

			else if (selectedHero.equals("Hulk")) { //if hulk selected change character to hulk
				Image1.setImage(new ImageIcon("hulkRS.png"));
				Image1.repaint();	
			}


			else if (selectedHero.equals("CAP")) { //if Cap selected change character to Captain America
				Image1.setImage(new ImageIcon("Cap_ARS.png"));
				Image1.repaint();	
			}

		}


		//PULL DOWN FOR TEAM 1 CHARACTER 2 CHOICE 
		///////////////////////////////////

		//same commenting and pictures as code above but for the cbOption1 combo box which 
		//is responsible for the team 1 character 2 image 
		if (e.getSource() == cbOption1) {
			String selectedHero = (String) cbOption1.getSelectedItem();


			if (selectedHero.equals("Iron Man")) {
				Image2.setImage(new ImageIcon("Iron_ManRS.png"));
				Image2.repaint();         
			} 

			else if (selectedHero.equals("Spider-Man")) {
				Image2.setImage(new ImageIcon("spider_manRS.png"));
				Image2.repaint();	
			}

			else if (selectedHero.equals("Hulk")) {
				Image2.setImage(new ImageIcon("hulkRS.png"));
				Image2.repaint();	
			}


			else if (selectedHero.equals("CAP")) {
				Image2.setImage(new ImageIcon("Cap_ARS.png"));
				Image2.repaint();	
			}

		}



		//PULL DOWN FOR TEAM 2 CHARACTER 1 CHOICE 
		///////////////////////////////////

		//same commenting but DIFFERENT IMAGES as code above but for the cbOption2 combo box which 
		//is responsible for the team 2 character 1 image 
		if (e.getSource() == cbOption2) {
			String selectedHero = (String) cbOption2.getSelectedItem();


			if (selectedHero.equals("CapMarvel")) {
				Image3.setImage(new ImageIcon("CapMarvelRS.png"));
				Image3.repaint();         
			} 

			else if (selectedHero.equals("Nick Fury")) {
				Image3.setImage(new ImageIcon("NickFuryRS.png"));
				Image3.repaint();	
			}

			else if (selectedHero.equals("HawkEye")) {
				Image3.setImage(new ImageIcon("HawkEyeRS.png"));
				Image3.repaint();	
			}


			else if (selectedHero.equals("Falcon")) {
				Image3.setImage(new ImageIcon("FalconRS.png"));
				Image3.repaint();	
			}


		}


		//PULL DOWN FOR TEAM 2 CHARACTER 2 CHOICE 
		///////////////////////////////////

		//same commenting and same images as code above but for the cbOption3 combo box which 
		//is responsible for the team 2 character 2 image 
		if (e.getSource() == cbOption3) {
			String selectedHero = (String) cbOption3.getSelectedItem();


			if (selectedHero.equals("CapMarvel")) {
				Image4.setImage(new ImageIcon("CapMarvelRS.png"));
				Image4.repaint();         
			} 

			else if (selectedHero.equals("Nick Fury")) {
				Image4.setImage(new ImageIcon("NickFuryRS.png"));
				Image4.repaint();	
			}

			else if (selectedHero.equals("HawkEye")) {
				Image4.setImage(new ImageIcon("HawkEyeRS.png"));
				Image4.repaint();	

			}


			else if (selectedHero.equals("Falcon")) {
				Image4.setImage(new ImageIcon("FalconRS.png"));
				Image4.repaint();	
			}


		}



		//PULL DOWN RACETRACK BACKGROUND 
		////////////////////////////////////////////

		//Similar code and commenting compared to all the cbOption codes above 
		//however this code below changes the image of the racetrack the user will 
		//get to race on using the images which they are being set to
		if (e.getSource() == cbOptionRaceTrack) {
			String selectedTrack = (String) cbOptionRaceTrack.getSelectedItem();

			if (selectedTrack.equals("HeadQuarters")) {
				raceTrackBackround.setImage(new ImageIcon("HQMARVELRS.png"));
				raceTrackBackround.repaint();         
			} 

			else if (selectedTrack.equals("New-York")) {
				raceTrackBackround.setImage(new ImageIcon("NewYorkRS.png"));
				raceTrackBackround.repaint();	
			}


			else if (selectedTrack.equals("Space")) {
				raceTrackBackround.setImage(new ImageIcon("marvelSpaceRS.png"));
				raceTrackBackround.repaint();	
			}

		}


	}




	//Main Method 
	public static void main(String[] args) {
		UniverseGUI animation = new UniverseGUI(); //runs and calls UniverseGUI



	}
}
