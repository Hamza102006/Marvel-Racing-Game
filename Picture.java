/**
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Authour: Hamza Khan 
 * Date: 10/16/2023
 * Description: A class that define a component - picture
 * 
 * REMEMBER SETTERS AND GETTERS BY HAND FOR UNIT TEST
 * 
 * 
 * 
 */
public class Picture extends JComponent{

	/*
	 * private data for picture 
	 * defines it colour, location and size
	 */

	private Color c;
	private int xPos, yPos, myWidth, myHeight;


	/*
	 * This is my default constructor 
	 */
	public Picture() {
		// Intialize its private data 
		this.c = Color.BLUE;
		this.xPos = 0;
		this.yPos = 0;
		this.myWidth = 50;
		this.myHeight = 25;
		repaint(); //calls out paint method 
	}

	//Overloaded constructor for a spefifed location and dimension
	//
	public Picture(int x, int y, int w, int h) {
		//intiizlaze the color 
		this.c = Color.BLUE;
		this.xPos = x;
		this.yPos = y;
		this.myWidth = w;
		this.myHeight = h;
		repaint();
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @return the myWidth
	 */
	public int getMyWidth() {
		return myWidth;

	}

	/**
	 * @return the myHeight
	 */
	public int getMyHeight() {
		return myHeight;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(Color c) {
		this.c = c;
	}

	/*
	 * Set Color Based on RGB - overloaded method
	 */

	public void setC(int r, int g, int b) {
		this.c = new Color (r,g,b);
	}

	/**
	 * @return the c
	 */
	public Color getC() {
		return c;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * @param myWidth the myWidth to set
	 */
	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
	}

	/**
	 * @param myHeight the myHeight to set
	 */
	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
	}


	//our own paint method - Overrides the JCompoenents' Paint
	public void paint(Graphics g) {
		g.setColor(this.c); //set out colour 
		//draw rectangle 
		//CHANGES MADE TO THE WIDTH AND X-POS TO ALLOW THE 
		//FILLED OVAL OR PUPIL TO BE LOCATED DIRECTLY INSIDE THE EYE 
		//AND BE PROPTAION THROUGH ALL THE DIFFRENT TESTS 
		g.drawRect(this.xPos,  this.yPos,  this.myWidth, this.myHeight);
		g.drawOval(xPos, yPos, myWidth, myHeight);
		g.fillOval(xPos + myWidth/3, yPos, myWidth/3, myHeight);

	}


	public static void main(String[] args) throws InterruptedException {
		//Create a JFrame to draw my picture
		JFrame f = new JFrame("Frame for Testing");

		Picture p1 = new Picture(); //creates a picture object 

		f.setSize (400,350);
		f.add(p1); // add my object to the frame 
		f.setVisible(true); // paint the jFrame 

		//wait!! Delay Before the colour changes
		JOptionPane.showMessageDialog(null, "Wait");

		//change the color 
		p1.setC (Color.RED);

		//repaint the frame
		f.repaint();

		//wait!! Delay Before the colour changes
		JOptionPane.showMessageDialog(null, "Wait");

		//test the setters for positions and size
		p1.setxPos(10);
		p1.setyPos(250);
		p1.setMyHeight(40);
		p1.setMyWidth(25);
		p1.repaint(); // call to change the change 

		//wait!! Delay Before the colour changes
		JOptionPane.showMessageDialog(null, "Wait");

		//Create a second object 
		Picture p2 = new Picture(200, 50, 100,50);
		f.add(p2);
		f.setVisible(true); // paint the jFrame 

		//wait!! Delay Before the colour changes
		JOptionPane.showMessageDialog(null, "Wait");

		p1.setC(255,200,0);

		f.repaint();

		//create another method in picture class change red p1 to half the 
		//width and double the height (HOMEWORK)

		//wait!! Delay Before the colour changes
		JOptionPane.showMessageDialog(null, "Wait");

		p2.setMyWidth(p2.getMyWidth()/2);
		p2.setMyHeight(p2.getMyHeight()*2);

		p2.repaint();

		//wait!! Delay Before the colour changes
		JOptionPane.showMessageDialog(null, "Wait");


		p2.setC(50,150,0); //change the colour green 

		f.repaint(); //repaint the frame

		//wait!! Delay Before the colour changes
		JOptionPane.showMessageDialog(null, "Wait");

		//move the shape slowly
		for (int i = 0; i <= 10; i++) {
			p2.setxPos(p2.getxPos() + 10); 
			p2.setyPos(p2.getyPos() + 10);
			//JOptionPane.showMessageDialog(null, "The X Postion: " + p2.getxPos() + "\nThe Y Postion: " + p2.getyPos());
			Thread.sleep(50);
			p2.repaint();
		}



	}

}
