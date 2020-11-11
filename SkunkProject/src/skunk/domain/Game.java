package skunk.domain;

import java.util.ArrayList;

public class Game{
    private ArrayList<Player> player = new ArrayList<Player>();
    private Dice dice;
    private Roll roll;
    private Turn turn;
    private int currentPlayer;
    private int numPlayers;
    private int penaltyPoints;
    private int skunkValue;
    
    /**
     * Constructor for objects of class Game
     */
    public Game(){
        this.dice = new Dice();
        this.turn = new Turn();
        penaltyPoints = 0;
        currentPlayer = 0;
        skunkValue = 0;
    }
    
    //Getter (Accessor) methods
    public int getCurrentPlayer(){
    	return currentPlayer;
    }
    public int getNumPlayers(){
    	return numPlayers;
    }
    public int getPenaltyPoints(){
    	return penaltyPoints;
    }
    public int getSkunkValue(){
    	return skunkValue;
    }
    public Dice getDice(){
    	return dice;
    }
    
    /**
     * Method that creates player objects (Number of people playing the game)
     */
    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
        for(int i = 0; i < this.numPlayers; i++){
            //Add a player (player starts with 0 points)
            player.add(new Player(0));
        }
    }
    
    /**
     * Method that Rolls the dice
     */
    public void roll(){
        //Roll the dice
        dice.roll();
        
        //Check for skunks
        roll = new Roll(dice.getDie1().getValue(), dice.getDie2().getValue());
        if(roll.isSkunk1() == true) {//Rolling only one skunk
            player.get(currentPlayer).removeFromScore(1);
            ++penaltyPoints;
            skunkValue = 1;
            nextPlayer();
            return;
        }
        else if(roll.isSkunk2() == true) { //Rolling a skunk AND a deuce
        
            player.get(currentPlayer).removeFromScore(2);
            penaltyPoints += 2;
            skunkValue = 2;
            nextPlayer();
            return;
        }
        else if(roll.isSkunk3() == true) { //Rolling two skunks
        
            player.get(currentPlayer).removeFromScore(4);
            penaltyPoints += 4;
            player.get(currentPlayer).setScore(0);
            skunkValue = 3;
            nextPlayer();
            return;
        }
        
        skunkValue = 0;
        turn.addToTurnScoreTotal(dice.getDiceTotal());
    }
    
    /**
     * Method that performs certain actions when a player is done rolling
     */
    public void doneRolling(){
        //Adds the player's turn score to their overall score
        player.get(currentPlayer).addToScore(turn.getCurrentTurnScoreTotal());
        
        //Reset the roll score and turn score to 0
        turn.setCurrentRollScore(0);
        turn.setCurrentTurnScoreTotal(0);
    }
    
    
    /**
     * Method that moves on to the next player
     */
    private void nextPlayer(){
        currentPlayer++;
        if(currentPlayer >= numPlayers)
        {
            currentPlayer = 0;
        }
    }
    
    /**
     * Method that gets the current player's score
     * @returns int The player's overall score
     */
    public int getPlayerScore(){
        return player.get(currentPlayer).getScore();
    }
}
