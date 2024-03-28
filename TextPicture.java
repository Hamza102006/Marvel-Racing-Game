import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 */

/**
 * @author Hamza
 * Date: October 18, 2023
 * Description: Display Text and allow many changes using setters and getters
 *
 *
 * METHODS;
 * 
 * 	public TextPicture() { //default constructor 
 *
 *	public TextPicture(String text, Font font) { //overloaded constructor 
 *
 *	public String getTitleText() { //get the title text
 *
 *	public void setTitleText(String titleText) { //set the title text 
 *
 *	public Font getFont() { // get the font
 *
 *	public void setFont(Font font) { //set the font
 *
 *	public void paint(Graphics g) { //paint method 
 *
 *	public static void main(String[] args) { // main method 
 *
 */
public class TextPicture extends Picture {

	/*
	 * Private Data for text
	 */

	//set variables for string and font
	private String titleText;
	private Font font;


	/**
	 * Default Constructor
	 */
	public TextPicture() {
		// set the instance variables to input
		super();
		this.titleText = "";
		this.font = new Font("MonoSpaced", Font.PLAIN,20);
		setxPos(150);
		setyPos(30);
	}

	/**
	 * Overloaded Constructor
	 */

	public TextPicture(String text, Font font) {
		super();
		this.titleText = text;
		this.font = font;

	}



	/**
	 * @return the titleText
	 */
	public String getTitleText() {
		return titleText;
	}

	/**
	 * @param titleText the titleText to set
	 */
	public void setTitleText(String titleText) {
		this.titleText = titleText;
		repaint();
	}

	/**
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public void setFont(Font font) {
		this.font = font;
		repaint();
	}

	// our paint method
	public void paint(Graphics g) {
		g.setColor(this.getC()); //set out colour 
		g.drawString(this.titleText, this.getxPos(), this.getyPos());
		g.setFont(this.font);
		repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Text Window");

		f.setSize(400, 500);

		// Create TextPicture object
		TextPicture p = new TextPicture("Hello World!", new Font("MonoSpaced", Font.PLAIN, 20));
		p.setxPos(100);
		p.setyPos(100);

		p.repaint();


		// add and display TextPicture object
		f.add(p);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "Wait");


		p.setFont(new Font("Arial", Font.PLAIN, 20)); // set and change font

		JOptionPane.showMessageDialog(null, "Wait");

		p.setTitleText("Hamza Khan"); //set text to my name 

		JOptionPane.showMessageDialog(null, "Wait");

		p.setC (Color.GREEN); //set color to green

		JOptionPane.showMessageDialog(null, "Wait");

		p.setxPos(100); //set x and y positions to different values 
		p.setyPos(200);

		p.repaint();
			







	}
}