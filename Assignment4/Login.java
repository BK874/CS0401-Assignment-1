/*
Assignment 4 - Voting
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JPanel{
    
    private JButton login;
    private boolean loggedIn;
    LoginInput loginPrompt = new LoginInput();

    public Login(){
	
	loggedIn = false;
	login = new JButton("Login to vote");
	login.addActionListener(new LoginPressed());
	
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	login.setAlignmentX(Component.CENTER_ALIGNMENT);
	login.setAlignmentY(Component.CENTER_ALIGNMENT);
	add(login);

	
    }

    class LoginPressed implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
	    
	    JButton loggedIn = (JButton) e.getSource();
 
	    loggedIn.setEnabled(false);
	    
	    loginPrompt.reveal();
	    
	}
    }
    
    public boolean getStatus(){
	return loginPrompt.getStatus();
    }
    
    public void changeStatus(){
	loginPrompt.changeStatus();
    }
    
}	
