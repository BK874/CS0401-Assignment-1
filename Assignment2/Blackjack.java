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
	    int aCount = 0;
	    
	    bet = betting(p.getMoney());
	    System.out.println("PLAYER DEAL");
	    
	    Card c1 = new Card();
	    Card c2 = new Card();

	    if (c1.getValue().equals("Ace")){
		aCount ++;
	    }
	    if (c2.getValue().equals("Ace")){
		aCount ++;
	    }

	    System.out.print("Cards ");
	    c1.printInfo();
	    System.out.print(" ");
	    c2.printInfo();
	    System.out.println();
	    System.out.println("Score: " + getScore(c1.calcScore() + c2.calcScore(), aCount));


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

    private static int getScore(int points, int numAces) {
	int score = 0;

	// If there are no aces, or if score is less than 21 with aces at
	// 11 points each, then the actual score is just
	// equal to the number of points.
	
	if (numAces == 0 || points <= 21) {
	    score = points;
	} else {

	    // Otherwise, we need to check what is the BEST score is,
	    // and that gets a little complicated.  We set a placeholder
	    // -1 for best score, and a placeholder potential score.
	    // We will keep track of what the best score is, and try
	    // different potential scores against it.  Whatever is
	    // highest without going over 21 will win as the best score.
	    
	    int bestScore = -1;
	    int potentialScore = points;

	    // Loop through _number of aces_ times.  Each time, try an
	    // increasing number of aces as a 1 value instead of an
	    // 11 value (thus, subtract 10 * j from the total points
	    // value, which assumes all Aces are equal to 11 points).
	    
	    for (int j = 0; j <= numAces; j++) {
		potentialScore = (points - (10 * j));

		// For each iteration, if the potential score is
		// better than the already-best score, but it is NOT
		// over 21 (causing us to bust), then the
		// potential score should count as our new best score.
		
		if (potentialScore > bestScore && potentialScore <= 21) {
		    bestScore = potentialScore;
		}
	    }

	    // We could have busted even when all of our aces were set
	    // to one point.  In this case, we might never have gotten a
	    // valid "best" score.  But our best potential score is the closest
	    // to a best score we have, so we will replace our placeholder -1
	    // best with the best potential score we got.

	    // Otherwise, just set the score to the best score.
	    
	    if (bestScore == -1) {
		score = potentialScore;
	    } else {
		score = bestScore;
	    }
	}
	return score;
    }
 }
	
