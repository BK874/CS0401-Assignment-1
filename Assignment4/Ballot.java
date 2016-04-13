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
	
	    
    }
    
    class ButtonListener implements ActionListener{

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

}
