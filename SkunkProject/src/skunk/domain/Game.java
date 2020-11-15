package skunk.domain;

import java.util.ArrayList;

public class Game{
    //List of players playing the game
    private ArrayList<Player> player;
    
    //The current player rolling the dice
    private Player currentPlayer;
    
    //The number of players playing the game
    private int numPlayers;
    
    //To traverse player ArrayList
    private int currentPlayerIndex;
    
    //Dice object
    private Dice dice;
    
    //Turn object
    private Turn turn;
    
    //Roll object
    private Roll roll;
    
    //Player who initiated finalTurn (First to accumulate more than SCOREGOAL)
    private Player initiator;
    
    //Flag to see if it is the final turn for the players
    private boolean finalTurn;
    
    //Flag to see if game is over
    private boolean gameOver;
    
    //The player who wins
    private Player winner;
    
    //The score for the players to reach
    private final int SCOREGOAL = 100;
    /**
     * Default constructor for objects of class Game
     */
    public Game(){
       player = new ArrayList<Player>();
       currentPlayer = null;
       numPlayers = 0;
       currentPlayerIndex = 0;
       
       dice = new Dice();
       turn = new Turn();
       roll = new Roll();
       
       finalTurn = false;
       gameOver = false;
       winner = null;
    }
    
    //Getter (Accessor) methods
    public Player getCurrentPlayer(){
    	return currentPlayer;
    }
    public int getNumPlayers(){
    	return numPlayers;
    }
    public int getCurrentPlayerIndex(){
    	return currentPlayerIndex;
    }
    public Dice getDice(){
    	return dice;
    }
    public Turn getTurn(){
    	return turn;
    }
    public Roll getRoll(){
    	return roll;
    }
    public boolean isFinalTurn(){
    	return finalTurn;
    }
    public boolean isGameOver(){
    	return gameOver;
    }
    public Player getWinner(){
    	return winner;
    }
    
    /**
     * Method that adds a player to the game (ArrayList)
     * @param String Name of the player
     */
    public void addPlayer(String name){
        player.add(new Player(name));
        ++numPlayers;
    }
    
    /**
     * Method that starts  the game
     * Initiates who is going first
     */
    public void start(){
        if(numPlayers > 0){
            currentPlayerIndex = 0;
            currentPlayer = player.get(currentPlayerIndex);
        }
    }
    
    /**
     * Method that rolls the dice
     */
    public void roll(){
        //Roll the dice
        dice.roll();
        
        //Increase turnScore
        turn.increaseTurnScore(dice.getDiceSum());
        
        //Set the roll score
        turn.setRollScore(dice.getDiceSum());
    }
    
    /**
     * Evaluates the results of a dice roll
     */
    public int evaluateRoll(){
        int dieValue1 = dice.getDie1().getValue();
        int dieValue2 = dice.getDie2().getValue();
        
        roll.setDieValues(dieValue1, dieValue2);
        
        //Check if skunks were rolled
        if (roll.isSkunk1() == true) { //Rolling exactly 1 skunk
            turn.setTurnScore(0);
            player.get(currentPlayerIndex).increaseKittyScore(1);
            return 1;
        }
        else if(roll.isSkunk2() == true) {
        
            turn.setTurnScore(0);
            player.get(currentPlayerIndex).increaseKittyScore(2);
            return 2;
        }
        else if(roll.isSkunk3() == true) {
        
            turn.setTurnScore(0);
            player.get(currentPlayerIndex).increaseKittyScore(4);
            //player.get(currentPlayerIndex).setScore(0);
            return 3;
        }
        
        return 0;
    }
    
    /**
     * Method that adds up a player's turnScore to their overall score 
     * Should be called when a player chooses to stop rolling for that turn
     */
    public void doneRolling() {
    
        //Get the player's turnScore
        int turnScore = turn.getTurnScore();
        
        //Add the player's turnScore to their overall score
        player.get(currentPlayerIndex).increaseScore(turnScore);
        
        //Reset the turn score
        turn.setTurnScore(0);
        
        //Call initiateFinalTurn() method; check if a person has reached the score goal
        //if yes, initiate final turn
        initiateFinalTurn();
    }
    
    /**
     * Method that moves to the next player's turn
     */
    public void nextPlayer() {
    
        ++currentPlayerIndex;
        if(currentPlayerIndex >= numPlayers) {
        
            currentPlayerIndex = 0;
        }
        currentPlayer = player.get(currentPlayerIndex);
        
        //Check if game is over
        if(finalTurn == true && currentPlayer.equals(initiator)) {
        
            gameOver = true;
            
            //Find winner
            findWinner();
        }
    }
    
    /**
     * Method that initiates the final turn if a player has reached the score goal
     */
    private void initiateFinalTurn() {
    
        if(finalTurn == true) {
        
            return;
        }
        
        if(currentPlayer.getScore() >= SCOREGOAL) {
        
            finalTurn = true;
            
            initiator = currentPlayer;
        }
    }
    
    private void findWinner() {
    
        if(gameOver == false){
            return;
        }
        
        int highestScore = 0;
        for(int i = 0; i < numPlayers; i++){
            if(highestScore < player.get(i).getScore()){
                highestScore = player.get(i).getScore();
                winner = player.get(i);
            }
        }
        
        //Call collectKitty() method; Winner collects kitty Chips from other players
        collectKitty();
    }
    
    /**
     * Method that has the winner collect kitty points from the other players
     */
    private void collectKitty(){
        for(int i = 0; i < numPlayers; i++){
            Player tempPlayer = player.get(i);
            if(winner.equals(tempPlayer) == false){
                if(tempPlayer.getScore() == 0){
                    player.get(i).decreaseKittyScore(10);
                    winner.increaseKittyScore(10);
                }
                else{
                    player.get(i).decreaseKittyScore(5);
                    winner.increaseKittyScore(5);
                }
            }
        }
    }
}
