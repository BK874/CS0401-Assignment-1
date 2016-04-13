/*
Assignment 4 - Voting
*/

import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ballot extends JPanel{

    private String[] tokens;
    private String id;
    private JLabel office;
    private String[] candidates;
    private JButton[] buttons;
    private int[] votes;
    private File file1;
    private PrintWriter outputFile;
    private File file2;
    private Scanner inputFile;
    private String oldLine;

    public Ballot(String str) throws IOException{
	tokens = str.split("[:,]");
	
	id = tokens[0];
	office = new JLabel(tokens[1]);
	candidates = new String[tokens.length-2];
	buttons = new JButton[candidates.length];
	votes = new int[candidates.length];
	file1 = new File(tokens[0]+".txt");
	outputFile = new PrintWriter(file1);

	for (int i = 2; i < tokens.length; i++){
	    candidates[i-2] = tokens[i];
	}
	ActionListener listener = new ButtonListener();
	for (int j = 0; j < candidates.length; j++){
	    buttons[j] = new JButton(candidates[j]);
	    buttons[j].addActionListener(listener);
	}
	
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
    
    private class ButtonListener implements ActionListener{

	public void actionPerformed(ActionEvent e){
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
    
    public void printCandidates(){
	for (String s : candidates)
	    System.out.println(s);
    }
    
    public void changeEnabled(){
	for (JButton b : buttons)
	    b.setEnabled(!b.isEnabled());
    }
    
    public void deselect(){
	for (JButton b : buttons)
	    b.setForeground(Color.BLACK);
    }
    
    public void updateVotes() throws IOException{
	Color red = Color.RED;
	file2 = new File("tempBallotsTemp.txt");
	inputFile = new Scanner(file1);
	outputFile = new PrintWriter(file2);

	for (int p = 0; p < buttons.length; p++){
	    if (buttons[p].getForeground() == red){
		votes[p]++;
	    }
	}
	
	for (int m = 0; m < candidates.length; m++){
	    oldLine = inputFile.nextLine();
	    tokens = oldLine.split("[:]");
	    System.out.println(Integer.parseInt(tokens[1]));
	    if (Integer.parseInt(tokens[1]) != votes[m]){
		for (int n = 0; n < candidates.length; n++){
		    outputFile.println(candidates[n] + ":" + votes[n]);
		}
	    }
	}
	inputFile.close();
	outputFile.close();
	file1.delete();
	file2.renameTo(file1);
    }
	    
	
	
}
