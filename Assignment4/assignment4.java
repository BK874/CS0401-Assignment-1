/*
Assignment 4 - Voting
Brian Knotten
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class assignment4{
    
    public static void main(String[] args) throws IOException{

	String ballotFile = "";
	String str = "";
	int numBallots;
	ArrayList <Ballot> ballots = new ArrayList<Ballot>();
	Login login = new Login();

	if (args.length > 0){
	    System.out.println("DEMOCRACY!");
	    ballotFile = args[0];
	}else{
	    System.out.println("COMMUNIST!");
	}
	

	File File = new File(ballotFile);
	Scanner inputFile = new Scanner(File);
	
	numBallots = inputFile.nextInt();
	inputFile.nextLine();
	
	for (int i = 0; i < numBallots; i++){
	    str = inputFile.nextLine();
	    Ballot x = new Ballot(str);
	    ballots.add(x);
	}
	
	
	
	final int WINDOW_WIDTH = 350;
	final int WINDOW_HEIGHT = 200;
	JFrame window = new JFrame();
	window.setTitle("E-Vote Version 1.0");
	window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setLayout(new FlowLayout(FlowLayout.LEFT));
	for (Ballot b : ballots){
	  window.add(b);
	  b.printCandidates();
	}
	window.add(login);
	window.setVisible(true);
	
    }

}



