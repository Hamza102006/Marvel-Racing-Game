import java.io.BufferedReader;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.PrintWriter;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

/**

 *

 */

/**

 * @author Rudra Patel

 * Date: 2023-11-13

 *

 * Description: The class calculates the points of players. The points are calculated using

 * the following formula: stepsMoved * (int) Math.pow(playerWin, 5). An additional

 * of stepsMoved multiplied by 10 will be awarded to the player that wins. Otherwise,

 * the points will just be based on the formula to calculate points. The class will

 * save the data to a new file, to access the data after the game ends using a loader class.

 *

 * Method list:

 * public HighScore() - Default constructor

 * public static int calculatePoints (int stepsMoved, int playerWin, boolean teamWin) - calculates points for each player

 * public static void newFile(int teamPoints) - store the points to a new file

 * public static String [] loadFile(String fileName) throws IOException - load data from file

 * public static void main(String[] args) throws IOException - self testing main

 *

 */

public class HighScore{

	// Behaviors of the HighScore class

	/**

	 * Method to calculate points

	 */

	public static int calculatePoints (int stepsMoved, int playerWin, boolean teamWin) {

		//calculate points using stepsMoved, and playerWin.

		int points = stepsMoved * (int) Math.pow(playerWin, 5);

		if (teamWin == true) { //bonus points if team wins

			points = points + (stepsMoved*10);

		}

		return points;

	}

	/**

	 * Method will create an new file to hold information

	 */

	public static void newFile(String info) {

		try {

			//creates new file to write to

			PrintWriter wFile = new PrintWriter(new FileWriter("RaceResults.txt"));

			//prints each team points to file

			wFile.println(info);

			wFile.print("EOF");

			//close the file

			wFile.close();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**

	 * Method to open and read from the file

	 * Given the file name. Returns a string array

	 */

	public static String [] loadFile(String fileName) throws IOException {

		//open the file in fileName to read
		FileReader fr = new FileReader (fileName);
		BufferedReader input = new BufferedReader(fr);
		int size = 0;
		while (!input.readLine().equalsIgnoreCase("EOF")) { 
			size++;
		}
		input.close(); // CLose file
		BufferedReader input2 = new BufferedReader(new FileReader(fileName));
		//declare and create an array for the data set
		String score [] = new String [size];
		//loop through the file and save the data in the array
		for (int i = 0; i < size; i++) {
			score [i] = input2.readLine();
		}
		//close the input stream
		input2.close();
		//return the data back to the caller
		return score;
	}

	/**

	 * @param args

	 *

	 * self-testing main

	 */

	public static void main(String[] args) throws IOException{

		//Declare and Initialize the variables

		boolean teamWin;

		String status, info = "";

		String [] score;

		int playerWin = 0;

		int steps = 0, points = 0, totalPoints = 0;

		//check points calculated when win is false

		steps = 100;

		playerWin = 2;

		teamWin = false;

		//Calculate the points for the player using the method

		points = calculatePoints(steps, playerWin, teamWin);


		//print the points

		System.out.println("Points: " + points);


		//total points for the player

		totalPoints += points;

		//print total points of the player

		System.out.println("Total Points: " + totalPoints);


		// wait!

		JOptionPane.showMessageDialog(null, "Wait!");




		//check points calculated when win is true

		steps = 100;

		playerWin = 2;

		teamWin = true;

		//Calculate the points for the player using the method

		points = calculatePoints(steps, playerWin, teamWin);

		//print the points

		System.out.println("Points: " + points);

		//total points for the player

		totalPoints += points;

		//print total points of the player

		System.out.println("Total Points: " + totalPoints);

		// wait!

		JOptionPane.showMessageDialog(null, "Wait!");



		//print the points

		info = "Points: " + points;

		//total points for the player

		totalPoints += points;

		//print total points of the player

		info += "\nTotal Points: " + totalPoints;

		// Save to file and check if file was saved

		newFile(info);

		JOptionPane.showMessageDialog(null, "Race Results Saved!");

		// Load from file (after the game ends)

		String fileName = JOptionPane.showInputDialog(null, "Enter file name: ", "RaceResults.txt");

		score = loadFile(fileName);

		// prints each phrase to file

		for (int i = 0; i < score.length; i++) {

			System.out.println(score[i]);

		}

	}

}