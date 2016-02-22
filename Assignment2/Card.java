import java.util.Random;

public class Card{

    // Create two arrays: one for the four possible suits and 
    // one for the thirteen possible values. Also create a random
    // number generator and declare Strings for each individual card's
    // suit and value

    private String[] suits = {"d", "h", "s", "c"};
    private String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String suit;
    private String value;
    Random rng = new Random();

    // Generate two integers: 0-3 for the suits and 0-12 for the values

    int v = rng.nextInt(13);

    int s = rng.nextInt(4);

    // The constructor uses the randomly generated values to index the 
    // suits and values arrays and assigns the generated value to the 
    // suit and value variables - now we have our randomly generated card.
    
    public Card(){
	value = values[v];
	suit = suits[s];
    }

    // Method for returning the card's suit - reads the letter stored in
    // the suit variable and returns the full name of the suit.

    public String getSuit(){
	String name = "";

	if (suit.equals("d")){
	    name = "Diamonds";
	}
	else if (suit.equals("h")){
	    name = "Hearts";
	}
	else if (suit.equals("s")){
	    name = "Spades";
	}
	else if (suit.equals("c")){
	    name = "Clubs";
	}

	return name;
    }

    // Method for returning the card's value - reads the letter or number 
    // stored in the value variable and returns either the name of the 
    // face values or the number of the number cards.

    public String getValue(){
	String num = "";
	
	if (value.equals("A")){
	    num = "Ace";
	}
	else if (value.equals("J")){
	    num = "Jack";
	}
	else if (value.equals("Q")){
	    num = "Queen";
	}
	else if (value.equals("K")){
	    num = "King";
	}
	else{
	    num = value;
	}
	
	return num;
    }

    // Method for calculating the point value of the card.
    // If the value variable holds a 10, Jack, Queen, or King 
    // the score variable is set to 10. If the value is an Ace
    // the score is set to 11 (because the scoring method in 
    // Blackjack.java assumes Aces are worth 11). Otherwise
    // the score is set to the integer value stored in value.
    
    public int calcScore(){
	int score;
	if (value.equals("10") || value.equals("J") ||
	    value.equals("Q") || value.equals("K")){
	    score = 10;
	}
	else if (value.equals("A")){
	    score = 11;
	}
	else{
	    score = Integer.parseInt(value);
	}
	return score;
    }

    // Method for printing the value and suit of the card.

    public void printInfo(){
	System.out.print(value + suit);
    }

}
