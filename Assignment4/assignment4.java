/*
Assignment 4 - Voting
Brian Knotten
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class assignment4{
    
    public static void main(String[] args) throws IOException{

	String ballots = "";
	
	
	if (args.length > 0){
	    System.out.println("DEMOCRACY!");
	    ballots = args[0];
	}else{
	    System.out.println("COMMUNIST!");
	}
	
	System.out.println(ballots);

	
	
    }
}
