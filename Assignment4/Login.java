/*
Assignment 4 - Voting
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JPanel{
    
    public Login(){
	
	JButton login = new JButton("Login to vote");
	login.addActionListener(new LoginPressed());
	
	setLayout(new BorderLayout());
	add(login, BorderLayout.CENTER);

	
    }

    class LoginPressed implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
	    
	    JButton loggedIn = (JButton) e.getSource();
 
	    loggedIn.setEnabled(false);
	    
	     new LoginInput();
	    
	}
    }
}	
