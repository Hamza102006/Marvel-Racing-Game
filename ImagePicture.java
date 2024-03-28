/**
 * 
 */
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Author: Hamza Khan 
 * Date: October 17, 2023
 * Description: Class for objects that display icons 
 * 
 *              A picture class that inherits from picture and 
 *              draws icon files 
 * 
 * 
 */
public class ImagePicture extends Picture {

	/**
	 * Private instance varaibles  
	 */
	
	private ImageIcon image; //for stroing icons 
	
	
	
	
	public ImagePicture(ImageIcon img) {
		//call our super class consturcor 
		super();
		this.image = img;
		setMyHeight(img.getIconHeight());
		setMyWidth(img.getIconWidth());
		repaint();

	}
	
	
	
	

	/*
	 * Overloaded construcor to specify location and image 
	 */
	
	/**
	 * @param image the image to set
	 */
	public void setImage(ImageIcon image) {
		this.image = image;
	}





	public ImagePicture(ImageIcon img, int x, int y) {
		//calls the superclass constuctor 
		super (x, y, img.getIconWidth(), img.getIconHeight());
		this.image = img;
		repaint();
	}
	
	/*
	 * Override the paint method from picture 
	 */
	
	public void paint(Graphics g) {
		//paint the image
		this.image.paintIcon(this, g, getxPos(), getyPos());
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Testing Only"); //create a Jframe for testing 
		
		//set the size
		f.setSize(400, 350);
		
		ImagePicture p1 = new ImagePicture (new ImageIcon("minion.png"));
		
		f.add(p1); //add the object to the frame
		
		f.setVisible(true); //displays the frame 
		
		//pause
		JOptionPane.showMessageDialog(null, "Wait");
		
		//craete an object with a diffrent picture and locations
		ImagePicture p2 = new ImagePicture (new ImageIcon("minion.png"), 100, 50);

		f.add(p2);
		
		f.setVisible(true);
		
		//pause
		JOptionPane.showMessageDialog(null, "Wait");
		
		p2.setxPos(p2.getxPos() + 50);
		p2.repaint();
		p1.setyPos(p1.getyPos() + 100);
		p1.repaint();
		
		//test the setImage to change the image of the object p1
		p1.setImage(new ImageIcon("Iron_Man.png"));

		p1.repaint();
}





	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return image;
	}

	

}
