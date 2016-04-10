/*
Assignment 4 - Voting
Brian Knotten
*/

public class Voter{
    
    private String[] tokens;
    private String id;
    private String name;
    private boolean voted;

    public Voter(String str){
	tokens = str.split("[:]");
	
	id = tokens[0];
	name = tokens[1];
	voted = Boolean.parseBoolean(tokens[2]);
    }
 
    public String getID(){
	return id;
    }
    
    public String getName(){
	return name;
    }
    
    public String getStatus(){
	return voted;
    }
    
    public void changeStatus(){
	voted = true;
    }
   
}
	
