package skunk.domain;

public class Player{
	
    //Name of the player
    private String name;
    
    //The player's score
    private int score;
    
    //The player's kittyScore
    private int kittyScore;

    public Player(String name){
        this.name = name;
        this.score = 0;
        this.kittyScore = 50;
    }
    
    public Player(String name, int score, int kittyScore){
        this.name = name;
        this.score = score;
        this.kittyScore = kittyScore;
    }
    
    //Getter (Accessor) methods
    public String getName(){
    	return name;
    }
    public int getScore(){
    	return score;
    }
    public int getKittyScore(){
    	return kittyScore;
    }
    
    //Setter (Mutator) methods
    public void setName(String name){
    	this.name = name;
    }
    public void setScore(int score){
    	this.score = score;
    }
    public void setKittyScore(int kittyScore){
    	this.kittyScore = kittyScore;
    }
    
    /**
     * Method that increase the player's score
     * @param int Amount to increase the player's score by
     */
    public void increaseScore(int s){
        this.score += s;
    }
    
    /**
     * Method that decreases the player's score
     * Does not go below 0
     * @param int Amount to decrease the player's score by
     */
    public void decreaseScore(int s){
        this.score -= s;
        
        if(this.score < 0){
            this.score = 0;
        }
    }
    
    /**
     * Method that increases the player's kittyScore
     * @param int Amount to increase kittyScore by
     */
    public void increaseKittyScore(int k){
        this.kittyScore += k;
    }
    
    /**
     * Method that decreases the player's kittyScore
     * Does not go below 0
     * @param int Amount to decrease kittyScore by
     */
    public void decreaseKittyScore(int k){
        this.kittyScore -= k;
        
        if(this.kittyScore < 0){
            this.kittyScore = 0;
        }
    }
    
    /**
     * toString method
     */
    public String toString(){
        String s = name + " | Score: " + score + " | KittyScore: " + kittyScore;
        
        return s;
    }
}