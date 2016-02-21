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

	//playLoop:
	while (play.equals("Y") || play.equals("y") ||play.equals("Yes") || play.equals("yes")){
	    double bet;
	    int score;
	    int dScore;
	    int aCount = 0;
	    String option;
	    
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
	    
	    score = c1.calcScore() + c2.calcScore();
	    score = getScore(score, aCount);
	    
	    System.out.print("Cards: ");
	    c1.printInfo();
	    System.out.print(" ");
	    c2.printInfo();
	    System.out.println();
	    System.out.println("Score: " +  score);
	    
	    option = hitStay();
	    // hitLoop:
	    while (option.equals("H") || option.equals("h") || option.equals("hit")){
		Card cN = new Card();
		
		if (cN.getValue().equals("Ace")){
		    aCount++;
		}
		
		score = score + cN.calcScore();
		score = getScore(score, aCount);

		System.out.print("Card dealt: ");
		cN.printInfo();
		System.out.println();
		System.out.println("Score: " + score);
		
		if (score > 21){
		    System.out.println("Player busted!");
		    System.out.println("You lost $" + bet + "!\n");
		    p.changeMoney(-bet);
		    p.changeHandsPlayed(1);
		    break;
		}
		else{
		    option = hitStay();
		}
	    }
	    
	    if (score <= 21){
		System.out.println("DEALER DEAL");
		dScore = dealer();
		
		System.out.println("Player Score: " + score);
		System.out.println("Dealer Score: " + dScore);
		
		if (score == 21 && dScore != 21){
		    System.out.println("Player wins with Blackjack!");
		    System.out.println("You won $" + 1.5 * bet + "!");
		    
		    p.changeMoney(1.5 * bet);
		    p.changeHandsPlayed(1);
		    p.changeHandsWon(1);
		}
		else if (dScore > 21){
		    System.out.println("Dealer busted!");
		    System.out.println("You won $" + bet + "!");
		    
		    p.changeMoney(bet);
		    p.changeHandsPlayed(1);
		    p.changeHandsWon(1);
		}
		else if (score > dScore){
		    System.out.println("Player won!");
		    System.out.println("You won $" + bet + "!");
		    
		    p.changeMoney(bet);
		    p.changeHandsPlayed(1);
		    p.changeHandsWon(1);
		    
		}
		else if (dScore > score){
		    System.out.println("Dealer won!");
		    System.out.println("You lost $" + bet + "!");

		    p.changeMoney(-bet);
		    p.changeHandsPlayed(1);
		}
		else if (score == dScore){
		    System.out.println("Push!");
		    
		    p.changeHandsPlayed(1);
		}
		    
	    }
	    
	    if (p.getMoney() <= 0){
		System.out.println("You have no more money to bet!");
		break;
	    }
	    
	    printInfo(name, p);
	    play = contPlay();
	}
	
	p.writeFile();
	System.out.println("Thank you for playing Infinite Blackjack!");
    }


    private static void printInfo(String name, Player p){
	System.out.printf("Name: %20s\n", name);
	System.out.printf("Total Hands: %13d\n", p.getHandsPlayed());
	System.out.printf("Hands Won: %15d\n", p.getHandsWon());
	System.out.printf("Money: %19.2f\n\n", p.getMoney());
	
    }


    private static String contPlay(){
	Scanner keyboard = new Scanner(System.in);
	String play;
	do{
	    System.out.print("Play a hand? (Y/N) ");
	    while(!keyboard.hasNextLine()){
		keyboard.next();
		System.out.print("Play a hand? (Y/N) ");
	    }
	    play = keyboard.nextLine();
	}while (!play.equals("Y") && !play.equals("N") && !play.equals("y") &&
		!play.equals("n") && !play.equals("Yes") && !play.equals("No") &&
		!play.equals("yes") && !play.equals("no"));
	
	return play;
    }


    private static double betting(double amount){
	Scanner keyboard = new Scanner(System.in);
	double bet;
	do{
	    System.out.print("Enter amount to bet: ");
	    while (!keyboard.hasNextDouble()){
		keyboard.next();
		System.out.println("Please enter a value in a valid form (e.g. 4.50)\n");
		System.out.print("Enter amount to bet: ");
	    }
	    bet = keyboard.nextDouble();
	} while (bet < 0.01 || bet > amount);
	
	return bet;
    }

    
    private static String hitStay(){
	Scanner keyboard = new Scanner(System.in);
	String option;
	do{
	    System.out.print("[H]it or [S]tay? ");
	    while(!keyboard.hasNextLine()){
		keyboard.next();
		System.out.print("[H]it or [S]tay? ");
	    }
	    option = keyboard.nextLine();
	}while (!option.equals("H") && !option.equals("S") && !option.equals("h") &&
		!option.equals("s") && !option.equals("Hit") && !option.equals("Stay") &&
		!option.equals("hit") && !option.equals("stay"));
	
	return option;
    }

    
    private static int dealer(){
	int score;
	int aCount = 0;
	Card d1 = new Card();
	Card d2 = new Card();
	
	if (d1.getValue().equals("Ace")){
	    aCount++;
	}
	if (d2.getValue().equals("Ace")){
	    aCount++;
	}
	
	System.out.print("Cards: ");
	d1.printInfo();
	System.out.print(" ");
	d2.printInfo();
	System.out.println();
	
	score = d1.calcScore() + d2.calcScore();
	score = getScore(score, aCount);
	
	System.out.println("Score: " + score);
	while (score < 22){
	    if (score >= 18 || score == 21){
		System.out.println("Stay!");
	    
		break;
	    }
	    else if (score == 17 && aCount == 0){
		System.out.println("Stay!");
	    
		break;
	    }
	    else if (score <= 17){
		System.out.println("Hit!");
		Card dN = new Card();
		
		if (dN.getValue().equals("Ace")){
		    aCount++;
		}
		
		System.out.print("Cards dealt: ");
		dN.printInfo();
		System.out.println();
		
		score += dN.calcScore();
		score = getScore(score, aCount);
		
		System.out.println("Score: " + score);
	    }
	}
	return score;
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
	
