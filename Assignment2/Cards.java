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

	if (suit == "d"){
	    name = "Diamonds";
	}
	else if (suit == "h"){
	    name = "Hearts";
	}
	else if (suit == "s"){
	    name == "Spades";
	}
	else if (suit == "c"){
	    name == "Clubs";
	}

	return name;
    }

    public String getValue(){
	String num = "";
	
	if (value == "A"){
	    num = "Ace";
	}
	else if (value == "J"){
	    num = "Jack";
	}
	else if (value == "Q"){
	    num = "Queen";
	}
	else if (value == "K"){
	    num = "King";
	}
	else{
	    num = value;
	}
	
	return num;
    }

    public void printInfo(){
	System.out.println(value.toString()+suit.toString());
    }

}
