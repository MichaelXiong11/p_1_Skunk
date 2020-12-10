package skunk.domain;


/**
 * Dice represents a single pair of rollable Die objects, randomly generating
 * sums of their two values
 * 
 * This is a Javadoc comment: add more to your finished class below
 * 
 * @author eric, edited by Michael
 *
 */

public class Dice{
    //Die objects
    private Die die1;
    private Die die2;
    
    //The sum of the two dice values
    private int diceSum;
    /**
     * Constructor for objects of class Dice
     */
    public Dice(){
        die1 = new Die();
        die2 = new Die();
        
        diceSum = die1.getValue() + die2.getValue();
    }
    
    //Getter (Accessor) methods
    public Die getDie1(){
    	return die1;
    }
    public Die getDie2(){
    	return die2;
    }
    public int getDiceSum(){
    	return diceSum;
    }
    
    //Setter (Mutator) methods
    public void setDie1(Die die1){
    	this.die1 = die1;
    }
    public void setDie2(Die die2){
    	this.die2 = die2;
    }
    
    /**
     * Method that rolls both die
     */
    public void roll(){
        die1.roll();
        die2.roll();
        
        diceSum = die1.getValue() + die2.getValue();
    }
    
    /**
     * toString method
     */
    public String toString(){
        String s = "{DieOne: " + die1.getValue() + "  DieTwo: " + die2.getValue() + "}";
        
        return s;
    }
}