package skunk.domain;

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class UserInterface{
    private static Controller controller;
    private static final int ROLL = 1;
    private static final int DONEROLLING = 2;
    private static final int HELP = 3;
    private static final int QUIT = 4;
    private static int chipsLost = 0;
    private static ArrayList<String> helpText = new ArrayList<String>();
    private static ArrayList<String> turnHistory = new ArrayList<String>();
    
    public static void main(String args[]){
        controller = new Controller();
        storeHelpList();
        titleText();
        
        //Ask for number of players
        int numPlayers = howManyPlayers();
        
        //Ask for player names and add them to game
        for(int i = 0; i < numPlayers; i++){
            StdOut.println("Player " + (i+1) + " Name: ");
            String name = StdIn.readLine();
            
            //Add player to game
            controller.addPlayer(name);
        }
        
        //Start the game
        controller.start();
        
        //To check if the game is over
        boolean isGameOver = controller.isGameOver();
        while(isGameOver == false){    
        	
        	//Call playState() method; The playState for a player
        	playState();
            
        	//Go to the next player
        	controller.nextPlayer();
            
        	//Check if the game is over (Winner was decided)
        	isGameOver = controller.isGameOver();
        }
        
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
     * Method that asks the user how many players
     * @return int Returns the number of players for the game
     */
    private static int howManyPlayers(){
        String input;
        int numPlayers = 0;
        boolean keepAsking = true;
        
        //Ask user for number of players
        do{
            StdOut.println("How many Players: ");
            input = StdIn.readLine();
            try{
                numPlayers = Integer.parseInt(input);
                
                //Negative numbers check
                if(numPlayers <= 0){
                    StdOut.println("INVALID INPUT");
                }
                else{
                    keepAsking = false;
                }
            }catch(NumberFormatException e){ //Non-number check
                StdOut.println("Invalid Input");
            }
        }while(keepAsking);
        
        return numPlayers;
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
        	StdOut.println(currentPlayer + ": 1-Roll Dice | 2-Stop Rolling | 3-Help | 4-Quit App");
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
                
                // Call skunkPrompt() method; prints message if skunk was rolled
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
            else if(choice == HELP){
                //Call help() method
                help();
            }
            else if(choice == QUIT){
                //Terminates Program
                System.exit(0);
            }
        }
    }
    
    private static void storeHelpList()
    {
        helpText.add("=================RULES=================");
        helpText.add("Objective: To accumulate 100 points or more");
        helpText.add(" -Each player starts with 0 points and 50 kitty chips");
        helpText.add(" -A score is made by rolling two dices and the combining values become the score");
        helpText.add(" -After a roll, a player can choose to roll again to increase score");
        helpText.add(" -Player can also choose to stop rolling and pass the dice to the next player");
        helpText.add(" -First player to accumulate a total of 100 points can continue to roll");
        helpText.add("  for as many additional points above 100 to secure a win.");
        helpText.add(" -Each succeeding player receives one more chance to beat the highest score to win");
        helpText.add(" -The winner of each game collects all the penalty kitty chips and in addition to five");
        helpText.add("  chips from each losing player or 10 chips from any player without a score");
        helpText.add(" -Rolling any of the three skunks ends your turn");
        helpText.add("Penalties");
        helpText.add(" -Rolling a single skunk results in");
        helpText.add("    *loss of points for the turn");
        helpText.add("    *penalty of 1 chip added to kitty");
        helpText.add("    *loss of turn");
        helpText.add(" -Rolling a duece skunk results in");
        helpText.add("    *loss points for the turn");
        helpText.add("    *penalty of 2 chips added to kitty");
        helpText.add("    *loss of turn");
        helpText.add(" -Rolling a double skunk results in");
        helpText.add("    *loss of ENTIRE accumulated points");
        helpText.add("    *penalty of 4 chips to kitty");
        helpText.add("    *loss of turn");
        helpText.add("==================END OF DIRECTIONS==================");
    }
    
    private static void help()
    {
        StdOut.println("KEEP PRESSING ENTER");
        int index = 0;
        int end = helpText.size();
        String input;
        while(true)
        {
            input = StdIn.readLine();
            
            if(input.equals(""))
            {
                StdOut.print(helpText.get(index));
                ++index;
            }
            
            if(index == end)
            {
                break;
            }
        }
        StdOut.println();
        StdOut.println();
    }
    
    /**
     * Method that displays a message if a skunk was rolled
     * @param int
     */
    private static void skunkMessage(int skunkValue){
        switch(skunkValue){
            case 1:
                StdOut.println(" --Rolled a Single Skunk | 1 Penalty Chip | Lose Turn Score");
                chipsLost = 1;
                break;
            case 2:
                StdOut.println(" --Rolled a Duece Skunk | 2 Penalty Chips | Lose Turn Score");
                chipsLost = 2;
                break;
            case 3:
                StdOut.println(" --Rolled a Double Skunk | 4 Penalty Chips | Lose ENTIRE Score");
                chipsLost = 4;
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
        
        turnHistory.add("   Total Score: " + playerScore + " | Kitty Chips: " + kittyScore + " | Kitty Chips Lost: " + chipsLost);
        chipsLost = 0;
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