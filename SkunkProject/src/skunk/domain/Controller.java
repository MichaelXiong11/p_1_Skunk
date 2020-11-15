package skunk.domain;

public class Controller{
    private Game game;

    /**
     * Constructor for objects of class Controller
     */
    public Controller(){
        game = new Game();
    }
    
    /**
     * Method that adds a player to the game
     * @param String The name of the player
     */
    public void addPlayer(String name){
        game.addPlayer(name);
    }
    
    public void start(){
        game.start();
    }
    
    public void roll(){
        game.roll();
    }
    
    public void doneRolling(){
        game.doneRolling();
    }
       
    public int getSkunkValue(){
        int skunkValue = game.evaluateRoll();
        
        return skunkValue;
    }
    
    public String getCurrentPlayerName(){
    	return (game.getCurrentPlayer().getName());
    }
    public int getCurrentPlayerScore(){
    	return (game.getCurrentPlayer().getScore());
    }
    public int getCurrentPlayerKittyScore(){
    	return (game.getCurrentPlayer().getKittyScore());
    }
    public String getDiceValues(){
    	return (game.getDice().toString());
    }
    public int getTurnScore(){
    	return (game.getTurn().getTurnScore());
    }
    public boolean isGameOver(){
    	return game.isGameOver();
    }
    
    public void nextPlayer(){
    	game.nextPlayer();
    }
    public String getWinner(){
    	return game.getWinner().toString();
    }
}