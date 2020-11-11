package skunk.domain;

public class Die{
    private final int MAX = 6;
    private int value;

    /**
     * Default Constructor
     */
    public Die(){
        this.roll();
    }
    
    /**
     * Parameterized constructor (Overloading
     */
    public Die(int v){
        value = v;
    }
    
    //Getter (Accessor) methods
    public int getValue(){
    	return value;
    }
    
    //Setter (Mutator) methods
    public void setValue(int v){
    	value = v;
    }
    
    /**
     * Rolling a  die
     * @return int The value of the die roll
     */
    public void roll(){
        value = (int)(Math.random() * MAX) + 1;
    }
    
    public String toString(){
        return "Die: " + value;
    }
}
