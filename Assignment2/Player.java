import java.util.Scanner;
import java.io.*;

public class Player{

    // Declare variables for storing the player's name,
    // money, hands played and won, and the variable for
    // the player's .txt file.

    private String name;
    private double money;
    private int handsPlayed;
    private int handsWon;
    private File file;

    // The constructor accepts the player's name as arguements 
    // and uses it to name the .txt file. The player's money
    // is auto set to 100 and both hands variables are set to 0.
    // The file is initialized using the name variable

    public Player(String x){
	name = x + ".txt";
	money = 100.00;
	handsPlayed = 0;
	handsWon = 0;
	file = new File(name);
    }

    // Method for returning the player's current amount of money

    public double getMoney(){
	return money;
    }

    // Method for returning the amount of hands the player has played
    
    public int getHandsPlayed(){
	return handsPlayed;
    }

    // Method for returning the number of hands the player has won
   
    public int getHandsWon(){
	return handsWon;
    }
    
    // Method for changing the amount of money the player has -
    // it accepts the amount as an argument and adds it to the 
    // money variable so if the player is losing money the amount 
    // passed should be negative

    public void changeMoney(double amount){
	money += amount;
    }

    // Method for increasing the number of hands played by the 
    // player.

    public void changeHandsPlayed(int amount){
	handsPlayed += amount;
    }
    
    // Method for increasing the number of hands won by the player.

    public void changeHandsWon(int amount){
	handsWon += amount;
    }

    // Method for loading or creating the player's file. If a
    // file with the player's name exists than the money,
    // handsPlayed and handsWon are set to the values in the 
    // file. If a file with the player's name does not exist
    // then one is created and the initial values of 100, 0, 0
    // and written to it. It is a boolean method so it returns true 
    // when a file is created and false when one is loaded - this tells
    // Blackjack.java which greeting to display.
 
    public boolean loadFile() throws IOException{
	if (!file.exists()){
	    PrintWriter outputFile = new PrintWriter(file);
	    outputFile.println(money);
	    outputFile.println(handsPlayed);
	    outputFile.println(handsWon);
	    outputFile.close();
	    
	    return true;
	}
	else{
	    Scanner inputFile = new Scanner(file);
	    money = inputFile.nextDouble();
	    handsPlayed = inputFile.nextInt();
	    handsWon = inputFile.nextInt();
	    inputFile.close();
	    return false;
	}
    }

    // Method for writing changes in the money, handsPlayed,
    // and handsWon variables.

    public void writeFile() throws IOException{
	PrintWriter outputFile = new PrintWriter(file);
	outputFile.println(money);
	outputFile.println(handsPlayed);
	outputFile.println(handsWon);
	outputFile.close();
    }
    
}
