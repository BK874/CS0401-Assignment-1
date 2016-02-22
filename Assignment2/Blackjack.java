import java.util.Scanner;
import java.io.*;

public class Blackjack{

    public static void main(String[] args) throws IOException {

	// Create a Scanner for reading input and declare the variables name 
	// and play for storing the user's name and his/her choice of playing 
	// a round.

	Scanner keyboard = new Scanner(System.in);
	String name;
	String play;
	
	// Display the greeting and prompt the user for their name, storing it
	// in the name variable. Then greet the user by name.

	System.out.println("Welcome to Infinite BlackJack!\n");
	System.out.print("Enter name: ");
	name = keyboard.nextLine();
	System.out.println("Welcome, " + name + "!");

	// Create a new Player object using the user's name
	
	Player p = new Player(name);

	// Call the Player.loadFile() method. If it returns true (it created
	// a new file) then display a first-time greeting. If it returns false
	// (it loaded an existing file) then welcome the user back.

	if (p.loadFile()){
	    System.out.println("Have fun on your first time playing Infinite Blackjack!\n");
	}
	else{
	    System.out.println("Welcome back, " + name + "!\n");
	}

	// Use the printInfo method to print the user's name, money, and number
	// of hands played and won.

	printInfo(name, p);

	// Call the contPlay() method to ask the user if they will play a round
	// and set the play variable to the result.

	play = contPlay();

	// The round loop

	while (play.equals("Y") || play.equals("y") ||play.equals("Yes") || play.equals("yes")){
	    
	    // Declare the variables for the user's bet, score, dealer's score, 
	    // number of aces, and their choice for hitting/staying

	    double bet;
	    int score;
	    int dScore;
	    int aCount = 0;
	    String option;

	    // Call the betting method and set the bet variable to its result.
	    // Print the indicator that it is the player's turn.
	    
	    bet = betting(p.getMoney());
	    System.out.println("PLAYER DEAL");
	    
	    // Create two new Card object and determine if they are aces,
	    // incrementing the ace counter if one or both of them are.

	    Card c1 = new Card();
	    Card c2 = new Card();

	    if (c1.getValue().equals("Ace")){
		aCount ++;
	    }
	    if (c2.getValue().equals("Ace")){
		aCount ++;
	    }

	    // Calculate the user's current score by getting each 
	    // individual card's point value and then calling the 
	    // get score method using the result and the ace counter.
	    
	    score = c1.calcScore() + c2.calcScore();
	    score = getScore(score, aCount);

	    // Display the user's initial two cards and his/her current
	    // score.
	    
	    System.out.print("Cards: ");
	    c1.printInfo();
	    System.out.print(" ");
	    c2.printInfo();
	    System.out.println();
	    System.out.println("Score: " +  score);
	    
	    // Call the hitStay() method to ask the user if they will
	    // hit or stay, storing the result in the option variable

	    option = hitStay();

	    // hitLoop:
	    while (option.equals("H") || option.equals("h") || option.equals("hit")){

		// If they hit, create a new Card object and check if it 
		// is an ace and increment appropriately.

		Card cN = new Card();
		
		if (cN.getValue().equals("Ace")){
		    aCount++;
		}

		// Calculate the user's new score by calling 
		// the getScore() method on their current score
		// plus the new card's point value.
		
		score = score + cN.calcScore();
		score = getScore(score, aCount);

		// Display the newly dealt card and the user's 
		// new score.

		System.out.print("Card dealt: ");
		cN.printInfo();
		System.out.println();
		System.out.println("Score: " + score);

		// If the player's score is over 21 they have "busted."
		// Display the appropriate message along with how much 
		// they have lost (the value of the bet variable).
		// Use the appropriate Player methods to change the user's
		// money and number of hand's played appropriately. 
		// Break out of the Hit loop.
		
		if (score > 21){
		    System.out.println("Player busted!");
		    System.out.println("You lost $" + bet + "!\n");
		    p.changeMoney(-bet);
		    p.changeHandsPlayed(1);
		    break;
		}

		// If the user's score is under 21 then prompt them 
		// to hit or stay again.
		
		else{
		    option = hitStay();
		}
	    }

	    // Once they stay it is the dealer's turn.
	    // Display the appropriate message and call the 
	    // dealer() method to calculate the dealer's turn.

	    if (score <= 21){
		System.out.println("DEALER DEAL");
		dScore = dealer();

		// After the dealer complete's their turn, display
		// the player's score and dealer's score.
		
		System.out.println("Player Score: " + score);
		System.out.println("Dealer Score: " + dScore);
		
		// Determine the results of the turn.

		// If the player's score is 21 and the dealer's
		// is not, they won with Blackjack They recieve
		// 1.5 * their bet. Change the player's money 
		// and hands won/played appropriately.

		if (score == 21 && dScore != 21){
		    System.out.println("Player wins with Blackjack!");
		    System.out.println("You won $" + 1.5 * bet + "!");
		    
		    p.changeMoney(1.5 * bet);
		    p.changeHandsPlayed(1);
		    p.changeHandsWon(1);
		}

		// If the dealer's score is over 21 and the player's 
		// is not, the player wins and receives the amount
		// they bet. Change the appropriate variables.
		
		else if (dScore > 21){
		    System.out.println("Dealer busted!");
		    System.out.println("You won $" + bet + "!");
		    
		    p.changeMoney(bet);
		    p.changeHandsPlayed(1);
		    p.changeHandsWon(1);
		}

		// If neither the dealer nor the player went over 
		// 21 and the player's score is greater, then the 
		// player wins their bet. Change the appropriate variables.
		
		else if (score > dScore){
		    System.out.println("Player won!");
		    System.out.println("You won $" + bet + "!");
		    
		    p.changeMoney(bet);
		    p.changeHandsPlayed(1);
		    p.changeHandsWon(1);
		    
		}

		// If the dealer's score was greater than the player's 
		// (and neither went over 21) then the dealer wins. 
		// The player loses their bet. Subtract their bet from
		// their money and increment their hands played.
		
		else if (dScore > score){
		    System.out.println("Dealer won!");
		    System.out.println("You lost $" + bet + "!");

		    p.changeMoney(-bet);
		    p.changeHandsPlayed(1);
		}

		// If the player and dealer scores are equal, then 
		// the player neither wins nor loses money. Only the 
		// handsPlayed variable is incremented.
		
		else if (score == dScore){
		    System.out.println("Push!");
		    
		    p.changeHandsPlayed(1);
		}
		    
	    }

	    // If the player's money is <= 0 at the end of the 
	    // round then they lose and the game loop is broken,
	    // ending the game.

	    
	    if (p.getMoney() <= 0){
		System.out.println("You have no more money to bet!");
		break;
	    }

	    // After the round if the player still has money, prompt them
	    // to play another round after displaying their information.
	    
	    printInfo(name, p);
	    play = contPlay();
	}

	// After the game ends, whether from losing or from refusing another round,
	// use the Player.writeFile() method to record their new information to their 
	// file and display a thank-you message.
	
	p.writeFile();
	System.out.println("Thank you for playing Infinite Blackjack!");
    }

    
    // Method for displaying the user's information. Accepts the name 
    // variable and the Player object as arguments.

