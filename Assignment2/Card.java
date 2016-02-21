import java.util.Random;

public class Card{
    private String[] suits = {"d", "h", "s", "c"};
    private String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String suit;
    private String value;
    Random rng = new Random();

    int v = rng.nextInt(13);

    int s = rng.nextInt(4);
    
    public Card(){
	value = values[v];
	suit = suits[s];
    }

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

    public void printInfo(){
	System.out.print(value + suit);
    }

}
