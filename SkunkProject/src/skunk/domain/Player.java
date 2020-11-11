package skunk.domain;

public class Player{
    private int score;
    
    /**
     * Default Constructor
     */
    public Player(){
        score = 0;
    }

    /**
     * Paramaterized Constructor (Overloading)
     */
    public Player(int s){
        score = s;
    }
    
    public int getScore(){
    	return score;
    }
    
    public void setScore(int s){
    	score = s;
    }
    
    public void addToScore(int s){
        score += s;
    }
    
    public void removeFromScore(int s){
        score -= s;
    }
}