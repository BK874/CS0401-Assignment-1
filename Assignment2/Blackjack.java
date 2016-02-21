import java.util.Scanner;
import java.io.*;

public class Blackjack{
    public static void main(String[] args) throws IOException {
	Scanner keyboard = new Scanner(System.in);
	String name;
	String play;
	System.out.println("Welcome to Infinite BlackJack!\n");
	System.out.print("Enter name: ");
	name = keyboard.nextLine();
	Player p = new Player(name);

	if (p.loadFile()){
	    System.out.println("Have fun on your first time playing Infinite Blackjack!\n");
	}
	else{
	    System.out.println("Welcome back, " + name + "!\n");
	}

	printInfo(name, p);
	play = contPlay();

	while (play.equals("Y")){
	    double bet;
	    
	    bet = betting(p.getMoney());
	    System.out.println("PLAYER DEAL");

	    
	    


	    play = contPlay();
	}
    }


    public static void printInfo(String name, Player p){
	System.out.printf("Name: %20s\n", name);
	System.out.printf("Total Hands: %13d\n", p.getHandsPlayed());
	System.out.printf("Hands Won: %15d\n", p.getHandsWon());
	System.out.printf("Money: %19.2f\n\n", p.getMoney());
	
    }


    public static String contPlay(){
	Scanner keyboard = new Scanner(System.in);
	System.out.print("Play a hand? (Y/N) ");
	while (!(keyboard.hasNext("Y")) && !(keyboard.hasNext("N"))){
	    System.out.print("Play a hand? (Y/N) ");
	    keyboard.next();
	}
	String play = keyboard.nextLine();
	
	return play;
    }


    public static double betting(double amount){
	Scanner keyboard = new Scanner(System.in);
	double bet;
	do{
	    System.out.print("Enter amount to bet: ");
	    while (!keyboard.hasNextDouble()){
		keyboard.next();
		System.out.print("Enter amount to bet: ");
	    }
	    bet = keyboard.nextDouble();
	} while (bet < 0 || bet > amount);
	
	return bet;
    }
}
	
