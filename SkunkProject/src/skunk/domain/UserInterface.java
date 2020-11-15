package skunk.domain;

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class UserInterface{
    private static Controller controller;
    private static final int ROLL = 1;
    private static final int DONEROLLING = 2;
    private static final int QUIT = 3;
    
    //Stores a player's turn history
    private static ArrayList<String> turnHistory = new ArrayList<String>();
    
    public static void main(String args[]){
        controller = new Controller();
        titleText();
        
        //Set game for 1 player  
        controller.addPlayer("Player 1");
        
        //Start the game
        controller.start();
        
        //To check if the game is over
        boolean isGameOver = controller.isGameOver();
        
        //Call playState() method; The playState for a player
        playState();
            
        //Go to the next player
        controller.nextPlayer();
            
        //Check if the game is over (Winner was decided)
         isGameOver = controller.isGameOver();
       
        
        //If game is over, display winner
        if(isGameOver == true){
            StdOut.println(" --GAME OVER");
            
            //Get Winner
            String winner = controller.getWinner();
            StdOut.println(" --WINNER: " + winner);
        }      
    }
    
    /**
     * Method that displays the game title
     */
    private static void titleText(){
        StdOut.println("Welcome to the SKUNK GAME");
        StdOut.println("==============================");
    }
    
    /**
     * Method that asks the user to make choice for the game
     * 1-Roll Dice 2-Stop Rolling 3-Help 4-Quit
     * @return int Returns the choice the player made
     */
    private static int makeChoice(){
        String input;
        int choice = 0;
        boolean keepAsking = false;
        String currentPlayer = controller.getCurrentPlayerName();
        
        //Ask a player to make a choice
        do{
            StdOut.println(currentPlayer + ": 1-Roll Dice | 2-Stop Rolling | 3-Quit App");
            input = StdIn.readLine();
            
            //Check for invalid inputs
            try{
                choice = Integer.parseInt(input);
                
                //Out of range check
                if(choice <= 0 || choice > QUIT){
                    StdOut.println("INVALID INPUT");
                }
                else{
                    keepAsking = false;
                }
            }catch(NumberFormatException e){ 
                StdOut.println("INVALID INPUT");
            }
        }while(keepAsking);
        
        return choice;
    }
    
    /**
     * Method that handles the display while a player is playing the game
     */
    private static void playState(){
        boolean keepPlaying = true;
        int choice;
        String currentPlayer = controller.getCurrentPlayerName();
        while(keepPlaying == true){
            choice = makeChoice();
            
            if(choice == ROLL) {
                //Roll the dice
                controller.roll();
                
                //Get the dice values from the roll
                String diceValues = controller.getDiceValues();
                
                //Get the turn score
                int turnScore = controller.getTurnScore();
                
                //Display the player's dice roll results
                StdOut.println("  " + currentPlayer + " | " + diceValues + " | TurnScore: " + turnScore);
                
                //Store the results into turn history
                turnHistory.add("  Roll " + (turnHistory.size() + 1) + " | " + diceValues + " | TurnScore: " + turnScore);
                
                //Check if a skunk was rolled
                int skunkValue = controller.getSkunkValue();
                
                // prints message if skunk was rolled
                skunkMessage(skunkValue);
                if(skunkValue != 0){
                    StdOut.println(" -------------------TURN IS OVER---------------------------");
                    
                    //Display results for the turn
                    turnResultsMessage();
                    keepPlaying = false;
                }
                StdOut.println("---------------------------------------------------------------------");
            }
            else if(choice == DONEROLLING) {
                controller.doneRolling();
                
                //Display results for the turn
                turnResultsMessage();
                StdOut.println("---------------------------------------------------------------------");
                keepPlaying = false;
            }
            else if(choice == QUIT){
                //Terminates Program
                System.exit(0);
            }
        }
    }
    
    
    /**
     * Method that displays a message if a skunk was rolled
     * @param int
     */
    private static void skunkMessage(int skunkValue){
        switch(skunkValue){
            case 1:
                StdOut.println(" --Rolled a Single Skunk | 1 Penalty Chip | Lose Turn Score");
                break;
            case 2:
                StdOut.println(" --Rolled a Duece Skunk | 2 Penalty Chips | Lose Turn Score");
                break;
            case 3:
                StdOut.println(" --Rolled a Double Skunk | 4 Penalty Chips | Lose ENTIRE Score");
                break;
        }
    }
    
    /**
     * Method that displays the turn result for a player
     */
    private static void turnResultsMessage(){
        //Print the results of the player's turn
        
        
        String currentPlayer = controller.getCurrentPlayerName();
        int playerScore = controller.getCurrentPlayerScore();
        int kittyScore = controller.getCurrentPlayerKittyScore();
        
        turnHistory.add("   Total Score: " + playerScore + " | Kitty Chips Lost: " + (kittyScore - 50));
        turnHistory.add("========================END=========================");
        
        StdOut.println("====================TURN SUMMARY====================");
        StdOut.println("KEEP PRESSING ENTER");
        StdOut.print("Player: " + currentPlayer);
        int index = 0;
        String input;
        while(true){
            input = StdIn.readLine();
            
            if(input.equals("")){
                StdOut.print(turnHistory.get(index));
                ++index;
            }
            
            if(index == turnHistory.size()){
                turnHistory.clear();
                break;
            }
        }
        StdOut.println();
    }
}