    private static void printInfo(String name, Player p){
	System.out.printf("Name: %20s\n", name);
	System.out.printf("Total Hands: %13d\n", p.getHandsPlayed());
	System.out.printf("Hands Won: %15d\n", p.getHandsWon());
	System.out.printf("Money: %19.2f\n\n", p.getMoney());
	
    }


    // Method asking the player if they want to play another hand.
    // Returns their decision.

    private static String contPlay(){

	// Creates a Scanner and variable for storing their choice

	Scanner keyboard = new Scanner(System.in);
	String play;
	
	// Do while loop for verifying input
	// Prompts the user and then continues to prompt 
	// until they enter a string matching an appropriate response.
	
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


    // Method for taking the user's bet.
    // Accepts their current amount of money as an argument
    // Returns their bet amount

    private static double betting(double amount){
	
	// Creates a Scanner object and a variable for storing their bet.
	
	Scanner keyboard = new Scanner(System.in);
	double bet;
	
	// Do while loop for verifying input.
	// Prompts the user to enter an amount to bet and continues
	// to until they enter an amount between 0.01 and their current
	// amount of money.
	
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


    // Method for asking the user if they want ot hit or stay
    // Returns their decision.
    
    private static String hitStay(){

	// Creates a Scanner and a variable for storing their decision
	
	Scanner keyboard = new Scanner(System.in);
	String option;

	// Do while loop for verifying input.
	// Prompts the user to hit or stay and continues
	// to do so until they enter an appropriate response.

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


    // Method for calculating the dealer's move
    // Returns the dealer's final score.
    
    private static int dealer(){

	// Create variables for storing the dealer's score
	// and the amount of aces. Also creates the initial
	// two cards and checks for aces.

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

	// Display the dealer's first two cards and caclulates 
	// and dislpays their initial score.
	
	System.out.print("Cards: ");
	d1.printInfo();
	System.out.print(" ");
	d2.printInfo();
	System.out.println();
	
	score = d1.calcScore() + d2.calcScore();
	score = getScore(score, aCount);
	
	System.out.println("Score: " + score);

	// While the dealer's score is less than 22
	// this loop determines their move.

	while (score < 22){
	    
	    // If their score is over 18 or exactly 21 they stay
	    
	    if (score >= 18 || score == 21){
		System.out.println("Stay!");
	    
		break;
	    }

	    // If their score is 17 and they have no aces, they stay.

	    else if (score == 17 && aCount == 0){
		System.out.println("Stay!");
	    
		break;
	    }

	    // If their score is 17 or under or exactly 17 and they have
	    // at least one ace they hit.

	    else if (score <= 17){
		System.out.println("Hit!");

		// A new card is generated, checked for being an Ace, 
		// new score calculated, and new card/score displayed.

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

    // Professor Laboon's scoring method taken from his Github.

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
	
