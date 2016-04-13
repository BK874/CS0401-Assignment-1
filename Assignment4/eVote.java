/*
Assignment 4 - Voting
Brian Knotten
*/

// Necessary imports.

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The class is an extension of the JFrame class

public class eVote extends JFrame{

    // Create the necessary variables for the program.
  
    private String str = "";
    private int numBallots;
    private ArrayList <Ballot> ballots = new ArrayList<Ballot>();
    private ArrayList <Voter> voters = new ArrayList<Voter>();
    private JButton login;
    private JButton cast;

    // The constructor accepts the name of the ballot file as an argument.
    
    public eVote(String ballotFile) throws IOException{
        
	// An ArrayList for the ballots is created using the getBallots() method.
	// Likewise an ArrayList of the voters is created using getVoters().

	ballots = getBallots(ballotFile);
	voters = getVoters();
	
 	// The title, layout, and two buttons (Login to vote and Cast your vote)
	// are created and added to the JFrame. Additionally, a JPanel object
	// is created and added for each ballot in the ballots ArrayList.

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

    
    // A method for creating an ArrayList of Voter objects from voters.txt.

    private ArrayList<Voter> getVoters() throws IOException{
	
	// Each individual line is read in as a String and passed to the
	// Voter constructor before being added to the ArrayList.

	ArrayList <Voter> voters = new ArrayList<Voter>();
	File file = new File("voters.txt");
	Scanner inputFile = new Scanner(file);
	String str;
	
	while (inputFile.hasNext()){
	    str = inputFile.nextLine();
	    Voter y = new Voter(str);
	    voters.add(y);
	}
	inputFile.close();
	return voters;
    }
    
    // A method for creating an ArrayList of Ballot objects from the ballots file.

    private ArrayList<Ballot> getBallots(String ballotFile) throws IOException{

	// The number of ballots is read in, then a for loop reads in the specified
	// number of lines as Strings, with each one being passed to the Ballot 
	// construct before the object is added to the ArrayList.

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
	inputFile.close();
	return ballots;
    }

    // A method for determining if a voter is registered.

    private int findVoter(ArrayList<Voter> voters, String id){
	
	// The ArrayList of Voter objects and the voter's id number
	// (a String) are passed as arguments.
	// A for loop iterates through the ArrayList, comparing 
	// the id String to each Voter object's id.
	// If a match is found, the location of the matching 
	// object is returned. Otherwise -1 is returned.

	for (Voter v : voters){
	    if (id.equals(v.getID())){
		return voters.indexOf(v);
	    }
	}
	return -1;
    }
 
    // ActionListener for the Login button.

    private class LoginPressed implements ActionListener{
	
	public void actionPerformed(ActionEvent e){

	    // When the button is pressed, the user's voter id 
	    // is retrieved using a JOptionPane InputDialog.

	    JButton loggedIn = (JButton) e.getSource();
	    String userId = JOptionPane.showInputDialog("Please enter your voter ID");
	    if (userId != null){
		int voterIndex = findVoter(voters, userId);
		
		// If the user's id is found and they have not voted yet, the buttons
		// for each ballot are enabled and they are prompted to vote.

		if (voterIndex != -1){
		    if (voters.get(voterIndex).getStatus() == false){
			JOptionPane.showMessageDialog(null, voters.get(voterIndex).getName() + 
						      ", please make your choices.");
			voters.get(voterIndex).changeStatus();
			loggedIn.setEnabled(false);
			for (Ballot b : ballots){
			    b.changeEnabled();
			}
			cast.setEnabled(true);
		    
			// If they have already voted, they are reminded and nothing changes.

		    }else{
			JOptionPane.showMessageDialog(null, voters.get(voterIndex).getName() 
						      + ", you have already voted!");
		    }

		    // If they are not registerd they are told to register before voting and nothing 
		    // changes.
		    
		}else{
		    JOptionPane.showMessageDialog(null, "You are not registered to vote! Please" +
						  " register before attempting to vote!");
		}	     
		
	    }
	    
	}
    }

    // ActionListener for the Cast Vote button.

    private class CastPressed implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
	    
	    // A try-catch statement is used because the overriden actionPerformed
	    // method does not throw an IOException.

	    // The user is prompted to confirm their vote with a JOptionPane.

	    try{
		JButton voteCast = (JButton) e.getSource();
	    	    
		int v = JOptionPane.showConfirmDialog(null, "Confirm vote?", "Vote Confirmation",
						      JOptionPane.YES_NO_CANCEL_OPTION);
		
		// If they confirm the vote, the ballot and voter files are updated accordingly
		// and all buttons besides the Login one are disabled.


		if (v == 0){
		    voteCast.setEnabled(false);
		    login.setEnabled(true);
		    for (Ballot b : ballots){
			b.updateVotes();
			b.deselect();
			b.changeEnabled();
		    }
		    updateVoters(voters);
		}
	    }catch(IOException ex){
		System.out.println(ex.toString());
		System.out.println("Could not find file.");
	    }
	}
    }

    // Method to update the voters file. 
    
    public void updateVoters(ArrayList<Voter> voters) throws IOException{

	// The ArrayList of voters is passed as an argument and 
	// necessary variables are created.

	File file1 = new File("voters.txt");
	File file2 = new File("tempVotersTemp.txt");
	Scanner inputFile = new Scanner(file1);
	String oldLine;
	PrintWriter outputFile = new PrintWriter(file2);
	boolean updated = false;

	// The Scanner reads in the old lines one at a time, creates Voter 
	// objects for them and compares their status (true/false) to that of 
	// the corresponding Voter objects in the voters ArrayList.

	for (int k = 0; k < voters.size(); k++){
	    oldLine = inputFile.nextLine();
	    Voter z = new Voter(oldLine);
	    
	    // If one of the status' does not match, 
	    // a new file is written with the updated information, 
	    // the old file is deleted, and the new file is renamed to that
	    // of the old file.

	    if (voters.get(k).getStatus() != z.getStatus()){
		updated = true;
		for (Voter v : voters){
		    outputFile.println(v.getID() + ":" + v.getName() + ":" + v.getStatus());
		}

	    }
	}
	inputFile.close();
	outputFile.close();
	
	if (updated = true){
	    file1.delete();
	    file2.renameTo(file1);
	}else{
	    file2.delete();
	}
	
    }	
}
