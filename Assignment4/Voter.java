/*
Assignment 4 - Voting
Brian Knotten
*/

public class Voter{
 
    // Create the necessary variables for storing 
    // the Voter's info
    
    private String[] tokens;
    private String id;
    private String name;
    private boolean voted;

    // The constructor accepts a line of voters.txt as an argument

    public Voter(String str){

	// The line is split into an array and each 
	// "token" is set to the correct variable
	// (the voter's id, name, and voted status).
	
	tokens = str.split("[:]");

	id = tokens[0];
	name = tokens[1];
	voted = Boolean.parseBoolean(tokens[2]);
    }

    // Three methods for returning the voter's id,
    // name, and voted status.
    
    public String getID(){
	return id;
    }
    
    public String getName(){
	return name;
    }
    
    public boolean getStatus(){
	return voted;
    }
    
    // A method for changing the voter's voted status to true
    // (it must be manually set back to false by an admin).

    public void changeStatus(){
	voted = true;
    }
   
}
	
