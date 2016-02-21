import java.util.Scanner;
import java.io.*;

public class Blackjack{
    public static void main(String[] args) throws IOException {
	Scanner keyboard = new Scanner(System.in);

	System.out.println("Welcome to Infinite BlackJack!\n");
	System.out.print("Enter name: ");
	String name = keyboard.nextLine();
	Player p = new Player(name);
	p.loadFile();
	System.out.printf("Name: %20s\n", name);
	System.out.printf("Total Hands: %13d\n", p.getHandsPlayed());
	System.out.printf("Hands Won: %15d\n", p.getHandsWon());
	System.out.printf("Money: %19.2f\n", p.getMoney());
    }
}
	
