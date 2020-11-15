package skunk.domain;

public class Die{
    //The current face value of the die
    private int value;
    
    //Lowest and highest values on the die
    private final int MIN = 1;
    private final int MAX = 6;
    
    /**
     * Default Constructor for objects of class Die
     */
    public Die(){
       roll();
    }
    
    public Die(int value){
        if(value > MAX || value < MIN){
            roll();
        }
        else{
            this.value = value;
        }
    }
    
    public int getValue(){
    	return value;
    }
    
    public void setValue(int value){
    	this.value = value;
    }
    
    /**
     * Method that rolls a die; possibly changing it's value
     */
    public void roll(){
        value = (int)(Math.random() * MAX) + MIN;
    }
}