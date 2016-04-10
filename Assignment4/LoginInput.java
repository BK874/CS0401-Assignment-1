/*
Assignment 4 - Voting
*/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginInput extends JFrame{
    
    private JPanel panel;
    private JLabel prompt;
    private JTextField idTextField;
    private JButton loginButton;
    private JButton cancelButton;
    private final int WINDOW_WIDTH = 300;
    private final int WINDOW_HEIGHT = 200;
    
    public LoginInput(){
	
	setTitle("Login");
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	buildPanel();
	add(panel);
	setVisible(true);

    }
    
    private void buildPanel(){

	prompt = new JLabel("Please enter your voter ID");
	idTextField = new JTextField(20);
	loginButton = new JButton("Login");
	cancelButton = new JButton("Cancel");
	ActionListener listener = new LoginListener();
	loginButton.addActionListener(listener);
	cancelButton.addActionListener(listener);
	panel = new JPanel();
	
	panel.add(prompt);
	panel.add(idTextField);
	panel.add(loginButton);
	panel.add(cancelButton);
    }
    
    class LoginListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
	    
	    String actionCommand = e.getActionCommand();
	    ArrayList <Voter> voters =  new ArrayList<Voter>();
	    String input;
	    int voterIndex;

	    if (actionCommand.equals("Login")){
		
		voters = getVoters();
		input = idTextField.getText();
		voterIndex = findVoter(voters, input);

		if (voterIndex != -1){
		    if (voters.get(voterIndex).getStatus() == false){
			JOptionPane.showMessageDialog(null, voters.get(voterIndex).getName() + 
						      ", please make your choices.");
		    }else{
			JOptionPane.showMessageDialog(null, voters.get(voterIndex).getName() 
						      + ", you have already voted!");
		    }
		    
		}else{
		    JOptionPane.showMessageDialog(null, "You are not registered to vote! Please" +
						  " register before attempting to vote!");
		}
		    
	    }else{
		System.exit(0);
	    }
	}
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
    
    private int findVoter(ArrayList<Voter> voters, String id){

	for (Voter v : voters){
	    if (id.equals(v.getID())){
		return voters.indexOf(v);
	    }
	}
	return -1;
    }
	
}
