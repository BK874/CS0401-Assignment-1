/*
Assignment 4 - Voting
Brian Knotten
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class assignment4{
    
    public static void main(String[] args) throws IOException{

	String ballotFile = "";
	String str = "";
	int numBallots;
	ArrayList <Ballot> ballots = new ArrayList<Ballot>();

	if (args.length > 0){
	    System.out.println("DEMOCRACY!");
	    ballotFile = args[0];
	}else{
	    System.out.println("COMMUNIST!");
	}
	

	File file = new File(ballotFile);
	Scanner inputFile = new Scanner(file);
	
	numBallots = inputFile.nextInt();
	inputFile.nextLine();
	
	for (int i = 0; i < numBallots; i++){
	    str = inputFile.nextLine();
	    Ballot x = new Ballot(str);
	    ballots.add(x);
	}
	    
}

