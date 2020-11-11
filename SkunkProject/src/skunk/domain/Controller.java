package skunk.domain;

public class Controller
{
    //Game object
    private Game game;
    
    /**
     * Constructor for objects of class Controller
     */
    public Controller(){
        game = new Game();
    }
    
    public void setNumPlayers(int numPlayers){
         game.setNumPlayers(numPlayers);
    }
    
    public void roll(){
        game.roll();
    }
    
    public int getCurrentPlayer(){
        return game.getCurrentPlayer();
    }
    
    public int getSkunkValue(){
        return game.getSkunkValue();
    }
    
    public void doneRolling(){
        game.doneRolling();
    }
    
    public int getPlayerScore(){
    	return game.getPlayerScore();
    }
    
    public String dieResults(){
        return (game.getDice().toString());
    }
}