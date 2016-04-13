/*
Assignment 4 - Voting
Brian Knotten
*/

// Import needed for file I/O

import java.io.*;

public class assignment4{
    
    public static void main(String[] args) throws IOException{

	// Accepts one argument (a .txt file) from the command line
	// and passes it into the constructor for the eVote class.
	// Otherwise it exits.
	
	if (args.length == 1){
	    new eVote(args[0]);
	}else{
	    System.exit(0);
	}
	    
    }
}
