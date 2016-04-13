/*
Assignment 4 - Voting
*/

// Necessary imports.

import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// The class is an extension of the JPanel class

public class Ballot extends JPanel{

    // Create the necessary variables for the program

    private String[] tokens;
    private String id;
    private JLabel office;
    private String[] candidates;
    private JButton[] buttons;
    private int[] votes;
    private File file;
    private PrintWriter outputFile;
   
    // The constructor accepts a line of the ballot file as an argument

    public Ballot(String str) throws IOException{
	
	// The line is split into an array and 
	// each "token" is assigned to the appropriate 
	// variable (id, office, candidate, etc.)
	// The candidates are placed in an array
	// and a file is created using the ballot's id

	tokens = str.split("[:,]");
	
	id = tokens[0];
	office = new JLabel(tokens[1]);
	candidates = new String[tokens.length-2];
	buttons = new JButton[candidates.length];
	votes = new int[candidates.length];
	file = new File(tokens[0]+".txt");
	outputFile = new PrintWriter(file);

	for (int i = 2; i < tokens.length; i++){
	    candidates[i-2] = tokens[i];
	}

	// Each candidate's button is assigned an 
	// ActionLIstener.

	ActionListener listener = new ButtonListener();
	for (int j = 0; j < candidates.length; j++){
	    buttons[j] = new JButton(candidates[j]);
	    buttons[j].addActionListener(listener);
	}

	// The panel is setup in a BoxLayout and 
	// each button is added and disabled.
	
	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	office.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(office);
	for (JButton b : buttons){
	    b.setAlignmentX(Component.CENTER_ALIGNMENT);
	    add(b);
	    b.setEnabled(false);
	}
	
	for (int k = 0; k < candidates.length; k++){
	    outputFile.println(candidates[k] + ":" + votes[k]);
	}
	outputFile.close();
	    
    }

    // ActionListener for the candidate's buttons
    
    private class ButtonListener implements ActionListener{

	public void actionPerformed(ActionEvent e){

	    // Every time a button is pressed it's color
	    // is checked. If it is black, it is 
	    // set to red and the rest are set to black.
	    // If it is red, it is set to black.

	    Color red = Color.RED;
	    JButton pressed = (JButton) e.getSource();
	    
	    if (pressed.getForeground() == red){
		pressed.setForeground(Color.BLACK);
	    }else{
		for (JButton b : buttons){
		    b.setForeground(Color.BLACK);
		}
		pressed.setForeground(Color.RED);
	    }
	}
	
    }

    // Method that disables/enables the buttons depending 
    // on their current status.

    public void changeEnabled(){
	for (JButton b : buttons)
	    b.setEnabled(!b.isEnabled());
    }
    
    // Method that resets all the buttons to black.

    public void deselect(){
	Color red = Color.RED;
	for (JButton b : buttons)
	    if (b.getForeground() == red){
		b.setForeground(Color.BLACK);
	    }
    }
    
    // Method for updating each ballot's vote count.

    public void updateVotes() throws IOException{
	
	// Variables for comparing the color of a button,
	// reading/writing the files, and the filenames
	// themselves are created.

	Color red = Color.RED;
	File file1 = new File(id + ".txt");
	File file2 = new File("tempBallotsTemp.txt");
	Scanner inputFile = new Scanner(file1);
	PrintWriter outputFile = new PrintWriter(file2);
	String oldLine = "";
	boolean updated = false;

	// The array of buttons is iterated over and each
	// one is checked for selection. If one is, it's vote
	// count (stored in the corresponding index of the votes
	// array) is incremented.

	for (int p = 0; p < buttons.length; p++){
	    if (buttons[p].getForeground() == red){
		votes[p]++;
	    }
	}
	
	// The each line of the old file is read in one by one,
	// split, and the vote count is compared to the current one.
	// If a mismatch is found, the new file is written with the 
	// updated information and renamed to the old file after it
	// is deleted.

	for (int m = 0; m < candidates.length; m++){
	    oldLine = inputFile.nextLine();
	    tokens = oldLine.split("[:]");
	    if (Integer.parseInt(tokens[1]) != votes[m]){
		updated = true;
		for (int n = 0; n < candidates.length; n++){
		    outputFile.println(candidates[n] + ":" + votes[n]);
		}
	    }
	}
	inputFile.close();
	outputFile.close();
	
	if (updated == true){
	    file1.delete();
	    file2.renameTo(file1);
	}else{
	    file2.delete();
	}
    }
	    
	
	
}
