package skunk.domain;

public class Turn{
    //Total points a player has scored in one turn
    private int turnScoreTotal;
    
    //Points a player has scored on one dice roll (sum of two dice) 
    private int rollScore;

    /**
     * Constructor for objects of class Turn
     */
    public Turn(){
        turnScoreTotal = 0;
        rollScore = 0;
    }
    
    //Getter (Accessor) methods
    public int getCurrentTurnScoreTotal(){
    	return turnScoreTotal;
    }
    public int getCurrentRollScore(){
    	return rollScore;
    }
    
    //Setter (Mutator) methods
    public void setCurrentTurnScoreTotal(int turnScoreTotal){
    	this.turnScoreTotal = turnScoreTotal;
    }
    public void setCurrentRollScore(int rollScore){
    	this.rollScore = rollScore;
    	}
    
    public void addToTurnScoreTotal(int i){
        turnScoreTotal += i;
    }
    
    public void removeFromTurnScoreTotal(int i){
        turnScoreTotal -= i;
    }
}