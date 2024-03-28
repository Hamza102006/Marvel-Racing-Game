
/**
 * @author 738077
 * Description: Represent a die object 
 *
 */
public class Die {

	/**
	 * Arrtibutes/properties/data 
	 */
	
	private int faces;
	private int value;  
	
	/**
	 * default constructor 
	 */
	public Die() {
		//initalize the attributes 
		this.faces = 6;
		rollDie();
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * An Overloader Constructor  
	 * An example of polymorphism (Many Forms)
	 */
	
	public Die (int faces) {
		this.faces = faces;
		rollDie();
	}
	
	//behaviours of die
	
	/**
	 * Method to roll my die
	 */
	public void rollDie() {
		this.value = (int)(Math.random() * this.faces + 1);
		}
	
	
	/**
	 * Method to read the value
	 */
	
	public int getValue() { 
		return this.value;
	}
	
	/*
	 * Method Compare to 
	 */
	public boolean compareTo(Die d) {
		d.getValue();
		if (this.getValue() == d.getValue()) {
		return true;

		}
		
		else {
			return false;
		}
		
	}
	
	
	
	

	/**
	 * Self Testing Method 
	 */
	public static void main(String[] args) {
		//Create one die object 
		Die d = new Die();
		
		// read the die value 
		System.out.println(d.getValue());
		
		// call a roll die method again 
		d.rollDie();
		
		
		//read the value again 
		System.out.println(d.getValue());

		//declare and create a second die
		Die d1 = new Die();
		
		// read the die value 
		System.out.println(d1.getValue());
		
		//call role die1 method 
		d1.rollDie();
		
		// read the die value 
		System.out.println(d1.getValue());
		
		//roll teh two dice 100 times and check when they are equal 
		// and dispaly thier values
		int counter = 0;
		for (int i = 0; i < 100; i++) {
				d.rollDie();
				d1.rollDie();
				if (d.getValue() == d1.getValue()) {
					counter++;
					System.out.println(d.getValue() + " " + d1.getValue());
					
					
					
				}
				
		}
				
			System.out.println("There were " + counter + " idenctical rolls");
		
		boolean tester;
		for (int i = 0; i < 10; i++) {
		d.rollDie();
		tester = d.compareTo(d1);
		System.out.println(tester);
	
		
		

		}

		
		
		
		
	}
	
}

	

