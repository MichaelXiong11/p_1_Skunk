package skunk.domain;

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class UserInterface{
    private static Controller controller;

    public static void main(String args[]){
        controller = new Controller();
        
        //Calls title() method; Text display of name of game
        title();
        
        //Calls playerSizePrompt() method; Asks user for how many players
        int numPlayers = playerSizePrompt();
        
        //Tells controller to set number of players
        controller.setNumPlayers(numPlayers);
        
        //To store the users choice
        int choice;
        
        //Flag for user's choice prompt;
        //true-keeps asking user to make a choice
        //false-stops asking user to make a choice
        boolean keepAsking = true;
        
        do{
            //Calls playerChoice() method; Asks user to make a choice
            choice = playerChoice();
            
            //Evaluates the player's choice; 1-Roll Dice 2-Stop Rolling Dice
            if(choice == 1) //User chose to roll dice
            {
                //Asks controller to roll dice
                controller.roll();
                
                //Display the dice values
                StdOut.println(controller.dieResults());
                StdOut.println("-----------------------");
                
                //Ask for skunkValue
                int skunkValue = controller.getSkunkValue();
                
                //Evaluate skunkValue
                switch(skunkValue){
                    case 0: //No skunks rolled
                        break;
                    case 1: //Rolled exactly 1 skunk
                        StdOut.println("Single SKUNK: Player 1's turn is over | 1 chip lost");
                        keepAsking = false;
                        break;
                    case 2: //Rolled 1 skunk and a deuce
                        StdOut.println("Duece SKUNK: Player 1's turn is over | 2 chips lost");
                        keepAsking = false;
                        break;
                    case 3: //Rolled 2 skunks
                        StdOut.println("Double SKUNK: Player 1's turn is over | 4 chips lost");
                        keepAsking = false;
                        break;
                }
            }
            else if(choice == 2) //User chose to stop rolling
            {
            	StdOut.println("Player 1 ends turn");
            	
                
                //Called doneRolling() method; Told controller to stop rolling
                controller.doneRolling();
                StdOut.println("Turn Score: " + controller.getPlayerScore() + " | 50 Kitty Chips Left");
                keepAsking = false;
            }
        }while(keepAsking);
    }
    
    /**
     * Displays Title of Game
     */
    private static void title(){
        StdOut.println("SKUNK GAME");
        StdOut.println("=================================");
    }
    
    /**
     * Method that asks user how many players are there for the game
     * @return int The number of players for the game
     */
    private static int playerSizePrompt(){
    	//Stores user's input
        String input;
        int numPlayers = 0;
        
        boolean flag = true;
        
        do{
        	//Ask user for the number of players
            StdOut.print("Enter the number of players: ");
            input = StdIn.readLine();
            
            //Check for invalid inputs such as letters, symbols, and negative numbers
            try{
                numPlayers = Integer.parseInt(input);
                if(numPlayers <= 0){
                    StdOut.println("INVALID INPUT");
                }
                else{
                    flag = false;
                }
            }catch(NumberFormatException e){
                StdOut.println("INVALID INPUT");
            }
        }while(flag);
        
        
        StdOut.println();
        return numPlayers;
    }
    
    /**
     * Method that asks user to make a choice
     * 1-Roll Dice | 2-Stop rolling dice
     * @return int The choice that the player made
     */
    private static int playerChoice(){
        String input;
        boolean flag = true;
        int choice = 0;
        
        do{
        	//Ask users for their choice
            StdOut.println("Player " + (controller.getCurrentPlayer() + 1) + ": 1-Roll Dice | 2-Stop Rolling ");
            input = StdIn.readLine();
            
            //Check for invalid inputs such as letters, symbols, and negative numbers
            try{
                choice = Integer.parseInt(input);
                if(choice <= 0 || choice > 2){
                    StdOut.println("INVALID INPUT");
                }
                else{
                    flag = false;
                }
            }catch(NumberFormatException e){
                StdOut.println("INVALID INPUT");
            }
        }while(flag);
        
        return choice;
    }
}