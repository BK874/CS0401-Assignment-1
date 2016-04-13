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

public class eVote extends JFrame{
  
    private String str = "";
    private int numBallots;
    private ArrayList <Ballot> ballots = new ArrayList<Ballot>();
    private ArrayList <Voter> voters = new ArrayList<Voter>();
    private JButton login;
    private JButton cast;

    public eVote(String[] ballotFile) throws IOException{
	    
	if (ballotFile.length > 0){
	    System.out.println("DEMOCRACY!");
	}else{
	    System.out.println("COMMUNIST!");
	}
	
	ballots = getBallots(ballotFile[0]);
	voters = getVoters();
	
 	
	setTitle("E-Vote Version 1.0");
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setLayout(new GridLayout(1, ballots.size() + 2));
	for (Ballot b : ballots){
	    add(b);
	}
	cast = new JButton("Cast your vote");
	cast.addActionListener(new CastPressed());
	cast.setEnabled(false);
	login = new JButton("Login to vote");
	login.addActionListener(new LoginPressed());
	add(login);
	add(cast);
	pack();
	setVisible(true);
    }

    
    private ArrayList<Voter> getVoters() throws IOException{
	
	ArrayList <Voter> voters = new ArrayList<Voter>();
	File file = new File("voters.txt");
	Scanner inputFile = new Scanner(file);
	String str;
	
	while (inputFile.hasNext()){
	    str = inputFile.nextLine();
	    Voter y = new Voter(str);
	    voters.add(y);
	}
	return voters;
    }
    
    private ArrayList<Ballot> getBallots(String ballotFile) throws IOException{
	ArrayList <Ballot> ballots = new ArrayList<Ballot>();
	File File = new File(ballotFile);
	Scanner inputFile = new Scanner(File);
	String str;
	int numBallots;

	numBallots = inputFile.nextInt();
	inputFile.nextLine();
	
	for (int i = 0; i < numBallots; i++){
	    str = inputFile.nextLine();
	    Ballot x = new Ballot(str);
	    ballots.add(x);
	}
	return ballots;
    }

    private int findVoter(ArrayList<Voter> voters, String id){
	
	for (Voter v : voters){
	    if (id.equals(v.getID())){
		return voters.indexOf(v);
	    }
	}
	return -1;
    }
 

    private class LoginPressed implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
	    try{
		JButton loggedIn = (JButton) e.getSource();
		String userId = JOptionPane.showInputDialog("Please enter your voter ID");
		ArrayList <Voter> voters = new ArrayList<Voter>();
		voters = getVoters();
		int voterIndex = findVoter(voters, userId);
		
		if (voterIndex != -1){
		    if (voters.get(voterIndex).getStatus() == false){
			JOptionPane.showMessageDialog(null, voters.get(voterIndex).getName() + 
						  ", please make your choices.");
			loggedIn.setEnabled(false);
			for (Ballot b : ballots){
			    b.changeEnabled();
			}
			cast.setEnabled(true);
		    }else{
			JOptionPane.showMessageDialog(null, voters.get(voterIndex).getName() 
						      + ", you have already voted!");
		    }
		    
		}else{
		    JOptionPane.showMessageDialog(null, "You are not registered to vote! Please" +
						      " register before attempting to vote!");
		}	     
	    }catch(IOException ex){
		System.out.println(ex.toString());
		System.out.println("Could not find file.");
	    }
	}
    
    }

    private class CastPressed implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
	    
	    JButton voteCast = (JButton) e.getSource();
	    	    
	    int v = JOptionPane.showConfirmDialog(null, "Confirm vote?", "Vote Confirmation",
					 JOptionPane.YES_NO_CANCEL_OPTION);

	    if (v == 0){
		voteCast.setEnabled(false);
		login.setEnabled(true);
		for (Ballot b : ballots){
		    b.deselect();
		    b.changeEnabled();
		}
	    }
	}
    }
}
