import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author S. Thanush
 * Date: Nov. 2023
 * Description: Class to create MarvelHero object that can hold various important data
 *				for game. Inherits from ImagePicture
 *
 *Method List: 
 * public MarvelHero(ImageIcon image) - Default constructor
 * public boolean isTeamWin() - returns team win
 * public void setTeamWin(boolean teamWin) - sets team win
 * public int getPoints() - return points
 * public void setPoints(int points) - set points
 * public int getLaps() - return laps
 * public void setLaps(int laps) - set laps
 * public String getName() - return name
 * public void setName(String name) - set name
 * public int getStepsLeft() - return steps
 * public void setStepsLeft(int steps) - set steps
 * public int getStepsTaken() - return steps taken
 * public void setStepsTaken(int stepsTaken) - set steps taken
 * public int getxVel() - return xvel
 * public void setxVel(int xVel) - set xvel
 * public int getyVel() - get yvel
 * public void setyVel(int yVel) - set yvel
 * public int getWins() - get wins
 * public void setWins(int wins) - set wins
 * public int getStepsLeft() - get steps left
 * public void setStepsLeft(int stepsLeft) - set steps left
 * public void move() - Method that sets the x and y position
						based of set x and y velocity,adds one to steps taken
						subtracts one to steps needed to move, and repaints it. 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class MarvelHero extends ImagePicture {


	//Declares private instances/attributes
	private int stepsLeft,stepsTaken;
	private int xVel,yVel;
	private int wins,laps, points;
	private String name;
	private boolean teamWin;

	//Default Constructor
	public MarvelHero(ImageIcon image) {
		super(image);
		this.stepsLeft = 0;
		this.stepsTaken = 0;	
		this.wins = 0;
		this.laps = 0;
		this.xVel = 0; 
		this.yVel = 0;
		this.name = "";
		this.points = 0;
		this.teamWin = false;
	}


	//Overloaded constructor (NOT NEEDED)
	//public MarvelHero(ImageIcon image, int x, int y, int stepsTaken,int stepsLeft,int wins, int laps, String name, int xVel, int yVel, int points, boolean teamWin) { 
	//	super(image,x,y);
	//	this.stepsLeft = stepsLeft;
	//	this.stepsTaken = stepsTaken;
	//	this.wins = wins;
	//	this.laps = laps;
	//	this.xVel = xVel;
	//	this.yVel = yVel;
	//	this.name = name;
	//	this.points = points; 
	//	this.teamWin = teamWin;
	//}

	/**
	 * @return the teamWin
	 */
	public boolean isTeamWin() {
		return teamWin;
	}

	/**
	 * @param teamWin the teamWin to set
	 */
	public void setTeamWin(boolean teamWin) {
		this.teamWin = teamWin;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the laps
	 */
	public int getLaps() {
		return laps;
	}

	/**
	 * @param laps the laps to set
	 */
	public void setLaps(int laps) {
		this.laps = laps;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the stepsTaken
	 */
	public int getStepsTaken() {
		return stepsTaken;
	}

	/**
	 * @param stepsTaken the stepsTaken to set
	 */
	public void setStepsTaken(int stepsTaken) {
		this.stepsTaken = stepsTaken;
	}

	/**
	 * @return the xVel
	 */
	public int getxVel() {
		return xVel;
	}

	/**
	 * @param xVel the xVel to set
	 */
	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	/**
	 * @return the yVel
	 */
	public int getyVel() {
		return yVel;
	}

	/**
	 * @param yVel the yVel to set
	 */
	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	/**
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * @param wins the wins to set
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * @return the stepsLeft
	 */
	public int getStepsLeft() {
		return stepsLeft;
	}

	/**
	 * @param stepsLeft the stepsLeft to set
	 */
	public void setStepsLeft(int stepsLeft) {
		this.stepsLeft = stepsLeft;
	}

	//Method that sets the x and y position
	//based of set x and y velocity,adds one to steps taken
	//subtracts one to steps needed to move, and repaints it. 
	public void move() { 
		setyPos(getyPos() + this.yVel);
		setxPos(getxPos() + this.xVel);
		this.stepsLeft-=1;
		this.stepsTaken+=1;
		repaint();
	}

	/**
	 * Self-Testing Main Method
	 */
	public static void main(String[] args) {
		//Creates frame and object
		JFrame f = new JFrame();
		f.setSize(400,350);
		MarvelHero m = new MarvelHero(new ImageIcon("spider_manRS.png"));
		f.add(m);
		f.setVisible(true);

		m.setName("Thanush");
		while (m.getLaps() == 0) { 
			//Tests setters and getters, moves object right
			m.setStepsLeft(100);
			m.setxVel(1);
			m.setyVel(0);

			for (int i = m.getStepsLeft(); i > 0; i--) { 
				m.move();
				try { 
					Thread.sleep(10);
				} catch (Exception error) { 
				}
			}

			//Tests setters and getters, moves object down

			m.setStepsLeft(100);
			m.setStepsTaken(0);
			m.setxVel(0);
			m.setyVel(1);
			for (int i = m.getStepsLeft(); i > 0; i--) { 
				m.move();
				try { 
					Thread.sleep(10);
				} catch (Exception error) { 
				}
			}

			//Tests setters and getters, moves object left
			m.setStepsLeft(100);
			m.setStepsTaken(0);
			m.setxVel(-1);
			m.setyVel(0);
			for (int i = m.getStepsLeft(); i > 0; i--) { 
				m.move();
				try { 
					Thread.sleep(10);
				} catch (Exception error) { 
				}
			}

			//Tests setters and getters, moves object up
			m.setStepsLeft(100);
			m.setStepsTaken(0);
			m.setxVel(0);
			m.setyVel(-1);
			for (int i = m.getStepsLeft(); i > 0; i--) { 
				m.move();
				try { 
					Thread.sleep(10);
				} catch (Exception error) { 
				}
			}
			m.setWins(1);
			m.setTeamWin(true);
			m.setPoints(123123123);
			m.setLaps(m.getLaps()+1);
		}
		JOptionPane.showMessageDialog(null,"Name: " + m.getName() 
						   + "\nTeam win: " + m.isTeamWin()
						   + "\nPoints: " + m.getPoints()
						   + "\nLaps Done: " + m.getLaps());
		
	}

}
