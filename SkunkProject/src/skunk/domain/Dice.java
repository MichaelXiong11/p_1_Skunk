package skunk.domain;
import edu.princeton.cs.introcs.StdOut;

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
    
    //Total sum of two dice
    private int diceTotal;
    /**
     * Constructor for objects of class Dice
     */
    public Dice(){
        this.die1 = new Die();
        this.die2 = new Die();
    }
    
    /**
     * Parameterized Constructor (Overloading)
     */
    public Dice(Die die1, Die die2){
        this.die1 = die1;
        this.die2 = die2;
    }
    
    //Getter (Accesser) methods
    public Die getDie1(){
    	return die1;
    }
    public Die getDie2(){
    	return die2;
    }
    public int getDiceTotal(){
    	return diceTotal;
    }
    
    //Setter (Mutator) methods
    public void setDie1(Die die1){
    	this.die1 = die1;
    }
    public void setDie2(Die die2){
    	this.die2 = die2;
    }
    public void setDiceTotal(int diceTotal){
    	this.diceTotal = diceTotal;
    }
        
    /**
     * Method that rolls two dice
     */
    public void roll(){
        die1.roll();
        die2.roll();
        diceTotal = die1.getValue() + die2.getValue();
    }
    
    public String toString(){
        String s = "Die 1: " + die1.getValue() + " | Die 2: " + die2.getValue();
        
        return s;
    }
}