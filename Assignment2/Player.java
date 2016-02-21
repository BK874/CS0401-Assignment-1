import java.util.Scanner;
import java.io.*;

public class Player{
    private String name;
    private double money;
    private int handsPlayed;
    private int handsWon;
    private File file;

    public Player(String x){
	name = x + ".txt";
	money = 100.00;
	handsPlayed = 0;
	handsWon = 0;
	file = new File(name);
    }

    public double getMoney(){
	return money;
    }
    
    public int getHandsPlayed(){
	return handsPlayed;
    }
   
    public int getHandsWon(){
	return handsWon;
    }
    
    public void changeMoney(double amount){
	money += amount;
    }

    public void changeHandsPlayed(int amount){
	handsPlayed += amount;
    }

    public void changeHandsWon(int amount){
	handsWon += amount;
    }
 
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
    
}
