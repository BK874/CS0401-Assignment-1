/*
Assignment 4 - Voting
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ballot extends JPanel{

    private String[] tokens;
    private String id;
    private String office;
    private String[] candidates;

    public Ballot(String str){
	tokens = str.split("[:,]");
	
	id = tokens[0];
	office = tokens[1];
	candidates = new String[tokens.length-2];

	for (int i = 2; i < tokens.length; i++){
	    candidates[i-2] = tokens[i];
	}
	    
    }
    
    public void printCandidates(){
	for (String s : candidates)
	    System.out.println(s);
    }

}
