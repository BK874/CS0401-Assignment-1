/*
Assignment 4 - Voting
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ballot extends JPanel{

    private String[] tokens;
    private String id;
    private JLabel office;
    private String[] candidates;
    private JButton[] buttons;

    public Ballot(String str){
	tokens = str.split("[:,]");
	
	id = tokens[0];
	office = new JLabel(tokens[1]);
	candidates = new String[tokens.length-2];
	buttons = new JButton[candidates.length];

	for (int i = 2; i < tokens.length; i++){
	    candidates[i-2] = tokens[i];
	}

	for (int j = 0; j < candidates.length; j++){
	    buttons[j] = new JButton(candidates[j]);
	    buttons[j].addActionListener(new ButtonListener());
	}
	
	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	office.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(office);
	for (JButton b : buttons){
	    b.setAlignmentX(Component.CENTER_ALIGNMENT);
	    add(b);
	 
	}
	
	    
    }
    
    class ButtonListener implements ActionListener{

	public void actionPerformerd(ActionEvent e){
	 
	    JButton pressed = (JButton) e.getSource();
	    pressed.setForeground(Color.RED);
	    
	}
	
    }
    
    public void printCandidates(){
	for (String s : candidates)
	    System.out.println(s);
    }

}
