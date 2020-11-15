package skunk.domain;

public class Turn{
    //The total score a player has rolled in one turn
    private int turnScore;
    
    //The dice total of a roll
    private int rollScore;
    
    /**
     * Default constructor for objects of class Turn
     */
    public Turn(){
        this.turnScore = 0;
        this.rollScore = 0;
    }
    
    public Turn(int turnScore){
        this.turnScore = turnScore;
    }
    
    //Getter (Accessor) methods
    public int getTurnScore(){
    	return turnScore;
    }
    public int getRollScore(){
    	return rollScore;
    }
    
    
    //Accessor (Mutator) methods
    public void setTurnScore(int turnScore){
    	this.turnScore = turnScore;
    }
    public void setRollScore(int rollScore){
    	this.rollScore = rollScore;
    }
    
    /**
     * Method that increases the turnScore
     * @param int Amount to increase turnScore by
     */
    public void increaseTurnScore(int t){
        turnScore += t;
    }
    
    /**
     * toString method
     */
    public String toString(){
        String s = "TurnScore: " + turnScore;
        
        return s;
    }
}